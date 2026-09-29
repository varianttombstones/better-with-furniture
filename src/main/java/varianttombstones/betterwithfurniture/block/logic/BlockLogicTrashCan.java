package varianttombstones.betterwithfurniture.block.logic;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import varianttombstones.betterwithfurniture.block.tileentity.TileEntityDrawers;
import varianttombstones.betterwithfurniture.block.tileentity.TileEntityTrashCan;

public class BlockLogicTrashCan extends BlockLogicRotatable {
    public BlockLogicTrashCan(Block<?> block) {
        super(block, Materials.METAL);
        block.withEntity(TileEntityTrashCan::new);
    }

    public boolean onInteracted(World world, TilePosc tilePos, Player player, @Nullable Side side, double xHit, double yHit) {
        if (world.isClientSide) {
            return true;
        } else {
            TileEntity tileEntity = world.getTileEntity(tilePos);
            @Nullable TileEntityTrashCan trashCanEntity = null;

            if (tileEntity instanceof TileEntityTrashCan)
            {
                trashCanEntity = (TileEntityTrashCan)tileEntity;
            }

            if (trashCanEntity == null)
            {
                return false;
            }
            
            int currentSlot = player.inventory.getCurrentSlot();
            ItemStack currentPlayerHeldItem = player.inventory.getCurrentItem();

            if (currentPlayerHeldItem != null && currentPlayerHeldItem.stackSize > 0)
            {
                trashCanEntity.setItem(new ItemStack(currentPlayerHeldItem.getItem(), currentPlayerHeldItem.stackSize));
                player.inventory.removeItem(currentSlot, currentPlayerHeldItem.stackSize);
            }
            else
            {
                trashCanEntity.dropContents(world, tilePos.x(), tilePos.y(), tilePos.z());
            }
            return true;
        }
    }

    public boolean isCubeShaped() {
        return false;
    }

    public boolean isSolidRender() {
        return false;
    }
}
