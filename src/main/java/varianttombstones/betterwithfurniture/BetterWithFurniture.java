package varianttombstones.betterwithfurniture;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.block.model.BlockModelDispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;
import varianttombstones.betterwithfurniture.block.ModBlockRenderer;
import varianttombstones.betterwithfurniture.block.ModBlocks;
import varianttombstones.betterwithfurniture.block.ModRecipes;

public class BetterWithFurniture implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("betterwithfurniture", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		CommonEvents.RECIPES_NAMESPACE_INIT.listen(Key.of(MOD_ID), this::initNamespaces);
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), this::onRecipesReady);
		if (HalpLibe.isClient)
		{
			ClientEvents.BLOCK_MODEL_RELOAD.listen(Key.of(MOD_ID),(t)->ModBlockRenderer.RegisterRenderers(BlockModelDispatcher.getInstance()));
		}
		
		LOGGER.info("Better with Furniture initialized.");
	}

	public void beforeGameStart() {
		ModBlocks.InitBlocks();
	}

	public void afterGameStart() {

	}

	public void onRecipesReady() {
		ModRecipes.initializeRecipes();
	}

	public void initNamespaces() {
		ModRecipes.initializeNamespaces();
	}
}
