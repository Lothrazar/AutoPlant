package com.lothrazar.autoplant.config;

import java.util.Arrays;
import java.util.List;
import com.lothrazar.autoplant.APMod;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigAutoPlant  {

  private static final List<String> DFLT = Arrays.asList("minecraft:short_grass");
  public static final ModConfigSpec CONFIG;
  public static ModConfigSpec.BooleanValue DOSAPLINGS;
  public static ModConfigSpec.ConfigValue<List<? extends String>> DOTHESEBLOCKS;
  static {
    final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    BUILDER.comment("General settings").push(APMod.MODID);
    //
    DOSAPLINGS = BUILDER.comment("Planting saplings allowed").define("plantAllSaplings", true);
    DOTHESEBLOCKS = BUILDER.comment("Extra blocks to auto plant").defineListAllowEmpty("extras",DFLT,()->"",o->o instanceof String);
    //
    BUILDER.pop();
    CONFIG = BUILDER.build();
  }
}
