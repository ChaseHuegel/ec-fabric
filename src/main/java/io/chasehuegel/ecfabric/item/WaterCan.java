package io.chasehuegel.ecfabric.item;

import org.jetbrains.annotations.Nullable;
import io.chasehuegel.ecfabric.EternalCraft;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.FluidDrainable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

public class WaterCan extends Item {
    private final Fluid fluid;
 
    public WaterCan(Fluid fluid, Settings settings) {
        super(settings);
        this.fluid = fluid;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        BlockHitResult blockHitResult = WaterCan.raycast(world, player, this.fluid == Fluids.EMPTY ? RaycastContext.FluidHandling.SOURCE_ONLY : RaycastContext.FluidHandling.NONE);
        
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        }

        if (blockHitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getSide();
            BlockPos blockPos2 = blockPos.offset(direction);

            if (!world.canPlayerModifyAt(player, blockPos) || !player.canPlaceOn(blockPos2, direction, itemStack)) {
                return TypedActionResult.fail(itemStack);
            }

            if (this.fluid == Fluids.EMPTY) {
                BlockState blockState = world.getBlockState(blockPos);
                FluidState fluidState = world.getFluidState(blockPos);
                Fluid blockFluid = fluidState.getFluid();

                if (blockState.getBlock() instanceof FluidDrainable && !((FluidDrainable)blockState.getBlock()).tryDrainFluid(world, blockPos, blockState).isEmpty()) {
                    world.emitGameEvent((Entity)player, GameEvent.FLUID_PICKUP, blockPos);
                    
                    if (itemStack.getDamage() > 0) {
                        itemStack.damage(-1, EternalCraft.Random, (player instanceof ServerPlayerEntity) ? (ServerPlayerEntity)player : null);
                    }
                    else {
                        if (blockFluid == Fluids.WATER){
                            itemStack = WaterCan.getFilledStack(itemStack, player, Fluids.WATER);
                        } else if (blockFluid == Fluids.LAVA){
                            itemStack = WaterCan.getFilledStack(itemStack, player, Fluids.LAVA);
                        }
                    }
                    
                    this.playFillingSound(player, world, blockPos);
                    player.incrementStat(Stats.USED.getOrCreateStat(this));
                    
                    return TypedActionResult.success(itemStack, world.isClient());
                }

                return TypedActionResult.fail(itemStack);
            }

            if (this.pourFluid(player, world, blockPos)) {
                this.onPour(player, world, itemStack, blockPos);

                
                if (player instanceof ServerPlayerEntity) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, blockPos, itemStack);
                }
                
                if (itemStack.damage(1, EternalCraft.Random, (player instanceof ServerPlayerEntity) ? (ServerPlayerEntity)player : null)) {
                    itemStack = WaterCan.getEmptiedStack(itemStack, player);
                }
                
                this.playPouringSound(player, world, blockPos);
                player.incrementStat(Stats.USED.getOrCreateStat(this));

                return TypedActionResult.success(itemStack, world.isClient());
            }

            return TypedActionResult.fail(itemStack);
        }
        return TypedActionResult.pass(itemStack);
    }

    public void onPour(@Nullable PlayerEntity player, World world, ItemStack stack, BlockPos pos) {
    }

    public boolean pourFluid(@Nullable PlayerEntity player, World world, BlockPos pos) {
        if (world.getDimension().isUltrawarm() && this.fluid == Fluids.WATER) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();

            world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5f, 2.6f + (world.random.nextFloat() - world.random.nextFloat()) * 0.8f);
            
            for (int l = 0; l < 8; ++l) {
                world.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0, 0.0, 0.0);
            }
            
            return true;
        }

        ParticleEffect farmlandParticle = ParticleTypes.SPLASH;

        if (this.fluid == Fluids.WATER)
            farmlandParticle = ParticleTypes.SPLASH;
        else if (this.fluid == Fluids.LAVA)
            farmlandParticle = ParticleTypes.FLAME;

        boolean didPour = false;
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 0, 1))) {
            if (world.getBlockState(blockPos).getBlock() instanceof FarmlandBlock) {
                BlockState blockState = world.getBlockState(blockPos);

                if ((this.fluid == Fluids.LAVA && blockState.get(Properties.MOISTURE) > 0) || 
                    (this.fluid == Fluids.WATER && blockState.get(Properties.MOISTURE) < FarmlandBlock.MAX_MOISTURE))
                {
                    if (this.fluid == Fluids.WATER)
                    world.setBlockState(blockPos, blockState.with(Properties.MOISTURE, FarmlandBlock.MAX_MOISTURE), Block.NOTIFY_LISTENERS);
                    else if (this.fluid == Fluids.LAVA)
                    world.setBlockState(blockPos, blockState.with(Properties.MOISTURE, 0), Block.NOTIFY_LISTENERS);
                    
                    world.emitGameEvent((Entity)player, GameEvent.BLOCK_CHANGE, blockPos);

                    int i = blockPos.getX();
                    int j = blockPos.getY() + 1;
                    int k = blockPos.getZ();

                    for (int l = 0; l < 8; ++l) {
                        world.addParticle(farmlandParticle, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0, 0.0, 0.0);
                    }

                    BlockState blockStateAbove = world.getBlockState(blockPos.up());

                    if (blockStateAbove.getBlock() instanceof CropBlock) {
                        CropBlock cropBlock = (CropBlock)blockStateAbove.getBlock();

                        if (this.fluid.isIn(FluidTags.WATER)) {
                            if (cropBlock.canGrow(world, EternalCraft.Random, blockPos.up(), blockStateAbove)) {
                                cropBlock.applyGrowth(world, blockPos.up(), blockStateAbove);
                            }
                        } else if (this.fluid.isIn(FluidTags.LAVA)) {
                            world.breakBlock(blockPos.up(), false);
                        }
                    }

                    didPour = true;
                }
            }
        }

        return didPour;
    }

    public static ItemStack getFilledStack(ItemStack stack, PlayerEntity player, Fluid fluid) {
        if (!player.getAbilities().creativeMode) {
            Item item = Items.EMPTY_CAN;

            if (fluid.isIn(FluidTags.WATER))
                item = Items.WATER_CAN;
            else if (fluid.isIn(FluidTags.LAVA))
                item = Items.LAVA_CAN;

            ItemStack itemStack = new ItemStack(item);
            return itemStack;
        }
        return stack;
    }

    public static ItemStack getFilledStack(ItemStack stack, PlayerEntity player) {
        if (!player.getAbilities().creativeMode) {
            ItemStack itemStack = new ItemStack(Items.WATER_CAN);
            return itemStack;
        }
        return stack;
    }

    public static ItemStack getPartialStack(ItemStack stack, PlayerEntity player, int amount) {
        if (!player.getAbilities().creativeMode) {
            ItemStack itemStack = new ItemStack(Items.WATER_CAN);
            itemStack.setDamage(itemStack.getMaxDamage() - amount);
            return itemStack;
        }
        return stack;
    }

    public static ItemStack getEmptiedStack(ItemStack stack, PlayerEntity player) {
        if (!player.getAbilities().creativeMode) {
            return new ItemStack(Items.EMPTY_CAN);
        }
        return stack;
    }

    protected void playFillingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        SoundEvent soundEvent = this.fluid.isIn(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;
        world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    protected void playPouringSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        SoundEvent soundEvent = this.fluid.isIn(FluidTags.LAVA) ? SoundEvents.BLOCK_LAVA_EXTINGUISH : SoundEvents.ENTITY_PLAYER_SPLASH;
        world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
}