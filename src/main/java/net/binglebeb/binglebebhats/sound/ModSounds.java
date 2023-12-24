package net.binglebeb.binglebebhats.sound;

import net.binglebeb.binglebebhats.BinglebebHats;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BinglebebHats.MOD_ID);

    public static final RegistryObject<SoundEvent> HAT_KIT_OPEN = registerSoundEvent("hat_kit_open");

    public static final RegistryObject<SoundEvent> HAT_KIT_WAIT = registerSoundEvent("hat_kit_wait");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(BinglebebHats.MOD_ID, name);
        return SOUNDS_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS_EVENTS.register(eventBus);
    }
}
