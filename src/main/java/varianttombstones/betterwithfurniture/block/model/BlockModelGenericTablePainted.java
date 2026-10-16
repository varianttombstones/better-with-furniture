package varianttombstones.betterwithfurniture.block.model;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.block.model.generic.BlockModelGenericRotatable;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicBed;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.block.StaticBlockModel;
import varianttombstones.betterwithfurniture.BetterWithFurniture;

public class BlockModelGenericTablePainted<T extends BlockLogic> extends BlockModelGeneric<T> {
	public final StaticBlockModel[] colourModels = new StaticBlockModel[16];

	public BlockModelGenericTablePainted(Block block) {
		super(block, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/table"));

		for (DyeColor color : DyeColor.blockOrderedColors()) {
			this.colourModels[color.blockMeta] = BlockModelDispatcher.loadDataModel(
				BetterWithFurniture.MOD_ID + ":block/tables/%s".formatted(color.colorID)
			).asModel();
		}
	}

	public @NotNull StaticBlockModel getModelFromData(int data) {
		return this.colourModels[data & 15];
	}
}
