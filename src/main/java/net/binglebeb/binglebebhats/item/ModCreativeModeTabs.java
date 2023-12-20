package net.binglebeb.binglebebhats.item;

import net.binglebeb.binglebebhats.binglebebhatsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, binglebebhatsMod.MODID);

public static final RegistryObject<CreativeModeTab> BINGLEBEB_HATS_TAB = CREATIVE_MODE_TABS.register("binglebeb_hats_tab",
        () -> CreativeModeTab.builder() .icon(() -> new ItemStack(moditems.HAT_KIT.get()))
                .title(Component.translatable("creativetab.binglebeb_hats_tab"))
                .displayItems((pParameters, pOutput) -> {
pOutput.accept(moditems.HAT_KIT.get());
                })
                .build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
