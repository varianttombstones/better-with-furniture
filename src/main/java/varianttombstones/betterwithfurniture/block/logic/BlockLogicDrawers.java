package varianttombstones.betterwithfurniture.block.logic;

import java.util.Objects;
import java.util.function.Supplier;

import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.IPaintable;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import varianttombstones.betterwithfurniture.block.ModBlocks;

public class BlockLogicDrawers extends BlockLogicRotatable implements IPaintable, BlockLogic.MatcherDataEquivalency {
   public BlockLogicDrawers(Block<?> block, Material material, @NotNull Supplier<TileEntity> tileEntity) {
      super(block, material);
	  block.withEntity(tileEntity);
   }

   public boolean onInteracted(World world, TilePosc tilePos, Player player, @Nullable Side side, double xHit, double yHit) {
	   if (!world.isClientSide) {
		   player.displayChestScreen(getInventory(world, tilePos), (double) tilePos.x(), (double) tilePos.y(), (double) tilePos.z());
	   }
	   return true;
   }

   public static Container getInventory(World world, TilePosc tilePos) {
	   return (Container)Objects.requireNonNull(world.getTileEntity(tilePos));
   }

	@Override
	public void setColor(@NotNull World world, @NotNull TilePosc tilePosc, @NotNull DyeColor dyeColor) {
		int meta = world.getBlockData(tilePosc);

		// I have to do this, which is a bit wacky because
		// otherwise the block automatically flips when painted,
		// strange, but this fixes it.
		Direction direction = Direction.fromId(meta & 7).opposite();
		int newMeta = BlockLogicRotatablePainted.setDirection(meta, direction);
		if (block == ModBlocks.CABINET_BLOCK) {
			world.setBlockTypeDataRaw(tilePosc, ModBlocks.CABINET_PAINTED, newMeta);
			world.setBlockDataNotify(tilePosc, newMeta);
			((BlockLogicRotatablePainted) ModBlocks.CABINET_PAINTED.getLogic()).setColor(world, tilePosc, dyeColor);

		} else {
			world.setBlockTypeDataRaw(tilePosc, ModBlocks.DRAWER_PAINTED, newMeta);
			world.setBlockDataNotify(tilePosc, newMeta);
			((BlockLogicDrawersPainted) ModBlocks.DRAWER_PAINTED.getLogic()).setColor(world, tilePosc, dyeColor);
		}
	}
}
