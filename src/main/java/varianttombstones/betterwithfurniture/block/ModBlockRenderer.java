package varianttombstones.betterwithfurniture.block;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelRotatable;
import net.minecraft.core.util.helper.Side;
import varianttombstones.betterwithfurniture.block.model.BlockModelChair;

public class ModBlockRenderer {
    public static void RegisterRenderers(BlockModelDispatcher dispatcher)
    {
        dispatcher.addDispatch(new BlockModelChair<>(ModBlocks.CHAIR_BLOCK));

        dispatcher.addDispatch(new BlockModelRotatable<>(ModBlocks.CHEST_OF_DRAWERS_BLOCK)
        .setTex("betterwithfurniture:block/dresser_front", Side.EAST)
        .setTex("minecraft:block/planks/oak", Side.TOP)
        .setTex("minecraft:block/log/oak_side", Side.NORTH)
        .setTex("minecraft:block/log/oak_side", Side.SOUTH)
        .setTex("minecraft:block/log/oak_side", Side.WEST)
        .setTex("minecraft:block/planks/oak", Side.BOTTOM)
        );
    }
}
