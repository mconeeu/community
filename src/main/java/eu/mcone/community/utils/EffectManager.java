package eu.mcone.community.utils;

import eu.mcone.community.CommunityPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class EffectManager {

    private static boolean fire = false;
    public static int fireShedular;

    private static boolean smoke = false;
    public static int smokeShedular;

    private static void setFireEffect() {
        if (fire) {
            fireShedular = Bukkit.getScheduler().scheduleSyncRepeatingTask(CommunityPlugin.getSystem(), () -> {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (Bukkit.getOnlinePlayers().size() == 0) {
                        cancelAllTask();
                        return;
                    }
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire1"), Effect.LAVA_POP, 100, 1, 1, 1, 1, 3, 15, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire2"), Effect.LAVA_POP, 100, 1, 1, 1, 1, 3, 15, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire3"), Effect.LAVA_POP, 100, 1, 1, 1, 1, 3, 15, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire4"), Effect.LAVA_POP, 100, 1, 1, 1, 1, 3, 15, 20);
                }
            }, 1, 2);
        }
    }

    public static void setFireTrue() {
        fire = true;
        setFireEffect();
    }

    public static void setFireFalse() {
        fire = false;
        Bukkit.getScheduler().cancelTask(fireShedular);
    }

    public static boolean isFire() {
        return fire;
    }


    private static void setSmokeEffect() {
        if (smoke) {
            smokeShedular = Bukkit.getScheduler().scheduleSyncRepeatingTask(CommunityPlugin.getSystem(), () -> {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (Bukkit.getOnlinePlayers().size() == 0) {
                        cancelAllTask();
                        return;
                    }
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("smoke2"), Effect.EXPLOSION, 100, 10, 5, 5, 5, 1, 35, 25);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("smoke1"), Effect.EXPLOSION, 100, 10, 5, 5, 5, 1, 35, 25);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("smoke3"), Effect.EXPLOSION, 100, 10, 5, 5, 5, 1, 35, 25);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("smoke4"), Effect.EXPLOSION, 100, 10, 5, 5, 5, 1, 35, 25);
                }
            }, 1, 2);
        }
    }

    public static void setSmokeTrue() {
        smoke = true;
        setSmokeEffect();
    }

    public static void setSmokeFalse() {
        smoke = false;
        Bukkit.getScheduler().cancelTask(smokeShedular);
    }

    public static boolean isSmoke() {
        return smoke;
    }


    public static void cancelAllTask() {
        Bukkit.getScheduler().cancelAllTasks();
    }


}
