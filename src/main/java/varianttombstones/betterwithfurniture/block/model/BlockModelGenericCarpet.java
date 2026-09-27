package varianttombstones.betterwithfurniture.block.model;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.useless.dragonfly.data.block.BlockModelData;
import org.useless.dragonfly.models.block.StaticBlockModel;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.DyeColor;
import varianttombstones.betterwithfurniture.BetterWithFurniture;

@Environment(EnvType.CLIENT)
public class BlockModelGenericCarpet<T extends BlockLogic> extends BlockModelGeneric<T> {
   public final StaticBlockModel[] models = new StaticBlockModel[16];

   public BlockModelGenericCarpet(@NotNull Block<T> block) {
      super(block, BlockModelDispatcher.loadDataModel("betterwithfurniture:block/carpet"));

      for(DyeColor color : DyeColor.blockOrderedColors()) {
         this.models[color.blockMeta] = BlockModelDispatcher.loadDataModel(
            BetterWithFurniture.MOD_ID + ":block/carpets/%s".formatted(color.colorID)
         ).asModel();
      }
   }

   public @NotNull StaticBlockModel getModelFromData(int data) {
      return this.models[data & 15];
   }
}
