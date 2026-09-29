package varianttombstones.betterwithfurniture.block.tileentity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class TileEntityTrashCan extends TileEntity {
    private @Nullable ItemStack heldStack = null;

    public TileEntityTrashCan() {
    }

    public void setItem(@Nullable ItemStack stack) {
        this.heldStack = stack;
        this.setChanged();
    }

    // Bugged
    public void readAdditionalData(@NotNull CompoundTag compoundTag) {
        this.heldStack = ItemStack.readItemStackFromNbt(compoundTag.getCompound("heldStack"));
    }

    public void writeAdditionalData(@NotNull CompoundTag compoundTag) {
        if (heldStack != null)
        {
            compoundTag.putCompound("heldStack", heldStack.writeToNBT(new CompoundTag()));
        }
        else
        {
            compoundTag.putCompound("heldStack", new CompoundTag());
        }
    }

    public void dropContents(World world, int x, int y, int z) {
        super.dropContents(world, x, y, z);

        if (heldStack != null) {
            EntityItem item = world.dropItem(this.tilePos, heldStack);
            item.xd *= (double)0.5F;
            item.yd *= (double)1.5F;
            item.zd *= (double)0.5F;
            item.pickupDelay = 0;
            setItem(null);
        }
    }
}
