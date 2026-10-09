package varianttombstones.betterwithfurniture.block.logic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.IPainted;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntitySeat;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockLogicChairPainted extends BlockLogicChair implements IPainted, BlockLogic.MatcherDataEquivalency {
	public BlockLogicChairPainted(Block<?> block) {
		super(block);
	}

	@Override
	public boolean onInteracted(World world, TilePosc tilePos, Player player, @Nullable Side side, double xHit, double yHit) {
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

	public void setColor(@NotNull World world, @NotNull TilePosc tilePos, @NotNull DyeColor color) {
		int meta = world.getBlockData(tilePos);
		world.setBlockData(tilePos, meta & 15 | this.toMetadata(color));
		world.notifyBlockChange(tilePos, this.block);
	}
}
