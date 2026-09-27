package varianttombstones.betterwithfurniture.block.logic;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.entity.TileEntitySeat;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.support.ISupport;
import net.minecraft.core.block.support.PartialSupport;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;

public class BlockLogicChair extends BlockLogicRotatable {
    public BlockLogicChair(Block<?> block) {
      super(block, Materials.WOOD);
      this.setBlockBounds((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.5625F, (double)1.0F);
      block.withEntity(() -> new TileEntitySeat(block));
   }

   public boolean onInteracted(World world, TilePosc tilePos, Player player, @Nullable Side side, double xHit, double yHit) {
      if (world.shouldBlockSuffocateEntities(tilePos.up(new TilePos()), player.getClass())) {
         return false;
      } else {
         if (!world.isClientSide) {
            TileEntitySeat tileEntity = (TileEntitySeat)world.getTileEntity(tilePos);
            if (tileEntity != null && tileEntity.getPassenger() == null) {
               player.startRiding(tileEntity);
            }
         }

         return true;
      }
   }

   public void onRemoved(World world, TilePosc tilePos, int data) {
      if (!world.isClientSide) {
         TileEntitySeat tileEntity = (TileEntitySeat)world.getTileEntity(tilePos);
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

   public ISupport getSupport(World world, TilePosc tilePos, Side side) {
      return PartialSupport.INSTANCE;
   }
}