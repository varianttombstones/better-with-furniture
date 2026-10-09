package varianttombstones.betterwithfurniture.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicLayerSnow;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.BlockLogicWool;
import net.minecraft.core.block.entity.TileEntityDispatcher;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.block.ItemBlockPainted;
import net.minecraft.core.sound.BlockSounds;
import net.minecraft.core.util.collection.NamespaceID;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import varianttombstones.betterwithfurniture.BetterWithFurniture;
import varianttombstones.betterwithfurniture.block.logic.*;
import varianttombstones.betterwithfurniture.block.tileentity.TileEntityCabinet;
import varianttombstones.betterwithfurniture.block.tileentity.TileEntityDrawers;
import varianttombstones.betterwithfurniture.block.tileentity.TileEntityTrashCan;

public class ModBlocks {
    private static int BLOCK_ID = 2344; // End at 2499?

    public static Block<?> CHAIR_BLOCK;
    public static Block<?> CHEST_OF_DRAWERS_BLOCK;
    public static Block<?> CABINET_BLOCK;
    public static Block<?> WOOD_TABLE_BLOCK;
    public static Block<?> COFFEE_TABLE_BLOCK;
    public static Block<?> CARPET_BLOCK;
    public static Block<?> TRASH_CAN_BLOCK;
	public static Block<?> CHAIR_PAINTED;

    public static void InitBlocks()
    {
		TileEntityDispatcher.addMapping(TileEntityDrawers.class, new NamespaceID(BetterWithFurniture.MOD_ID, "drawers"));
		TileEntityDispatcher.addMapping(TileEntityCabinet.class, new NamespaceID(BetterWithFurniture.MOD_ID, "cabinet"));
		TileEntityDispatcher.addMapping(TileEntityTrashCan.class, new NamespaceID(BetterWithFurniture.MOD_ID, "trashcan"));

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
            .build("drawers", newBlockID(), b -> new BlockLogicDrawers(b, Materials.WOOD, TileEntityDrawers::new));

        CABINET_BLOCK = new BlockBuilder(BetterWithFurniture.MOD_ID)
            .setResistance(2.0f)
            .setHardness(.4f)
            .setBlockSound(BlockSounds.WOOD)
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.STORAGE))
            .build("cabinet", newBlockID(), b -> new BlockLogicDrawers(b, Materials.WOOD, TileEntityCabinet::new));

        WOOD_TABLE_BLOCK = new BlockBuilder(BetterWithFurniture.MOD_ID)
            .setResistance(2.0f)
            .setHardness(.2f)
            .setBlockSound(BlockSounds.WOOD)
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.PLACEABLES))
            .build("woodtable", newBlockID(), b -> new BlockLogicTable(b, false));

        COFFEE_TABLE_BLOCK = new BlockBuilder(BetterWithFurniture.MOD_ID)
            .setResistance(2.0f)
            .setHardness(.2f)
            .setBlockSound(BlockSounds.WOOD)
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.PLACEABLES))
            .build("coffeetable", newBlockID(), b -> new BlockLogicTable(b, true));

        CARPET_BLOCK = new BlockBuilder(BetterWithFurniture.MOD_ID)
            .setResistance(0.5f)
            .setHardness(.05f)
            .setBlockSound(BlockSounds.CLOTH)
            .setTags(BlockTags.MINEABLE_BY_SHEARS)
            .build("carpet", newBlockID(), b -> new BlockLogicCarpet(b));

        TRASH_CAN_BLOCK = new BlockBuilder(BetterWithFurniture.MOD_ID)
            .setResistance(4f)
            .setHardness(1f)
            .setBlockSound(BlockSounds.METAL)
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.PLACEABLES))
            .build("trashcan", newBlockID(), b -> new BlockLogicTrashCan(b));

		CHAIR_PAINTED = new BlockBuilder(BetterWithFurniture.MOD_ID)
			.setResistance(2.0f)
			.setHardness(.2f)
			.setBlockSound(BlockSounds.WOOD)
			.setTags(BlockTags.MINEABLE_BY_AXE)
			.build("chairpainted", newBlockID(), b -> new BlockLogicChairPainted(b));
    }

    public static int newBlockID() {
        BLOCK_ID = BLOCK_ID + 1;
        return BLOCK_ID;
    }
}
