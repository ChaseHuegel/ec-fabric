package io.chasehuegel.ecfabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EternalCraft implements ModInitializer {
	public static final String Namespace = "ecfabric";

	public static final Logger LOGGER = LogManager.getLogger(Namespace);

	public static final Item WATERING_CAN = new Item(new FabricItemSettings().group(ItemGroup.TOOLS));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier(Namespace, "wooden_watering_can"), WATERING_CAN);
	}
}
