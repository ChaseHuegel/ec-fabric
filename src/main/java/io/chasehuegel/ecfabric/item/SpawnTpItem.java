package io.chasehuegel.ecfabric.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpawnTpItem extends Item {

    public SpawnTpItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!user.getEntityWorld().isClient()) {
            user.setCurrentHand(hand);

            ServerWorld serverWorld = world.isClient ? MinecraftClient.getInstance().getServer().getOverworld() : user.getServer().getOverworld();

            BlockPos spawnPos = serverWorld.getSpawnPos();
            user.teleport(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
            user.getItemCooldownManager().set(this, 60);

            itemStack.setCount(itemStack.getCount()-1);

            user.playSound(SoundEvents.ENTITY_EVOKER_CAST_SPELL, 1f, 1f);

            return TypedActionResult.consume(itemStack);
        }

        return super.use(world, user, hand);
    }
    
}
