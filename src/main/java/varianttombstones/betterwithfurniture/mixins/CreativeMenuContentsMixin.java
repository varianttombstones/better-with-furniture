package varianttombstones.betterwithfurniture.mixins;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.CreativeMenuContents;
import net.minecraft.core.util.helper.DyeColor;
import varianttombstones.betterwithfurniture.block.ModBlocks;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(CreativeMenuContents.class)
public class CreativeMenuContentsMixin {
	@Inject(method = "addPainted", at = @At("TAIL"))
	private static void addDyablesItems(@NotNull List<ItemStack> list, DyeColor color, CallbackInfo ci) {
		list.add(new ItemStack(ModBlocks.CARPET_BLOCK, 1, color.blockMeta));
	}
}