package net.binglebeb.binglebebhats.event;

import net.binglebeb.binglebebhats.BinglebebHats;
import net.binglebeb.binglebebhats.particle.ModParticles;
import net.binglebeb.binglebebhats.particle.SparkleParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

@Mod.EventBusSubscriber(modid = BinglebebHats.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.SPARKLE_PARTICLE.get(),
                SparkleParticle.Provider ::new);
    }

//RegisterParticleProvidersEvent(final ParticleFactory event) {
    //event.register (ModParticles.SPARKLE_PARTICLE.get(),
          //  SparkleParticle.Provider ::new);
//}



}