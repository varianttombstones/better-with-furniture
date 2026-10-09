package varianttombstones.betterwithfurniture.block;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.block.model.generic.BlockModelGenericRotatable;
import varianttombstones.betterwithfurniture.block.model.BlockModelGenericChair;
import varianttombstones.betterwithfurniture.block.model.BlockModelGenericCarpet;
import varianttombstones.betterwithfurniture.block.model.BlockModelGenericChairPainted;
import varianttombstones.betterwithfurniture.block.model.BlockModelPlayerFacing;

public class ModBlockRenderer {
    public static void RegisterRenderers(BlockModelDispatcher dispatcher)
    {
        dispatcher.addDispatch(new BlockModelGenericChair<>(ModBlocks.CHAIR_BLOCK));
		dispatcher.addDispatch(new BlockModelGenericChairPainted<>(ModBlocks.CHAIR_PAINTED));

        dispatcher.addDispatch(new BlockModelPlayerFacing<>(ModBlocks.CHEST_OF_DRAWERS_BLOCK, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/drawers")));
        dispatcher.addDispatch(new BlockModelPlayerFacing<>(ModBlocks.CABINET_BLOCK, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/cabinet")));

        dispatcher.addDispatch(new BlockModelGeneric<>(ModBlocks.WOOD_TABLE_BLOCK, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/table")));
        dispatcher.addDispatch(new BlockModelGeneric<>(ModBlocks.COFFEE_TABLE_BLOCK, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/coffee_table")));
        dispatcher.addDispatch(new BlockModelGenericCarpet<>(ModBlocks.CARPET_BLOCK));
        dispatcher.addDispatch(new BlockModelGenericRotatable<>(ModBlocks.TRASH_CAN_BLOCK, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/trashcan")));
    }
}
