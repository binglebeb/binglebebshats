package net.binglebeb.binglebebhats.block;

import net.binglebeb.binglebebhats.item.ModItems;
import net.binglebeb.binglebebhats.particle.ModParticles;
import net.binglebeb.binglebebhats.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HatKitBlock extends Block {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    public HatKitBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);

        if (facing == Direction.NORTH) {
            return Shapes.create(0.0625, 0.0, 0.1875, 0.9375, 0.5, 0.8125);
        } else if (facing == Direction.EAST) {
            return Shapes.create(0.1875, 0.0, 0.0625, 0.8125, 0.5, 0.9375);
        } else if (facing == Direction.SOUTH) {
            return Shapes.create(0.0625, 0.0, 0.1875, 0.9375, 0.5, 0.8125);
        } else if (facing == Direction.WEST) {
            return Shapes.create(0.1875, 0.0, 0.0625, 0.8125, 0.5, 0.9375);
        }

        // Default shape if no specific direction is matched
        return Shapes.create(0.0625, 0.0, 0.1875, 0.9375, 0.5, 0.8125);
    }


    @Override //this says that this block CAN have rotations based on the direction that the player is facing.
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }


    @Override //this identifies the direction of the block when the player places it. if its placed up or down, this says to ignore it and place it whatever direction theyre facing horizontally. otherwise itll place the block in the direction opposite of the face.
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();
        Direction playerFacing = context.getHorizontalDirection().getOpposite();
        if (clickedFace == Direction.DOWN || clickedFace == Direction.UP) {
            return this.defaultBlockState().setValue(FACING, playerFacing);
        } else {
            return this.defaultBlockState().setValue(FACING, clickedFace.getOpposite());
        }
    }

    @Override //this says to place the block in the direction the player is facing.
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (!(placer instanceof Player)) {
            return;
        }
        Player player = (Player) placer;
        Direction direction = player.getDirection();
        if (direction == Direction.UP || direction == Direction.DOWN) {
            return;
        }
        BlockState newState = this.defaultBlockState().setValue(FACING, direction.getOpposite());
        if (newState.canSurvive(level, pos)) {
            level.setBlockAndUpdate(pos, newState);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            // Spawn a burst of your custom SPARKLE_PARTICLE particles around the block on the client side
            spawnRandomParticles(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, ModParticles.SPARKLE_PARTICLE.get(), 8);
        } else {
            level.destroyBlock(pos, false);
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.DIAMOND_HELMET)));
            level.playSound(null, pos, ModSounds.HAT_KIT_OPEN.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }

        return InteractionResult.SUCCESS;
    }

    // Helper method to spawn a burst of your custom particles around a position
    private void spawnRandomParticles(Level level, double centerX, double centerY, double centerZ, ParticleOptions particleType, int count) {
        for (int i = 0; i < count; ++i) {
            double offsetX = centerX + (level.random.nextFloat() - 0.5D) * 1.5D; // Random offset in X-axis
            double offsetY = centerY + (level.random.nextFloat() - 0.5D) * 1.1D; // Random offset in Y-axis
            double offsetZ = centerZ + (level.random.nextFloat() - 0.5D) * 1.5D; // Random offset in Z-axis

            level.addParticle(particleType, offsetX, offsetY, offsetZ, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override //this is what happens if the player destroys the block. it spawns the hat kit item.
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, net.minecraft.world.level.block.entity.BlockEntity blockEntity, ItemStack stack) {
        super.playerDestroy(level, player, pos, state, blockEntity, stack);
        if (!level.isClientSide && !player.isCreative()) {
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.HAT_KIT_BLOCK_ITEM.get())));
        }
    }

    @Override //this is what happens if the block is placed in water. it spawns the hat kit item.
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (level.getFluidState(pos).is(FluidTags.WATER)) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.HAT_KIT_BLOCK_ITEM.get())));
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }
}

