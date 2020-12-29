package io.github.pheonixvx.fof;

import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FOF implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("fof");

	@Override
	public void onInitialize () {
		LOGGER.info("Forge of Fiends is loading...");
		RegistryHandler.initializeRegistries();
	}

}
