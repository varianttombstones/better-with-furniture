package varianttombstones.betterwithfurniture.block.tileentity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.mojang.nbt.tags.CompoundTag;
import com.mojang.nbt.tags.ListTag;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventorySorter;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.world.World;

public class TileEntityDrawers extends TileEntity implements Container {
   private ItemStack[] drawerContents = new ItemStack[9];

   public TileEntityDrawers() {
   }

   public int getContainerSize() {
      return 9;
   }

   public @Nullable ItemStack getItem(int slot) {
      return this.drawerContents[slot];
   }

   public @Nullable ItemStack removeItem(int slot, int takeAmount) {
      if (this.drawerContents[slot] != null) {
         if (this.drawerContents[slot].stackSize <= takeAmount) {
            ItemStack itemstack = this.drawerContents[slot];
            this.drawerContents[slot] = null;
            this.setChanged();
            return itemstack;
         } else {
            ItemStack itemstack1 = this.drawerContents[slot].splitStack(takeAmount);
            if (this.drawerContents[slot].stackSize <= 0) {
               this.drawerContents[slot] = null;
            }

            this.setChanged();
            return itemstack1;
         }
      } else {
         return null;
      }
   }

   public void setItem(int slot, @Nullable ItemStack stack) {
      this.drawerContents[slot] = stack;
      if (stack != null && stack.stackSize > this.getMaxStackSize()) {
         stack.stackSize = this.getMaxStackSize();
      }

      this.setChanged();
   }

   public @NotNull String getNameTranslationKey() {
      return "tile.betterwithfurniture.drawers.name";
   }

   public void readAdditionalData(@NotNull CompoundTag compoundTag) {
      ListTag nbttaglist = compoundTag.getList("Items");
      this.drawerContents = new ItemStack[this.getContainerSize()];

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         CompoundTag nbttagcompound1 = (CompoundTag)nbttaglist.tagAt(i);
         int j = nbttagcompound1.getByte("Slot") & 255;
         if (j >= 0 && j < this.drawerContents.length) {
            this.drawerContents[j] = ItemStack.readItemStackFromNbt(nbttagcompound1);
         }
      }

   }

   public void writeAdditionalData(@NotNull CompoundTag compoundTag) {
      ListTag nbttaglist = new ListTag();

      for(int i = 0; i < this.drawerContents.length; ++i) {
         if (this.drawerContents[i] != null) {
            CompoundTag nbttagcompound1 = new CompoundTag();
            nbttagcompound1.putByte("Slot", (byte)i);
            this.drawerContents[i].writeToNBT(nbttagcompound1);
            nbttaglist.addTag(nbttagcompound1);
         }
      }

      compoundTag.put("Items", nbttaglist);
   }

   public int getMaxStackSize() {
      return 64;
   }

   public boolean stillValid(@NotNull Player player) {
      if (this.worldObj != null && this.worldObj.getTileEntity(this.tilePos.x, this.tilePos.y, this.tilePos.z) == this) {
         return player.distanceToSqr((double)this.tilePos.x + (double)0.5F, (double)this.tilePos.y + (double)0.5F, (double)this.tilePos.z + (double)0.5F) <= (double)64.0F;
      } else {
         return false;
      }
   }

   public void sort() {
      InventorySorter.sortInventory(this.drawerContents);
   }

   public void dropContents(World world, int x, int y, int z) {
      super.dropContents(world, x, y, z);

      for(int i = 0; i < this.getContainerSize(); ++i) {
         ItemStack itemStack = this.getItem(i);
         if (itemStack != null) {
            EntityItem item = world.dropItem(x, y, z, itemStack);
            item.xd *= (double)0.5F;
            item.yd *= (double)0.5F;
            item.zd *= (double)0.5F;
            item.pickupDelay = 0;
         }
      }

   }
}
