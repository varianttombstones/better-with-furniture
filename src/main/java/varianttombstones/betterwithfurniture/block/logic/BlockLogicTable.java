package varianttombstones.betterwithfurniture.block.logic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;

public class BlockLogicTable extends BlockLogicRotatable {
    public BlockLogicTable(Block<?> block) {
      super(block, Materials.WOOD);
      this.setBlockBounds((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.5625F, (double)1.0F);
   }

   // TODO: CHANGE THIS TO THE ITEM FOR THE TABLE
   public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int data, TileEntity tileEntity) {
      return dropCause != EnumDropCause.IMPROPER_TOOL ? new ItemStack[]{new ItemStack(Items.SEAT)} : null;
   }

   public boolean isCubeShaped() {
      return false;
   }

   public boolean isSolidRender() {
      return false;
   }
}