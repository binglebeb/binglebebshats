package net.binglebeb.binglebebhats.block;

import net.binglebeb.binglebebhats.BinglebebHats;
import net.binglebeb.binglebebhats.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BinglebebHats.MOD_ID);

    //this lets blocks appear as items within inventory.
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //this registers the hat kit block and gives it the properties of low strength and sound properties of wood.
    public static final RegistryObject<Block> HAT_KIT_BLOCK = BLOCKS.register("hat_kit_block",
            () -> new HatKitBlock(Block.Properties.of()
                    .strength(0.1F)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

