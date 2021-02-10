package com.lothrazar.autoplant.event;

import com.lothrazar.autoplant.UtilString;
import com.lothrazar.autoplant.config.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
    World world = item.world;
    BlockPos pos = item.getPosition();
    if (!world.isAirBlock(pos)) {
      return;
    }
    if (this.isPlantable(itemstack.getItem())) {
      //sapling tags yes
      Block block = Block.getBlockFromItem(itemstack.getItem());
      if (block != null && block != Blocks.AIR
          && block.getDefaultState().isValidPosition(world, pos)
          && world.setBlockState(pos, block.getDefaultState())) {
        itemstack.shrink(1);
        event.getEntityItem().setItem(itemstack);
        if (event.getEntityItem().getItem().isEmpty()) {
          event.getEntity().remove();
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

  private boolean isPlantable(Item item) {
    if (ConfigManager.DOSAPLINGS.get() && item.isIn(ItemTags.SAPLINGS)) {
      return true;
    }
    return UtilString.isInList(ConfigManager.DOTHESEBLOCKS.get(), item.getRegistryName());
  }
}
