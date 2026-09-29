package varianttombstones.betterwithfurniture.block;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelRotatable;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.block.model.generic.BlockModelGenericRotatable;
import net.minecraft.core.util.helper.Side;
import varianttombstones.betterwithfurniture.block.model.BlockModelChair;
import varianttombstones.betterwithfurniture.block.model.BlockModelGenericCarpet;
import varianttombstones.betterwithfurniture.block.model.BlockModelGenericDrawers;

public class ModBlockRenderer {
    public static void RegisterRenderers(BlockModelDispatcher dispatcher)
    {
        dispatcher.addDispatch(new BlockModelChair<>(ModBlocks.CHAIR_BLOCK));

        dispatcher.addDispatch(new BlockModelGenericDrawers<>(ModBlocks.CHEST_OF_DRAWERS_BLOCK));

        dispatcher.addDispatch(new BlockModelGeneric<>(ModBlocks.WOOD_TABLE_BLOCK, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/table")));
        dispatcher.addDispatch(new BlockModelGeneric<>(ModBlocks.COFFEE_TABLE_BLOCK, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/coffee_table")));
        dispatcher.addDispatch(new BlockModelGenericCarpet<>(ModBlocks.CARPET_BLOCK));
        dispatcher.addDispatch(new BlockModelGenericRotatable<>(ModBlocks.TRASH_CAN_BLOCK, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/trashcan")));
    }
}
