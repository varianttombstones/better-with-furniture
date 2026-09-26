package varianttombstones.betterwithfurniture.block.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGenericRotatable;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;

@Environment(EnvType.CLIENT)
public class BlockModelTable<T extends BlockLogic> extends BlockModelGenericRotatable<T> {
    public BlockModelTable(Block<T> block) {
        super(block, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/table"));
    }
    
    /*
    @Override
	public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
		Direction direction = BlockLogicRotatable.getDirectionFromMeta(worldSource.getBlockData(tilePos));
		switch (direction) {
			case UP -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, 1, 2, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
			case DOWN -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, -1, 2, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
			case NORTH -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
			case WEST -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, 0, 1, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
			case EAST -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, 0, 3, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
			default -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, 0, 2, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
		}
	}
    */
}
