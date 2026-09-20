package varianttombstones.betterwithfurniture.block.logic;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicSeat;
import net.minecraft.core.block.entity.TileEntitySeat;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;

public class BlockLogicChair extends BlockLogicSeat {
    public BlockLogicChair(Block<?> block) {
        super(block);

        // oh cruel world is it wrong to set this twice technically?
        this.setBlockBounds((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.435F, (double)1.0F);
        block.withEntity(() -> new TileEntitySeat(block));
    }


    @Override
    public boolean onInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Player player, @Nullable Side side, double xHit, double yHit) {
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
}