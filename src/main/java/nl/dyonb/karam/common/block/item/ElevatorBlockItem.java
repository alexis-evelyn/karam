package nl.dyonb.karam.common.block.item;

import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nl.dyonb.karam.common.block.entity.ElevatorBlockEntity;

public class ElevatorBlockItem extends BlockItem {
    public ElevatorBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    // Put NBT back into block
    @Override
    protected boolean postPlacement(BlockPos pos, World world, PlayerEntity player, ItemStack stack, BlockState state) {
        ElevatorBlockEntity blockEntity = (ElevatorBlockEntity) world.getBlockEntity(pos);
        CompoundTag nbt = stack.getOrCreateTag();

        int color = 16777215;
        if (nbt.contains("color", NbtType.INT)) // nbt.contains(String, int) allows you to check for a subtag of a specific type, very cool
            color = nbt.getInt("color");
        else if (nbt.contains("color", NbtType.STRING)) {
            try {
                color = Integer.parseInt(nbt.getString("color"));
            } catch (NumberFormatException ignored) { }
        }
        blockEntity.setColor(color);

        blockEntity.markDirty();
        if (!world.isClient()) {
            blockEntity.sync();
        }

        return super.postPlacement(pos, world, player, stack, state);
    }
}