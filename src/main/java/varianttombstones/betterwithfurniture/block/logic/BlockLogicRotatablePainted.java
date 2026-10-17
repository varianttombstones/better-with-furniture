package varianttombstones.betterwithfurniture.block.logic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.IPainted;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockLogicRotatablePainted extends BlockLogicRotatable implements IPainted, BlockLogic.MatcherDataEquivalency {
	public BlockLogicRotatablePainted(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
	}

	public void setColor(@NotNull World world, @NotNull TilePosc tilePos, @NotNull DyeColor color) {
		int data = world.getBlockData(tilePos);
		int newMetaData = this.stripColorFromMetadata(data) | this.toMetadata(color) << 3;
		world.setBlockDataNotify(tilePos, newMetaData);
	}

	public @NotNull DyeColor fromMetadata(int meta) {
		return DyeColor.colorFromBlockMeta(meta & 15);
	}

	public int toMetadata(@NotNull DyeColor color) {
		return color.blockMeta;
	}

	public int stripColorFromMetadata(int meta) {
		return meta & 7;
	}

	public void removeDye(@NotNull World world, @NotNull TilePosc tilePos) {
		world.setBlockDataNotify(tilePos, 0);
	}

	@Override
	public @NotNull String getLanguageKey(int meta) {
		return super.getLanguageKey(meta) + "." + this.fromMetadata(meta).colorID;
	}

	public static int setDirection(int meta, Direction direction) {
		return meta << 3 | direction.id;
	}

	public void onPlacedByMob(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, @NotNull Mob mob, double xHit, double yHit) {
		int meta = world.getBlockData(tilePos);
		world.setBlockDataNotify(tilePos, setDirection(meta, mob.getHorizontalPlacementDirection(side)));
	}

	public void onPlacedOnSide(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		if (!side.isHorizontal()) {
			side = Side.SOUTH;
		}

		int meta = world.getBlockData(tilePos);
		world.setBlockDataNotify(tilePos, setDirection(meta, side.direction()));
	}

	public int getPlacedData(@Nullable Player player, @NotNull ItemStack itemStack, @NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		return itemStack.getMetadata();
	}

	public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(this, 1, (data >> 3) & 15)};
	}
}
