package com.lothrazar.autoplant.config;

import java.util.Arrays;
import java.util.List;
import com.lothrazar.autoplant.APMod;
import com.lothrazar.library.config.ConfigTemplate;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ConfigAutoPlant extends ConfigTemplate {

  private static final List<String> DFLT = Arrays.asList(new String[] {
      "minecraft:grass"
  });
  private static ForgeConfigSpec CONFIG;
  public static BooleanValue DOSAPLINGS;
  public static ConfigValue<List<String>> DOTHESEBLOCKS;
  static {
    final ForgeConfigSpec.Builder BUILDER = builder();
    BUILDER.comment("General settings").push(APMod.MODID);
    //
    DOSAPLINGS = BUILDER.comment("Planting saplings allowed").define("plantAllSaplings", true);
    DOTHESEBLOCKS = BUILDER.comment("Extra blocks to auto plant").define("extras", DFLT);
    //
    BUILDER.pop();
    CONFIG = BUILDER.build();
  }

  public ConfigAutoPlant() {
    CONFIG.setConfig(setup(APMod.MODID));
  }
}
