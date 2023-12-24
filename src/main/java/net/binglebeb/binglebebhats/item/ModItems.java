package net.binglebeb.binglebebhats.item;

import net.binglebeb.binglebebhats.BinglebebHats;
import net.binglebeb.binglebebhats.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BinglebebHats.MOD_ID);

    public static final RegistryObject<BlockItem> HAT_KIT_BLOCK_ITEM = ITEMS.register("hat_kit",
            () -> new HatKitBlockItem(ModBlocks.HAT_KIT_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
