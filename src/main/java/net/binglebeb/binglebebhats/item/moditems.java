package net.binglebeb.binglebebhats.item;

import net.binglebeb.binglebebhats.binglebebhatsMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class moditems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, binglebebhatsMod.MODID);

public static final RegistryObject<Item> HAT_KIT = ITEMS.register( "hat_kit",
        () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
