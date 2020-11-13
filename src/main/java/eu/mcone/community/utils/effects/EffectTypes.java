package eu.mcone.community.utils.effects;

import lombok.Getter;
import net.minecraft.server.v1_8_R3.EnumParticle;

@Getter
public enum EffectTypes {

    FIRE(StageEffects.FIRE, EnumParticle.LAVA, 0, 2, 0, 10, 5),
    KONFETTI(StageEffects.KONFETTI, EnumParticle.DRIP_LAVA, 5, 5, 5, 10, 47),
    SNOW(StageEffects.SNOW, EnumParticle.SNOWBALL, 5, 3, 5, 1, 25),
    BLASS(StageEffects.BLASS, EnumParticle.SPELL_MOB_AMBIENT, 5, 7, 5, 1, 45),
    MUSIC(StageEffects.MUSIC, EnumParticle.NOTE, 1, 2, 1, 3, 3),
    WATER(StageEffects.WATER, EnumParticle.WATER_SPLASH, 8, 2, 8, 3, 42),

    FIRE_PREMIUM(StageEffects.PREMIUMFIRE, EnumParticle.LAVA, 0, 1, 0, 1, 9);

    private final StageEffects stageEffects;
    private final EnumParticle enumParticle;
    private final Integer xOffSet, yOffSet, zOffSet, speed, count;


    EffectTypes(StageEffects stageEffects, EnumParticle enumParticle, Integer xOffSet, Integer yOffSet, Integer zOffSet, Integer speed, Integer count) {
        this.stageEffects = stageEffects;
        this.enumParticle = enumParticle;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
        this.zOffSet = zOffSet;
        this.speed = speed;
        this.count = count;
    }

}
