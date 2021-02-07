package com.lothrazar.autoplant.event;

import com.lothrazar.autoplant.APMod;
import com.lothrazar.autoplant.config.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemEvents {

  @SubscribeEvent
  public void onItemExpireEvent(ItemExpireEvent event) {
    if (ConfigManager.DOPLANTING.get() == false
        || event.getEntityItem().isAlive() == false) {
      return;
    }
    ItemEntity item = event.getEntityItem();
    ItemStack itemstack = item.getItem();
    System.out.println(event.getExtraLife() + "; plantable? " + itemstack);
    APMod.LOGGER.info("plantable? " + itemstack.getItem().getTags());
    World world = event.getEntity().world;
    BlockPos pos = event.getEntity().getPosition();
    if (world.isAirBlock(pos) &&
        itemstack.getItem().isIn(ItemTags.SAPLINGS)) {
      //sapling tags yes
      Block b = Block.getBlockFromItem(itemstack.getItem());
      if (b != null && b != Blocks.AIR) {
        if (world.setBlockState(item.getPosition(), b.getDefaultState())) {
          event.getEntity().remove();
        }
      }
    }
  }
}
