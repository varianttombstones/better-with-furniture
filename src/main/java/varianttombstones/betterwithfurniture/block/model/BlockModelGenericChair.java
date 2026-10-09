package varianttombstones.betterwithfurniture.block.model;

import net.minecraft.core.util.helper.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGenericRotatable;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicBed;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.useless.dragonfly.models.block.StaticBlockModel;
import varianttombstones.betterwithfurniture.BetterWithFurniture;

@Environment(EnvType.CLIENT)
public class BlockModelGenericChair<T extends BlockLogic> extends BlockModelGenericRotatable<T> {
	public BlockModelGenericChair(Block<T> block) {
		super(block, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/chair"));
	}

	public boolean renderAttached(TessellatorGeneral tessellator, WorldSource worldSource, TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
		int direction = BlockLogicBed.DIRECTION.get(worldSource.getBlockData(tilePos));
		switch (direction) {
			case 0 -> {
				return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 3, 0, (double) 0.0F, (double) 0.0F, (double) 0.0F, false, cullFaces, overrideTexture);
			}
			case 1 -> {
				return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 1, 0, (double) 0.0F, (double) 0.0F, (double) 0.0F, false, cullFaces, overrideTexture);
			}
			case 2 -> {
				return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 2, 0, (double) 0.0F, (double) 0.0F, (double) 0.0F, false, cullFaces, overrideTexture);
			}
			default -> {
				return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, (double) 0.0F, (double) 0.0F, (double) 0.0F, false, cullFaces, overrideTexture);
			}
		}
	}
}
