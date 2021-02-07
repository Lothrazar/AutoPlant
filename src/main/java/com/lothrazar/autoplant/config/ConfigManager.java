package com.lothrazar.autoplant.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.lothrazar.autoplant.APMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.fml.loading.FMLPaths;

public class ConfigManager {

  private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
  private static ForgeConfigSpec CFG;
  public static BooleanValue DOPLANTING;
  static {
    initConfig();
  }

  private static void initConfig() {
    BUILDER.comment("General settings").push(APMod.MODID);
    DOPLANTING = BUILDER.comment("do it").define("doPlanting", true);
    BUILDER.pop();
    CFG = BUILDER.build();
  }

  public static void setup() {
    final CommentedFileConfig configData = CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get().resolve(APMod.MODID + ".toml"))
        .sync()
        .autosave()
        .writingMode(WritingMode.REPLACE)
        .build();
    configData.load();
    CFG.setConfig(configData);
  }
}
