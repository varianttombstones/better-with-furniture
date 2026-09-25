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
}
