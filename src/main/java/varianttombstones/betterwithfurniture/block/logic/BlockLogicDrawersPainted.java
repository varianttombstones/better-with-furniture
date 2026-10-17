package varianttombstones.betterwithfurniture.block.logic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

public class BlockLogicDrawersPainted extends BlockLogicRotatablePainted {
	public BlockLogicDrawersPainted(@NotNull Block<?> block, @NotNull Material material,
									@NotNull Supplier<TileEntity> tileEntity) {
		super(block, material);
		block.withEntity(tileEntity);
	}

	public boolean onInteracted(World world, TilePosc tilePos, Player player, @Nullable Side side, double xHit, double yHit) {
		if (!world.isClientSide) {
			player.displayChestScreen(getInventory(world, tilePos), (double) tilePos.x(), (double) tilePos.y(), (double) tilePos.z());
		}
		return true;
	}

	public static Container getInventory(World world, TilePosc tilePos) {
		return (Container) Objects.requireNonNull(world.getTileEntity(tilePos));
	}
}
