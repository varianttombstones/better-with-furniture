package varianttombstones.betterwithfurniture.block;

import java.util.List;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryDyeing;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import turniplabs.halplibe.helper.RecipeBuilder;
import varianttombstones.betterwithfurniture.BetterWithFurniture;
import static net.minecraft.core.util.helper.DyeColor.colorFromBlockMeta;

public class ModRecipes {
    private static void registerItemGroup(String groupName, List<ItemStack> groupList)
	{
		Registries.ITEM_GROUPS.register(BetterWithFurniture.MOD_ID + ":" + groupName, groupList);
	}

	private static void registerBlockDyeRecipes(String recipeKey, String groupName, Block<?> blockDyed, boolean useUpperMeta)
	{
		Registries.RECIPES.addCustomRecipe(
			BetterWithFurniture.MOD_ID + ":workbench/" + recipeKey + "_dyeing",
			new RecipeEntryDyeing(
				new RecipeSymbol(BetterWithFurniture.MOD_ID + ":" + groupName),
				blockDyed.getDefaultStack(), useUpperMeta, false
			)
		);
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

		for (DyeColor c : DyeColor.values())
		{
			carpets.add(new ItemStack(ModBlocks.CARPET_BLOCK, 1, c.blockMeta));
		}

		registerItemGroup("carpets", carpets);
	}

	public static void initializeRecipes()
	{
		RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
			.setShape("xx","x#", "xx")
			.addInput('x', Blocks.PLANKS_OAK)
			.create("drawer", new ItemStack(ModBlocks.CHEST_OF_DRAWERS_BLOCK, 1));
		
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
			.addInput('x', Blocks.PLANKS_OAK)
			.addInput('s', Items.INGOT_IRON)
			.create("trashcan", new ItemStack(ModBlocks.TRASH_CAN_BLOCK, 1));
		
		// Carpet crafting
		for (DyeColor c : DyeColor.itemOrderedColors())
		{
			RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
				.setShape("xxx")
				.addInput('x', Blocks.WOOL, c.itemMeta)
				.create(colorFromBlockMeta(c.itemMeta).colorID + "_workbench", new ItemStack(ModBlocks.CARPET_BLOCK, 6, c.itemMeta));
		}

		// Register the carpets for dying
		registerBlockDyeRecipes("carpet", "carpets", ModBlocks.CARPET_BLOCK, false);
	}
}
