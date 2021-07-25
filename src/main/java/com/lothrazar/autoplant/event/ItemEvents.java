package com.lothrazar.autoplant.event;

import com.lothrazar.autoplant.UtilString;
import com.lothrazar.autoplant.config.ConfigManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemEvents {
  //  @SubscribeEvent
  //  public void onItemTossEvent(ItemTossEvent event) {
  //    APMod.LOGGER.info("lfiespan hack " + event.getEntityItem());
  //    if (event.getPlayer().isCreative()) {
  //      event.getEntityItem().lifespan = 50;
  //    }
  //  }

  @SubscribeEvent
  public void onItemExpireEvent(ItemExpireEvent event) {
    if (ConfigManager.DOSAPLINGS.get() == false
        || event.getEntityItem().isAlive() == false) {
      return;
    }
    ItemEntity item = event.getEntityItem();
    ItemStack itemstack = item.getItem();
    Level world = item.level;
    BlockPos pos = item.blockPosition();
    if (!world.isEmptyBlock(pos)) {
      return;
    }
    if (this.isPlantable(itemstack)) {
      //sapling tags yes
      Block block = Block.byItem(itemstack.getItem());
      if (block != null && block != Blocks.AIR
          && block.defaultBlockState().canSurvive(world, pos)
          && world.setBlockAndUpdate(pos, block.defaultBlockState())) {
        itemstack.shrink(1);
        event.getEntityItem().setItem(itemstack);
        if (event.getEntityItem().getItem().isEmpty()) {
          event.getEntity().remove(Entity.RemovalReason.KILLED);
        }
        else { // not really needed
          //increase life
          event.setExtraLife(3000);
          event.setCanceled(true);
          //            event.getEntityItem().lifespan = 6000;
        }
      }
    }
  }

  private boolean isPlantable(ItemStack itemstack) {
    if (ConfigManager.DOSAPLINGS.get() && itemstack.is(ItemTags.SAPLINGS)) {
      return true;
    }
    return UtilString.isInList(ConfigManager.DOTHESEBLOCKS.get(), itemstack.getItem().getRegistryName());
  }
}
