package varianttombstones.betterwithfurniture.block.model;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGenericRotatable;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.block.StaticBlockModel;
import varianttombstones.betterwithfurniture.BetterWithFurniture;
import varianttombstones.betterwithfurniture.block.logic.BlockLogicRotatablePainted;

public class BlockModelGenericRotatablePainted<T extends BlockLogicRotatablePainted> extends BlockModelGenericRotatable<T> {
	public final StaticBlockModel[] colorModels = new StaticBlockModel[16];

	public BlockModelGenericRotatablePainted(Block block, String baseModel, String colorFolder) {
		super(block, BlockModelDispatcher.loadDataModel(baseModel));

		for (DyeColor color : DyeColor.blockOrderedColors()) {
			this.colorModels[color.blockMeta] = BlockModelDispatcher.loadDataModel(
				BetterWithFurniture.MOD_ID + ":" + colorFolder + "/%s".formatted(color.colorID)
			).asModel();
		}
	}

	public @NotNull StaticBlockModel getModel(@NotNull WorldSource source, @NotNull TilePosc tilePosc) {
		int data = source.getBlockData(tilePosc);
		int color = (data >> 3) & 15;
		return colorModels[color];
	}

	public @NotNull StaticBlockModel getModelFromData(int data) {
		int color = data & 15;
		return this.colorModels[color];
	}

	@Override
	public @Nullable IconCoordinate getParticleTexture(@NotNull Side side, int meta) {
		return this.getModelFromData((meta >> 3) & 15).getParticle(side);
	}
}
