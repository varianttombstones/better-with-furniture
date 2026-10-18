package varianttombstones.betterwithfurniture.block.logic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.IPainted;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import varianttombstones.betterwithfurniture.block.ModBlocks;

public class BlockLogicTablePainted extends BlockLogicTable implements IPainted {
	public BlockLogicTablePainted(Block<?> block, boolean isCoffeeTable) {
		super(block, isCoffeeTable);
	}

	public void setColor(@NotNull World world, @NotNull TilePosc tilePos, @NotNull DyeColor color) {
		int meta = this.toMetadata(color);
		if (this.isCoffeeTable) {
			world.setBlockTypeDataRaw(tilePos, ModBlocks.COFFEE_TABLE_PAINTED, meta);
		} else {
			world.setBlockTypeDataRaw(tilePos, ModBlocks.TABLE_PAINTED, meta);
		}

		world.setBlockDataNotify(tilePos, meta);
	}

	public int getPlacedData(@Nullable Player player, @NotNull ItemStack itemStack, @NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		return itemStack.getMetadata() & 15;
	}

	public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(this, 1, data)};
	}

	public @NotNull DyeColor fromMetadata(int meta) {
		return DyeColor.colorFromBlockMeta(meta);
	}

	public int toMetadata(@NotNull DyeColor color) {
		return color.blockMeta;
	}

	public int stripColorFromMetadata(int meta) {
		return 0;
	}

	public void removeDye(@NotNull World world, @NotNull TilePosc tilePos) {
		world.setBlockDataNotify(tilePos, 0);
	}

	@Override
	public @NotNull String getLanguageKey(int meta) {
		return super.getLanguageKey(meta) + "." + this.fromMetadata(meta).colorID;
	}

	public boolean isCubeShaped() {
		return false;
	}

	public boolean isSolidRender() {
		return false;
	}
}
