package varianttombstones.betterwithfurniture.block.logic;

import java.util.Objects;
import java.util.function.Supplier;

import net.minecraft.core.block.IPaintable;
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

public class BlockLogicDrawers extends BlockLogicRotatable implements IPaintable {
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
	   Block<?> paintedBlock = ModBlocks.DRAWER_PAINTED;

	   if (block == ModBlocks.CABINET_BLOCK)
	   {
		   paintedBlock = ModBlocks.CABINET_PAINTED;
	   }

	   int meta = world.getBlockData(tilePosc);
	   world.setBlockTypeDataRaw(tilePosc, paintedBlock, meta);
	   world.setBlockData(tilePosc, meta);

	   if (block == ModBlocks.CABINET_PAINTED) {
		   ((BlockLogicDrawersPainted) ModBlocks.CABINET_PAINTED.getLogic()).setColor(world, tilePosc, dyeColor);
	   } else {
		   ((BlockLogicDrawersPainted) ModBlocks.DRAWER_PAINTED.getLogic()).setColor(world, tilePosc, dyeColor);
	   }
	}
}
