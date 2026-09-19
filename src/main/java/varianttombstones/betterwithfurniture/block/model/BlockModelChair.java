package varianttombstones.betterwithfurniture.block.model;

import net.minecraft.client.render.block.model.BlockModel;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;

public abstract class BlockModelChair<T extends BlockLogic> extends BlockModel<T> {
    public BlockModelChair(Block<T> block) {
      super(block);
    }
}
