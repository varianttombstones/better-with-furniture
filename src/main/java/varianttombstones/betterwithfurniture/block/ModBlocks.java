package varianttombstones.betterwithfurniture.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicSeat;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import varianttombstones.betterwithfurniture.BetterWithFurniture;

public class ModBlocks {
    private static int BLOCK_ID = 2344;

    public static Block<?> CHAIR_BLOCK;

    public static void InitBlocks()
    {
        CHAIR_BLOCK = new BlockBuilder(BetterWithFurniture.MOD_ID)
            .setResistance(2.0f)
            .setHardness(.2f)
            .setBlockSound(BlockSounds.WOOD)
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.PLACEABLES))
            .build("chair", newBlockID(), b -> new BlockLogicSeat(b));
    }
    
    public static int newBlockID() {
        BLOCK_ID = BLOCK_ID + 1;
        return BLOCK_ID;
    }
}
