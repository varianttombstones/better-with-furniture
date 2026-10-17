package varianttombstones.betterwithfurniture.block.model;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.block.model.generic.BlockModelGenericRotatable;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicBed;
import net.minecraft.core.block.BlockLogicChest;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.block.StaticBlockModel;
import varianttombstones.betterwithfurniture.BetterWithFurniture;
import varianttombstones.betterwithfurniture.block.logic.BlockLogicChairPainted;

public class BlockModelGenericChairPainted<T extends BlockLogic> extends BlockModelGeneric<T> {
	public final StaticBlockModel[] colourModels = new StaticBlockModel[16];

	public BlockModelGenericChairPainted(Block block) {
		super(block, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/chair"));

		for (DyeColor color : DyeColor.blockOrderedColors()) {
			this.colourModels[color.blockMeta] = BlockModelDispatcher.loadDataModel(
				BetterWithFurniture.MOD_ID + ":block/chairs/%s".formatted(color.colorID)
			).asModel();
		}
	}

	public boolean renderAttached(TessellatorGeneral tessellator, WorldSource worldSource, TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
		int direction = BlockLogicBed.DIRECTION.get(worldSource.getBlockData(tilePos) & 7);
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

	/*
	public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
		Direction direction = BlockLogicChest.getDirectionFromMeta(worldSource.getBlockData(tilePos));
		switch (direction) {
			case NORTH -> {
				return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
			}
			case WEST -> {
				return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 1, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
			}
			case EAST -> {
				return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 3, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
			}
			case SOUTH -> {
				return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 2, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
			}
			default -> {
				return this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
			}
		}
	 }
	 */

	public @NotNull StaticBlockModel getModel(@NotNull WorldSource source, @NotNull TilePosc tilePosc) {
		int data = source.getBlockData(tilePosc);
		int color = (data >> 3) & 15;
		return colourModels[color];
	}

	public @NotNull StaticBlockModel getModelFromData(int data) {
		int color = data & 15;
		return this.colourModels[color];
	}
}
