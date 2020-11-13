package eu.mcone.community.utils.effects;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.utils.Particle;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class StageEffectManager {

    @Getter
    public HashMap<StageEffects, Integer> effectsType = new HashMap<>();

    public void auto(Player player, StageEffects stageEffects) {
        if (effectsType.containsKey(stageEffects)) {
            stopEffect(stageEffects);
            CommunityPlugin.getInstance().getMessenger().send(player, "§4Du hast das Effekt §c" + stageEffects.getName() + " §4deaktivert");
        } else {
            startEffect(stageEffects);
            CommunityPlugin.getInstance().getMessenger().send(player, "§2Du hast das Effekt §a" + stageEffects.getName() + " §2aktiviert");
        }
    }

    public void startEffect(StageEffects stageEffects) {

        Integer shedular = Bukkit.getScheduler().scheduleSyncRepeatingTask(CommunityPlugin.getSystem(), () -> {

            for (int i = 1; i <= stageEffects.getAmount(); i++) {

                Particle particle = new Particle(stageEffects.getParticle().getEnumParticle(), CommunityPlugin.getInstance().getCommunityWorld().getLocation(stageEffects.getLocName() + i), stageEffects.getParticle().getXOffSet(), stageEffects.getParticle().getYOffSet(), stageEffects.getParticle().getZOffSet(), stageEffects.getParticle().getSpeed(), stageEffects.getParticle().getCount());
                particle.sendToAll();
            }

        }, 1, 2);

        effectsType.put(stageEffects, shedular);

        System.out.println("start effect" + stageEffects.getLocName());
    }


    public void stopEffect(StageEffects stageEffects) {
        Bukkit.getScheduler().cancelTask(effectsType.get(stageEffects));
        effectsType.remove(stageEffects);

        System.out.println("stop effect" + stageEffects.getLocName());
    }


    public void clearAllEffects() {
        effectsType.clear();
        Bukkit.getScheduler().cancelAllTasks();
    }


}
