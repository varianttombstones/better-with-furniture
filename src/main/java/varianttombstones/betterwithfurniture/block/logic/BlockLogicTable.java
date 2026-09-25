package varianttombstones.betterwithfurniture.block.logic;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntitySeat;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.support.ISupport;
import net.minecraft.core.block.support.PartialSupport;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;

public class BlockLogicTable extends BlockLogicRotatable {
    public BlockLogicTable(Block<?> block) {
      super(block, Materials.WOOD);
      this.setBlockBounds((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.5625F, (double)1.0F);
      block.withEntity(() -> new TileEntitySeat(block));
   }

   // TODO: CHANGE THIS TO THE ITEM FOR THE TABLE
   public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int data, TileEntity tileEntity) {
      return dropCause != EnumDropCause.IMPROPER_TOOL ? new ItemStack[]{new ItemStack(Items.SEAT)} : null;
   }
}