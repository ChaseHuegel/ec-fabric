package io.chasehuegel.ecfabric.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HomeTpItem extends Item {

    public HomeTpItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!user.getEntityWorld().isClient()) {
            MinecraftServer server = world.isClient ? MinecraftClient.getInstance().getServer() : user.getServer();
            BlockPos respawnPos = server.getPlayerManager().getPlayer(user.getUuid()).getSpawnPointPosition();

            if (respawnPos != server.getOverworld().getSpawnPos()) {
                user.setCurrentHand(hand);

                user.teleport(respawnPos.getX(), respawnPos.getY(), respawnPos.getZ());
                user.getItemCooldownManager().set(this, 60);

                itemStack.setCount(itemStack.getCount()-1);

                user.playSound(SoundEvents.ENTITY_EVOKER_CAST_SPELL, 1f, 1f);

                return TypedActionResult.consume(itemStack);
            }

            user.sendMessage(Text.of("You do not have a bed."), true);
            return TypedActionResult.fail(itemStack);
        }
        
        return super.use(world, user, hand);
    }
    
}
