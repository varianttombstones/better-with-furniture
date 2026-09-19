package varianttombstones.betterwithfurniture;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;
import varianttombstones.betterwithfurniture.block.ModBlocks;

public class BetterWithFurniture implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("betterwithfurniture", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		LOGGER.info("Better with Furniture initialized.");
	}

	public void beforeGameStart() {
		ModBlocks.InitBlocks();
	}

	public void afterGameStart() {

	}
}
