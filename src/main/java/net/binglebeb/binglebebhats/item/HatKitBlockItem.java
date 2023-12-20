package net.binglebeb.binglebebhats.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class HatKitBlockItem extends BlockItem {
    public HatKitBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        System.out.println("useOn method called!");
        InteractionResult result = super.useOn(context);
        if (result == InteractionResult.SUCCESS) {
            Level world = context.getLevel();
            BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
            BlockState state = world.getBlockState(pos);
            if (state.isAir()) {
                world.setBlock(pos, this.getBlock().defaultBlockState(), 11);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

}
