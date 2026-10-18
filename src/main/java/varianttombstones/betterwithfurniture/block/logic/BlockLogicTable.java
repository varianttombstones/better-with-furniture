package varianttombstones.betterwithfurniture.block.logic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.IPaintable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.support.FullSupport;
import net.minecraft.core.block.support.ISupport;
import net.minecraft.core.block.support.PartialSupport;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import varianttombstones.betterwithfurniture.block.ModBlocks;

public class BlockLogicTable extends BlockLogic implements IPaintable {
	protected boolean isCoffeeTable = false;

	public BlockLogicTable(Block<?> block, boolean isCoffeeTable) {
		super(block, Materials.WOOD);
		this.isCoffeeTable = isCoffeeTable;
		if (this.isCoffeeTable) {
			this.setBlockBounds((double) 0.0F, (double) 0.0F, (double) 0.0F, (double) 1.0F, (double) 0.4375F, (double) 1.0F);
		}
	}

	public boolean isCubeShaped() {
		return false;
	}

	public boolean isSolidRender() {
		return false;
	}

	public @NotNull ISupport getSupport(World world, TilePosc tilePos, Side side) {
		return FullSupport.INSTANCE;
	}

	public void setColor(@NotNull World world, @NotNull TilePosc tilePos, @NotNull DyeColor color) {
		int meta = world.getBlockData(tilePos);
		if (this.isCoffeeTable) {
			world.setBlockTypeDataRaw(tilePos, ModBlocks.COFFEE_TABLE_PAINTED, meta);
			world.setBlockDataNotify(tilePos, meta);
			((BlockLogicTablePainted) ModBlocks.COFFEE_TABLE_PAINTED.getLogic()).setColor(world, tilePos, color);
		} else {
			world.setBlockTypeDataRaw(tilePos, ModBlocks.TABLE_PAINTED, meta);
			world.setBlockDataNotify(tilePos, meta);
			((BlockLogicTablePainted) ModBlocks.TABLE_PAINTED.getLogic()).setColor(world, tilePos, color);
		}
	}
}
