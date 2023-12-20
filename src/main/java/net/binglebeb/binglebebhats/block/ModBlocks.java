package net.binglebeb.binglebebhats.block;

import net.binglebeb.binglebebhats.BinglebebHats;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BinglebebHats.MOD_ID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);

    }
    public static final RegistryObject<Block> HAT_KIT_BLOCK_MODEL = BLOCKS.register("hat_kit_block_model",
            HatKitBlockModel::new);


}
