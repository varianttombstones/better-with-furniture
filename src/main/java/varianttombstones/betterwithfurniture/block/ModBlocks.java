package varianttombstones.betterwithfurniture.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicChest;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import varianttombstones.betterwithfurniture.BetterWithFurniture;
import varianttombstones.betterwithfurniture.block.logic.BlockLogicChair;

public class ModBlocks {
    private static int BLOCK_ID = 2344;

    public static Block<?> CHAIR_BLOCK;
    public static Block<?> CHEST_OF_DRAWERS_BLOCK;

    public static void InitBlocks()
    {
        CHAIR_BLOCK = new BlockBuilder(BetterWithFurniture.MOD_ID)
            .setResistance(2.0f)
            .setHardness(.2f)
            .setBlockSound(BlockSounds.WOOD)
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.PLACEABLES))
            .build("chair", newBlockID(), b -> new BlockLogicChair(b));

        CHEST_OF_DRAWERS_BLOCK = new BlockBuilder(BetterWithFurniture.MOD_ID)
            .setResistance(2.0f)
            .setHardness(.4f)
            .setBlockSound(BlockSounds.WOOD)
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.STORAGE))
            .build("drawers", newBlockID(), b -> new BlockLogicChest(b, Materials.WOOD));
    }
    
    public static int newBlockID() {
        BLOCK_ID = BLOCK_ID + 1;
        return BLOCK_ID;
    }
}
