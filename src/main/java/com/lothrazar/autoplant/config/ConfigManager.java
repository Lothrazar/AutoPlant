package com.lothrazar.autoplant.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.lothrazar.autoplant.APMod;
import java.util.Arrays;
import java.util.List;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.loading.FMLPaths;

public class ConfigManager {

  private static final ForgeConfigSpec.Builder CFG = new ForgeConfigSpec.Builder();
  private static final List<String> DFLT = Arrays.asList(new String[] {
      "minecraft:grass"
  });
  private static ForgeConfigSpec COMMON_CONFIG;
  public static BooleanValue DOSAPLINGS;
  public static ConfigValue<List<String>> DOTHESEBLOCKS;
  static {
    initConfig();
  }

  private static void initConfig() {
    CFG.comment("General settings").push(APMod.MODID);
    //
    DOSAPLINGS = CFG.comment("Planting saplings allowed").define("plantAllSaplings", true);
    DOTHESEBLOCKS = CFG.comment("Extra blocks to auto plant").define("extras", DFLT);
    //
    CFG.pop();
    COMMON_CONFIG = CFG.build();
  }

  public static void setup() {
    final CommentedFileConfig configData = CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get().resolve(APMod.MODID + ".toml"))
        .sync()
        .autosave()
        .writingMode(WritingMode.REPLACE)
        .build();
    configData.load();
    COMMON_CONFIG.setConfig(configData);
  }
}
