package com.lothrazar.autoplant.event;

import com.lothrazar.autoplant.config.ConfigAutoPlant;
import com.lothrazar.library.util.StringParseUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemEvents {

  @SubscribeEvent
  public void onItemExpireEvent(ItemExpireEvent event) {
    if (ConfigAutoPlant.DOSAPLINGS.get() == false
        || event.getEntity().isAlive() == false) {
      return;
    }
    ItemEntity item = event.getEntity();
    ItemStack itemstack = item.getItem();
    Level world = item.level();
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
        event.getEntity().setItem(itemstack);
        if (event.getEntity().getItem().isEmpty()) {
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
    if (ConfigAutoPlant.DOSAPLINGS.get() && itemstack.is(ItemTags.SAPLINGS)) {
      return true;
    }
    return StringParseUtil.isInList(ConfigAutoPlant.DOTHESEBLOCKS.get(), ForgeRegistries.ITEMS.getKey(itemstack.getItem().asItem()));
  }
}
