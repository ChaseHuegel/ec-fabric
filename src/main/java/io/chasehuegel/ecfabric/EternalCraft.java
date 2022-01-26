package io.chasehuegel.ecfabric;

import net.fabricmc.api.ModInitializer;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.chasehuegel.ecfabric.block.CustomBlocks;
import io.chasehuegel.ecfabric.item.CustomItemGroup;
import io.chasehuegel.ecfabric.item.CustomItems;

public class EternalCraft implements ModInitializer {
	public static final String Namespace = "ecfabric";

	public static final Logger LOGGER = LogManager.getLogger(Namespace);

	public static final Random Random = new Random();

	@Override
	public void onInitialize() {
		CustomBlocks.Initialize();
		CustomItems.Initialize();
		CustomItemGroup.Initialize();
	}
}
