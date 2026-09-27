package varianttombstones.betterwithfurniture.block.logic;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.IPainted;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.support.FullSupport;
import net.minecraft.core.block.support.ISupport;
import net.minecraft.core.block.support.ISupportable;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;

public class BlockLogicCarpet extends BlockLogic implements ISupportable, IPainted, BlockLogic.MatcherDataEquivalency {
    public BlockLogicCarpet(@NotNull Block<?> block) {
        super(block, Materials.CLOTH);
        this.setBlockBounds((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.0625F, (double)1.0F);
    }

    public boolean isCubeShaped() {
        return false;
    }

    public boolean isSolidRender() {
        return false;
    }

    protected @NotNull AABBd getBoundsFromStateMut(@NotNull WorldSource source, @NotNull TilePosc tilePos) {
        int data = Math.min(source.getBlockData(tilePos) & 7, 6);
        float f = (float)(2 * (1 + data)) / 16.0F;
        return new AABBd((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)f, (double)1.0F);
    }

    public @Nullable AABBdc getCollisionAABB(@NotNull WorldSource source, @NotNull TilePosc tilePos) {
        AABBd aabb = this.getBoundsFromStateMut(source, tilePos);
        aabb.maxY = (double)0; // We wanna be walking through it.
        return aabb.translate((double)tilePos.x(), (double)tilePos.y(), (double)tilePos.z());
    }

    public boolean canPlaceAt(@NotNull World world, @NotNull TilePosc tilePos) {
        return this.isSupported(world, tilePos, Side.BOTTOM);
    }

    public void onNeighborChanged(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Block<?> block) {
        if (!this.canPlaceAt(world, tilePos)) {
            this.dropWithCause(world, EnumDropCause.WORLD, tilePos, world.getBlockData(tilePos), (TileEntity)null, (Player)null);
            world.setBlockTypeNotify(tilePos, Blocks.AIR);
        }
    }

    public @NotNull ISupport getSupportConstraint(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side) {
        return FullSupport.INSTANCE;
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
}