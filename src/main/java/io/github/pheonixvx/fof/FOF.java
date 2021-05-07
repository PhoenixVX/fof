package io.github.pheonixvx.fof;

import io.github.pheonixvx.fof.config.ModConfig;
import io.github.pheonixvx.fof.registry.RegistryHelper;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

public class FOF implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("fof");

	@Override
	public void onInitialize () {
		LOGGER.info("Forge of Fiends is loading...");
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
		RegistryHelper.initializeRegistries();
		GeckoLib.initialize();
	}

}
