package varianttombstones.betterwithfurniture.block.logic;

import java.util.Objects;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.render.block.model.generic.BlockModelGenericFurnace;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import varianttombstones.betterwithfurniture.block.tileentity.TileEntityDrawers;

public class BlockLogicDrawers extends BlockLogicRotatable {
   public BlockLogicDrawers(Block<?> block, Material material) {
      super(block, material);
      block.withEntity(TileEntityDrawers::new);
   }

   public boolean onInteracted(World world, TilePosc tilePos, Player player, @Nullable Side side, double xHit, double yHit) {
      if (world.isClientSide) {
         return true;
      } else {
         player.displayChestScreen(getInventory(world, tilePos), (double)tilePos.x(), (double)tilePos.y(), (double)tilePos.z());
         return true;
      }
   }
   
   public static Container getInventory(World world, TilePosc tilePos) {
      Container inventory = (Container)Objects.requireNonNull(world.getTileEntity(tilePos));
      return inventory;
   }
}
