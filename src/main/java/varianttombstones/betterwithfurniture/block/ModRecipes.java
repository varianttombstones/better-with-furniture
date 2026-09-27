package varianttombstones.betterwithfurniture.block;

import java.util.List;

import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryDyeing;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DyeColor;
import turniplabs.halplibe.helper.RecipeBuilder;
import varianttombstones.betterwithfurniture.BetterWithFurniture;

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
		registerBlockDyeRecipes("carpet", "carpets", ModBlocks.CARPET_BLOCK, false);
        /* RecipeBuilder.Shaped(BetterWithFurniture.MOD_ID)
			.setShape("xx","xx")
			.addInput('x', Blocks.PLANKS_OAK)
			.create("workbench", new ItemStack(Blocks.WORKBENCH, 1));
            */
	}
}
