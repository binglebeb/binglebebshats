package net.binglebeb.binglebebhats.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class HatKitBlockItem extends BlockItem {
    public HatKitBlockItem(Block block, Properties properties) {
        super(block, properties);
    }



    @Override
    public InteractionResult place(BlockPlaceContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = world.getBlockState(pos);

        if (state.canBeReplaced(context)) {
            BlockState placementState = this.getBlock().getStateForPlacement(context);
            if (placementState != null) {
                world.setBlock(pos, placementState, 11);
                world.playSound(player, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

                if (player != null && !player.isCreative()) {
                    context.getItemInHand().shrink(1);
                }
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }
}
