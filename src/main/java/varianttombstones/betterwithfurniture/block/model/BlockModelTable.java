package varianttombstones.betterwithfurniture.block.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;

/*
*
* The plan is to make these connect together in the future.
*
*/
@Environment(EnvType.CLIENT)
public class BlockModelTable<T extends BlockLogic> extends BlockModelGeneric<T> {
    public BlockModelTable(Block<T> block) {
        super(block, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/table"));
    }
}
