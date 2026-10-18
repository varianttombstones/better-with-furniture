package varianttombstones.betterwithfurniture.block;

import java.util.List;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryDyeing;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryUndyeing;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import org.jetbrains.annotations.Nullable;
import turniplabs.halplibe.helper.RecipeBuilder;
import varianttombstones.betterwithfurniture.BetterWithFurniture;
import static net.minecraft.core.util.helper.DyeColor.colorFromBlockMeta;

public class ModRecipes {
    private static void registerItemGroup(String groupName, List<ItemStack> groupList)
	{
		Registries.ITEM_GROUPS.register(BetterWithFurniture.MOD_ID + ":" + groupName, groupList);
	}

	private static void registerBlockDyeRecipes(String recipeKey, String groupName, Block<?> blockDyed, @Nullable Block<?> blockUndyed, boolean useUpperMeta)
	{
		Registries.RECIPES.addCustomRecipe(
			BetterWithFurniture.MOD_ID + ":workbench/" + recipeKey + "_dyeing",
			new RecipeEntryDyeing(
				new RecipeSymbol(BetterWithFurniture.MOD_ID + ":" + groupName),
				blockDyed.getDefaultStack(), useUpperMeta, false
			)
		);

		if (blockUndyed != null)
		{
			Registries.RECIPES.addCustomRecipe(
				BetterWithFurniture.MOD_ID + ":workbench/" + recipeKey + "_undyeing",
				new RecipeEntryUndyeing(
					new RecipeSymbol(BetterWithFurniture.MOD_ID + ":" + groupName),
					blockUndyed.getDefaultStack()
				)
			);
		}
	}

    /*
	private static void registerItemDyeRecipes(String recipeKey, String groupName, Item itemDyed, boolean useWhiteMeta)
	{
		Registries.RECIPES.addCustomRecipe(
			BetterWithFurniture.MOD_ID + ":workbench/" + recipeKey + "_dyeing",
			new RecipeEntryDyeing(
				new RecipeSymbol(BetterWithFurniture.MOD_ID + ":" + groupName),
				itemDyed.getDefaultStack(), false, true
			)
        );
	}
    */

	public static void initializeNamespaces()
	{
		RecipeBuilder.initNameSpace(BetterWithFurniture.MOD_ID);
		List<ItemStack> carpets = Registries.stackListOf(ModBlocks.CARPET_BLOCK);
		List<ItemStack> chairs = Registries.stackListOf(ModBlocks.CHAIR_BLOCK);
		List<ItemStack> tables = Registries.stackListOf(ModBlocks.WOOD_TABLE_BLOCK);
		List<ItemStack> drawers = Registries.stackListOf(ModBlocks.CHEST_OF_DRAWERS_BLOCK);
		List<ItemStack> cabinets = Registries.stackListOf(ModBlocks.CABINET_BLOCK);
		List<ItemStack> coffeeTables = Registries.stackListOf(ModBlocks.COFFEE_TABLE_BLOCK);

		for (DyeColor c : DyeColor.values())
		{
			carpets.add(new ItemStack(ModBlocks.CARPET_BLOCK, 1, c.blockMeta));
			chairs.add(new ItemStack(ModBlocks.CHAIR_PAINTED, 1, c.blockMeta << 3));
			tables.add(new ItemStack(ModBlocks.TABLE_PAINTED, 1, c.blockMeta << 3));
			drawers.add(new ItemStack(ModBlocks.DRAWER_PAINTED, 1, c.blockMeta << 3));
			cabinets.add(new ItemStack(ModBlocks.CABINET_PAINTED, 1, c.blockMeta << 3));
		}

		registerItemGroup("carpets", carpets);
		registerItemGroup("chairs", chairs);
		registerItemGroup("tables", tables);
		registerItemGroup("drawers", drawers);
		registerItemGroup("cabinets", cabinets);
		registerItemGroup("coffeetables", coffeeTables);
	}

