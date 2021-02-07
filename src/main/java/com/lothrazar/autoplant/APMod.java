package com.lothrazar.autoplant;

import com.lothrazar.autoplant.config.ConfigManager;
import com.lothrazar.autoplant.event.ItemEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(APMod.MODID)
public class APMod {

  public static final String MODID = "autoplant";
  public static final Logger LOGGER = LogManager.getLogger();

  public APMod() {
    ConfigManager.setup();
    //    ConfigClientManager.setup();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
  }

  private void setup(final FMLCommonSetupEvent event) {
    MinecraftForge.EVENT_BUS.register(new ItemEvents());
  }

  private void setupClient(final FMLClientSetupEvent event) {
    //for client side only setup
  }
}
