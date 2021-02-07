package com.lothrazar.autoplant;

import com.lothrazar.autoplant.config.ConfigClientManager;
import com.lothrazar.autoplant.config.ConfigManager;
import com.lothrazar.autoplant.event.ItemEvents;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleMod.MODID)
public class ExampleMod {

  public static final String MODID = "autoplant";
  public static final Logger LOGGER = LogManager.getLogger();

  public ExampleMod() {
    ConfigManager.setup();
    ConfigClientManager.setup();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
  }

  private void setup(final FMLCommonSetupEvent event) {
    //now all blocks/items exist  
    MinecraftForge.EVENT_BUS.register(new ItemEvents());
    //  MinecraftForge.EVENT_BUS.register(new WorldGenEvents());
    if (ConfigClientManager.ATTEST.get()) {
      float test = Blocks.BEDROCK.getDefaultState().hardness;
      ExampleMod.LOGGER.info("accesstransformer.cfg test bedrock hardness = " + test);
    }
  }

  private void setupClient(final FMLClientSetupEvent event) {
    //for client side only setup
  }
}
