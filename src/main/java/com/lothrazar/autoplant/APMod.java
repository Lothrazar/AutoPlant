package com.lothrazar.autoplant;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lothrazar.autoplant.config.ConfigAutoPlant;
import com.lothrazar.autoplant.event.ItemEvents;

@Mod(APMod.MODID)
public class APMod {

  public static final String MODID = "autoplant";
  public static final Logger LOGGER = LogManager.getLogger();

  public APMod(IEventBus modEventBus, ModContainer modContainer) {
    modContainer.registerConfig(ModConfig.Type.COMMON, ConfigAutoPlant.CONFIG);
    NeoForge.EVENT_BUS.register(new ItemEvents());
  }

}
