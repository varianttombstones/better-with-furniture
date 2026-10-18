package varianttombstones.betterwithfurniture.block.logic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
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
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import varianttombstones.betterwithfurniture.BetterWithFurniture;

public class BlockLogicChairPainted extends BlockLogicRotatablePainted {
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

	@Override
	public void onPlacedByMob(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, @NotNull Mob mob, double xHit, double yHit) {
		int meta = world.getBlockData(tilePos);
		world.setBlockDataNotify(tilePos, setDirection(meta, mob.getHorizontalPlacementDirection(side).opposite()));
	}
}
