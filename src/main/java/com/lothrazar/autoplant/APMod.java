package com.lothrazar.autoplant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lothrazar.autoplant.config.ConfigAutoPlant;
import com.lothrazar.autoplant.event.ItemEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(APMod.MODID)
public class APMod {

  public static final String MODID = "autoplant";
  public static final Logger LOGGER = LogManager.getLogger();

  public APMod() {
    new ConfigAutoPlant();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
  }

  private void setup(final FMLCommonSetupEvent event) {
    MinecraftForge.EVENT_BUS.register(new ItemEvents());
  }
}
