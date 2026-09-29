package varianttombstones.betterwithfurniture.block.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.block.model.generic.BlockModelGenericChest;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicChest;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;

@Environment(EnvType.CLIENT)
public class BlockModelGenericDrawers<T extends BlockLogic> extends BlockModelGeneric<T> {
   public BlockModelGenericDrawers(Block<T> block) {
      super(block, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/drawers"));
   }
   
   public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
      Direction direction = BlockLogicRotatable.getDirectionFromMeta(worldSource.getBlockData(tilePos));
      switch (direction) {
         case UP -> {
            return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 1, 2, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
         }
         case DOWN -> {
            return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, -1, 2, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
         }
         case NORTH -> {
            return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
         }
         case SOUTH -> {
            return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 2, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
         }
         case WEST -> {
            return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 1, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
         }
         case EAST -> {
            return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 3, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
         }
         default -> {
            return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 2, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
         }
      }
   }
}