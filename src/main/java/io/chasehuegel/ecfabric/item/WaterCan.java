package io.chasehuegel.ecfabric.item;

import org.jetbrains.annotations.Nullable;
import io.chasehuegel.ecfabric.EternalCraft;
import net.fabricmc.fabric.impl.transfer.fluid.CauldronStorage;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        BlockHitResult hitResult = WaterCan.raycast(world, player, RaycastContext.FluidHandling.SOURCE_ONLY);

        if (hitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        }

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = hitResult.getBlockPos();
            Direction direction = hitResult.getSide();
            BlockPos blockPos2 = blockPos.offset(direction);

            if (!world.canPlayerModifyAt(player, blockPos) || !player.canPlaceOn(blockPos2, direction, itemStack)) {
                return TypedActionResult.fail(itemStack);
            }

            BlockState blockState = world.getBlockState(blockPos);
            FluidState fluidState = world.getFluidState(blockPos);
            Fluid blockFluid = fluidState.getFluid();

            if (blockState.getBlock() instanceof CauldronBlock) {
                blockFluid = CauldronStorage.get(world, blockPos).getResource().getFluid();
            }

            if (this.fluid == Fluids.EMPTY || this.fluid == blockFluid) {
                boolean filled = false;

                if (blockState.getBlock() instanceof FluidDrainable && !((FluidDrainable) blockState.getBlock())
                        .tryDrainFluid(world, blockPos, blockState).isEmpty()) {
                    world.emitGameEvent((Entity) player, GameEvent.FLUID_PICKUP, blockPos);
                    filled = true;
                } else if (blockState.getBlock() instanceof CauldronBlock
                        && blockState.get(LeveledCauldronBlock.LEVEL) > 0) {
                    player.incrementStat(Stats.USE_CAULDRON);
                    LeveledCauldronBlock.decrementFluidLevel(blockState, world, blockPos);
                    filled = true;
                }

                if (filled) {
                    this.playFillingSound(player, world, blockPos);
                    player.incrementStat(Stats.USED.getOrCreateStat(this));
                    itemStack = refillStack(itemStack, player, blockFluid);

                    return TypedActionResult.success(itemStack, world.isClient());
                } else {
                    return TypedActionResult.fail(itemStack);
                }
            }

            if (this.pourFluid(player, world, blockPos)) {
                this.onPour(player, world, itemStack, blockPos);

                if (player instanceof ServerPlayerEntity) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, blockPos, itemStack);
                }

                if (itemStack.damage(1, EternalCraft.MinecraftRandom,
                        (player instanceof ServerPlayerEntity) ? (ServerPlayerEntity) player : null)) {
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

            world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5f,
                    2.6f + (world.random.nextFloat() - world.random.nextFloat()) * 0.8f);

            for (int l = 0; l < 8; ++l) {
                world.addParticle(ParticleTypes.LARGE_SMOKE, (double) i + Math.random(), (double) j + Math.random(),
                        (double) k + Math.random(), 0.0, 0.0, 0.0);
            }

            return true;
        }

        ParticleEffect farmlandParticle = ParticleTypes.SPLASH;

        if (this.fluid == Fluids.WATER)
            farmlandParticle = ParticleTypes.SPLASH;
        else if (this.fluid == Fluids.LAVA)
            farmlandParticle = ParticleTypes.FLAME;

        // for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 0,
        // 1)))
        boolean didPour = false;
        BlockPos blockPos = pos;
        if (world.getBlockState(blockPos).getBlock() instanceof FarmlandBlock) {
            BlockState blockState = world.getBlockState(blockPos);

            if ((this.fluid == Fluids.LAVA && blockState.get(Properties.MOISTURE) > 0) ||
                    (this.fluid == Fluids.WATER && blockState.get(Properties.MOISTURE) < FarmlandBlock.MAX_MOISTURE)) {
                if (this.fluid == Fluids.WATER)
                    world.setBlockState(blockPos, blockState.with(Properties.MOISTURE, FarmlandBlock.MAX_MOISTURE),
                            Block.NOTIFY_LISTENERS);
                else if (this.fluid == Fluids.LAVA)
                    world.setBlockState(blockPos, blockState.with(Properties.MOISTURE, 0), Block.NOTIFY_LISTENERS);

                world.emitGameEvent((Entity) player, GameEvent.BLOCK_CHANGE, blockPos);

                int i = blockPos.getX();
                int j = blockPos.getY() + 1;
                int k = blockPos.getZ();

                for (int l = 0; l < 8; ++l) {
                    world.addParticle(farmlandParticle, (double) i + Math.random(), (double) j + Math.random(),
                            (double) k + Math.random(), 0.0, 0.0, 0.0);
                }

                BlockState blockStateAbove = world.getBlockState(blockPos.up());

                if (blockStateAbove.getBlock() instanceof CropBlock) {
                    CropBlock cropBlock = (CropBlock) blockStateAbove.getBlock();

                    if (this.fluid.isIn(FluidTags.WATER)) {
                        if (cropBlock.canGrow(world, EternalCraft.MinecraftRandom, blockPos.up(), blockStateAbove)) {
                            cropBlock.applyGrowth(world, blockPos.up(), blockStateAbove);
                        }
                    } else if (this.fluid.isIn(FluidTags.LAVA)) {
                        world.breakBlock(blockPos.up(), false);
                    }
                }

                didPour = true;
            }
        }

        return didPour;
    }

    public boolean tryEquip(ItemStack itemStack, World world, PlayerEntity player) {
        EquipmentSlot equipmentSlot = EquipmentSlot.HEAD;
        ItemStack itemStack2 = player.getEquippedStack(equipmentSlot);

        if (itemStack2.isEmpty()) {
            player.equipStack(equipmentSlot, itemStack.copy());

            if (!world.isClient()) {
                player.incrementStat(Stats.USED.getOrCreateStat(this));
            }

            itemStack.setCount(0);

            return true;
        }

        return false;
    }

    public static ItemStack refillStack(ItemStack itemStack, PlayerEntity player, Fluid fluid) {
        if (fluid == Fluids.WATER) {
            itemStack = WaterCan.getFilledStack(itemStack, player, Fluids.WATER);
        } else if (fluid == Fluids.LAVA) {
            itemStack = WaterCan.getFilledStack(itemStack, player, Fluids.LAVA);
        }

        return itemStack;
    }

    public static ItemStack getFilledStack(ItemStack stack, PlayerEntity player, Fluid fluid) {
        if (!player.getAbilities().creativeMode) {
            Item item = CustomItems.EMPTY_CAN;

            if (fluid.isIn(FluidTags.WATER))
                item = CustomItems.WATER_CAN;
            else if (fluid.isIn(FluidTags.LAVA))
                item = CustomItems.LAVA_CAN;

            ItemStack itemStack = new ItemStack(item);
            return itemStack;
        }

        return stack;
    }

    public static ItemStack getFilledStack(ItemStack stack, PlayerEntity player) {
        if (!player.getAbilities().creativeMode) {
            ItemStack itemStack = new ItemStack(CustomItems.WATER_CAN);
            return itemStack;
        }

        return stack;
    }

    public static ItemStack getPartialStack(ItemStack stack, PlayerEntity player, int amount) {
        if (!player.getAbilities().creativeMode) {
            ItemStack itemStack = new ItemStack(CustomItems.WATER_CAN);
            itemStack.setDamage(itemStack.getMaxDamage() - amount);
            return itemStack;
        }

        return stack;
    }

    public static ItemStack getEmptiedStack(ItemStack stack, PlayerEntity player) {
        if (!player.getAbilities().creativeMode) {
            return new ItemStack(CustomItems.EMPTY_CAN);
        }

        return stack;
    }

    protected void playFillingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        SoundEvent soundEvent = this.fluid.isIn(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA
                : SoundEvents.ITEM_BUCKET_EMPTY;
        world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    protected void playPouringSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        SoundEvent soundEvent = this.fluid.isIn(FluidTags.LAVA) ? SoundEvents.BLOCK_LAVA_EXTINGUISH
                : SoundEvents.ENTITY_PLAYER_SPLASH;
        world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
}