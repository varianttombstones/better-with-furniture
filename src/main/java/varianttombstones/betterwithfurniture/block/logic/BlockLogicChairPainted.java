package varianttombstones.betterwithfurniture.block.logic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.IPainted;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntitySeat;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.support.ISupport;
import net.minecraft.core.block.support.PartialSupport;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import varianttombstones.betterwithfurniture.BetterWithFurniture;
import varianttombstones.betterwithfurniture.block.ModBlocks;

public class BlockLogicChairPainted extends BlockLogicRotatable implements IPainted, BlockLogic.MatcherDataEquivalency {
	public BlockLogicChairPainted(Block<?> block) {

		super(block, Materials.WOOD);
		this.setBlockBounds((double) 0.0F, (double) 0.0F, (double) 0.0F, (double) 1.0F, (double) 0.4375F, (double) 1.0F);
		block.withEntity(() -> new TileEntitySeat(block));
	}

	@Override
	public boolean onInteracted(World world, TilePosc tilePos, Player player, @Nullable Side side, double xHit, double yHit) {
		int meta = world.getBlockData(tilePos);
		BetterWithFurniture.LOGGER.info("printing furniture: " + meta);
		if (world.shouldBlockSuffocateEntities(tilePos.up(new TilePos()), player.getClass())) {
			return false;
		} else {
			if (!world.isClientSide) {
				if (player.getCurrentEquippedItem() != null &&
					player.getCurrentEquippedItem().itemID == Items.PAINTBRUSH.id)
				{
					return false;
				}

				TileEntitySeat tileEntity = (TileEntitySeat) world.getTileEntity(tilePos);
				if (tileEntity != null && tileEntity.getPassenger() == null) {
					player.startRiding(tileEntity);
				}
			}

			return true;
		}
	}

	public void onRemoved(World world, TilePosc tilePos, int data) {
		if (!world.isClientSide) {
			TileEntitySeat tileEntity = (TileEntitySeat) world.getTileEntity(tilePos);
			if (tileEntity != null && tileEntity.getPassenger() != null) {
				tileEntity.ejectRider();
			}
		}

		super.onRemoved(world, tilePos, data);
	}

	public boolean isCubeShaped() {
		return false;
	}

	public boolean isSolidRender() {
		return false;
	}

	public @NotNull ISupport getSupport(World world, TilePosc tilePos, Side side) {
		return PartialSupport.INSTANCE;
	}

	public void setColor(@NotNull World world, @NotNull TilePosc tilePos, @NotNull DyeColor color) {
		int data = world.getBlockData(tilePos);
		int newMetaData = this.stripColorFromMetadata(data) | this.toMetadata(color) << 3;
		world.setBlockDataNotify(tilePos, newMetaData);
	}

	public int getPlacedData(@Nullable Player player, @NotNull ItemStack itemStack, @NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		return itemStack.getMetadata();
	}

	public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(this, 1, data)};
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
		BetterWithFurniture.LOGGER.info("WOWIE DIRECTION SET, THE META IS " + (meta << 3 | direction.id));
		return meta << 3 | direction.id;
	}

	public void onPlacedByMob(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, @NotNull Mob mob, double xHit, double yHit) {
		int meta = world.getBlockData(tilePos);
		world.setBlockDataNotify(tilePos, setDirection(meta, mob.getHorizontalPlacementDirection(side).opposite()));
	}

	public void onPlacedOnSide(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		if (!side.isHorizontal()) {
			side = Side.SOUTH;
		}

		int meta = world.getBlockData(tilePos);
		world.setBlockDataNotify(tilePos, setDirection(meta, side.direction()));
	}

	public static void setDefaultDirection(@NotNull World world, @NotNull TilePosc tilePos) {
		if (!world.isClientSide) {
			TilePos queryPos = new TilePos();
			Block<?> bN = world.getBlockType(tilePos.north(queryPos));
			Block<?> bS = world.getBlockType(tilePos.south(queryPos));
			Block<?> bW = world.getBlockType(tilePos.west(queryPos));
			Block<?> bE = world.getBlockType(tilePos.east(queryPos));
			Direction direction = Direction.NORTH;
			if (bN.solid() && !bS.solid()) {
				direction = Direction.SOUTH;
			}

			if (bS.solid() && !bN.solid()) {
				direction = Direction.NORTH;
			}

			if (bW.solid() && !bE.solid()) {
				direction = Direction.EAST;
			}

			if (bE.solid() && !bW.solid()) {
				direction = Direction.WEST;
			}

			world.setBlockDataNotify(tilePos, setDirection(world.getBlockData(tilePos), direction));
		}
	}
}
