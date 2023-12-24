package net.binglebeb.binglebebhats.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SparkleParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected SparkleParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed,
                              SpriteSet spriteSet) {

        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.friction = 0.8F;
        this.quadSize *= 0.85f;
        this.lifetime = 50; // Increase the lifetime to make it last longer
        this.setSpriteFromAge(spriteSet);

        double angle = pLevel.getRandom().nextDouble() * Math.PI * 2; // Random angle in radians
        double radius = 0.14; // Adjust the radius for a smaller spread
        this.xd = Math.cos(angle) * radius *0.5; // Set X velocity based on cosine of the angle
        this.yd = 0.2F; // Set a slower fixed Y velocity for gentle upward motion
        this.zd = Math.sin(angle) * radius *0.5; // Set Z velocity based on sine of the angle

        this.sprites = spriteSet; // Assign the SpriteSet to the sprites variable
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age % 5 == 0) {
            // Gradually slow down the particle's Y velocity to simulate falling
            this.yd *= 0.98D;
        }

        // Move the particle downwards slowly
        this.move(this.xd, this.yd - 0.1D, this.zd);

        if (this.age++ >= this.lifetime) {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            SparkleParticle particle = new SparkleParticle(level, x, y, z, dx, dy, dz, this.sprites);

            // Spawn additional particles at the start
            for (int i = 0; i < 2; i++) {
                double newX = x + (level.random.nextDouble() - 0.5D) * 0.3D;
                double newY = y + (level.random.nextDouble() - 0.5D) * 0.3D;
                double newZ = z + (level.random.nextDouble() - 0.5D) * 0.3D;

                SparkleParticle newParticle = new SparkleParticle(level, newX, newY, newZ, dx, dy, dz, this.sprites);
                newParticle.pickSprite(this.sprites);
                newParticle.age = level.random.nextInt(30); // Set random starting age for each particle
                Minecraft.getInstance().particleEngine.add(newParticle);
            }

            return particle;
        }
    }

}
