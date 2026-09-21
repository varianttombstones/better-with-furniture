package varianttombstones.betterwithfurniture.block.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicBed;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;

@Environment(EnvType.CLIENT)
public class BlockModelChair<T extends BlockLogic> extends BlockModelGeneric<T> {
   public BlockModelChair(Block<T> block) {
      super(block, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/chair"));
   }

   public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
      int direction = BlockLogicBed.DIRECTION.get(worldSource.getBlockData(tilePos));
      boolean var10000;
      switch (direction) {
         case 0 -> var10000 = this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 3, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
         case 1 -> var10000 = this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 2, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
         case 2 -> var10000 = this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 1, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
         default -> var10000 = this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
      }

      return var10000;
   }
}