	public static void initializeRecipes()
	{
		RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
			.setShape("xy","xy")
			.addInput('x', Blocks.PLANKS_OAK)
			.addInput('y', "minecraft:trapdoor_planks")
			.create("drawer", new ItemStack(ModBlocks.CHEST_OF_DRAWERS_BLOCK, 3));

		RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
			.setShape("xy")
			.addInput('x', "minecraft:logs")
			.addInput('y', "minecraft:trapdoor_planks")
			.create("cabinet", new ItemStack(ModBlocks.CABINET_BLOCK, 3));

		RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
			.setShape("x#","xx", "ss")
			.addInput('x', Blocks.PLANKS_OAK)
			.addInput('s', Items.STICK)
			.create("chair", new ItemStack(ModBlocks.CHAIR_BLOCK, 2));

		RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
			.setShape("xxx", "s#s", "s#s")
			.addInput('x', Blocks.PLANKS_OAK)
			.addInput('s', Items.STICK)
			.create("table", new ItemStack(ModBlocks.WOOD_TABLE_BLOCK, 2));

		RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
			.setShape("xxx", "s#s")
			.addInput('x', Blocks.PLANKS_OAK)
			.addInput('s', Items.STICK)
			.create("coffeetable", new ItemStack(ModBlocks.COFFEE_TABLE_BLOCK, 2));

		RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
			.setShape("xxx", "s#s", "sss")
			.addInput('x', "minecraft:planks")
			.addInput('s', Items.INGOT_IRON)
			.create("trashcan", new ItemStack(ModBlocks.TRASH_CAN_BLOCK, 1));

		// Dyed items crafting
		for (DyeColor c : DyeColor.itemOrderedColors())
		{
			RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
				.setShape("xxx")
				.addInput('x', Blocks.WOOL, c.itemMeta)
				.create(colorFromBlockMeta(c.itemMeta).colorID + "_carpet", new ItemStack(ModBlocks.CARPET_BLOCK, 6, c.itemMeta));

			RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
				.setShape("x#","xx", "ss")
				.addInput('x', Blocks.PLANKS_OAK_PAINTED, c.itemMeta)
				.addInput('s', Items.STICK)
				.create(colorFromBlockMeta(c.itemMeta).colorID + "_chair", new ItemStack(ModBlocks.CHAIR_PAINTED, 2, c.itemMeta));

			RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
				.setShape("xxx", "s#s", "s#s")
				.addInput('x', Blocks.PLANKS_OAK_PAINTED, c.itemMeta)
				.addInput('s', Items.STICK)
				.create(colorFromBlockMeta(c.itemMeta).colorID + "_table", new ItemStack(ModBlocks.TABLE_PAINTED, 2, c.itemMeta));

			RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
				.setShape("xxx", "s#s")
				.addInput('x', Blocks.PLANKS_OAK_PAINTED, c.itemMeta)
				.addInput('s', Items.STICK)
				.create(colorFromBlockMeta(c.itemMeta).colorID + "_coffeetable", new ItemStack(ModBlocks.COFFEE_TABLE_PAINTED, 2, c.itemMeta));

		}

		// Register dyeing recipes
		registerBlockDyeRecipes("carpet", "carpets", ModBlocks.CARPET_BLOCK, null, false);
		registerBlockDyeRecipes("chair", "chairs", ModBlocks.CHAIR_PAINTED, ModBlocks.CHAIR_BLOCK, false);
		registerBlockDyeRecipes("table", "tables", ModBlocks.TABLE_PAINTED, ModBlocks.WOOD_TABLE_BLOCK, false);
		registerBlockDyeRecipes("drawer", "drawers", ModBlocks.DRAWER_PAINTED, ModBlocks.CHEST_OF_DRAWERS_BLOCK, false);
		registerBlockDyeRecipes("cabinet", "cabinets", ModBlocks.CABINET_PAINTED, ModBlocks.CABINET_BLOCK, false);
		registerBlockDyeRecipes("coffeetable", "coffeetables", ModBlocks.COFFEE_TABLE_PAINTED, ModBlocks.COFFEE_TABLE_BLOCK, false);
	}
}
