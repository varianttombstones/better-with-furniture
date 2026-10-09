package varianttombstones.betterwithfurniture.block.model;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.useless.dragonfly.models.block.StaticBlockModel;
import varianttombstones.betterwithfurniture.BetterWithFurniture;

public class BlockModelGenericChairPainted<T extends BlockLogic> extends BlockModelGenericChair{
	public final StaticBlockModel[] colourModels = new StaticBlockModel[16];

	public BlockModelGenericChairPainted(Block block) {
		super(block);

		for (DyeColor color : DyeColor.blockOrderedColors()) {
			this.colourModels[color.blockMeta] = BlockModelDispatcher.loadDataModel(
				BetterWithFurniture.MOD_ID + ":block/chairs/%s".formatted(color.colorID)
			).asModel();
		}
	}

	public @NotNull StaticBlockModel getModelFromData(int data) {
		return this.colourModels[data & 15];
	}
}
