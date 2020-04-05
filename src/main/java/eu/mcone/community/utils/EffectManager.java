package eu.mcone.community.utils;

import eu.mcone.community.CommunityPlugin;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class EffectManager {

    private static boolean firePremium = false;
    public static int fireShedularPremium;

    private static boolean white = false;
    public static int whiteShedular;

    private static boolean fire = false;
    public static int fireShedular;

    private static boolean smoke = false;
    public static int smokeShedular;

    private static boolean blass = false;
    public static int blassShedular;


    private static void setBlass() {
        if (blass) {
            blassShedular = Bukkit.getScheduler().scheduleSyncRepeatingTask(CommunityPlugin.getSystem(), () -> {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (Bukkit.getOnlinePlayers().size() == 0) {
                        cancelAllTask();
                        return;
                    }


                    Particle particle2 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-2"), 3, 7, 5, 1, 45);
                    Particle particle1 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-1"), 3, 7, 2, 1, 45);
                    Particle particle3 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-3"), 5, 7, 7, 1, 45);
                    Particle particle4 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-4"), 5, 7, 6, 1, 45);
                    Particle particle5 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-5"), 5, 7, 2, 1, 35);
                    Particle particle6 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-6"), 5, 7, 2, 1, 35);
                    Particle particle7 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-7"), 5, 7, 5, 1, 45);

                    particle1.sendToAll(all);
                    particle2.sendToAll(all);
                    particle3.sendToAll(all);
                    particle4.sendToAll(all);
                    particle5.sendToAll(all);
                    particle6.sendToAll(all);
                    particle7.sendToAll(all);

                }
            }, 1, 2);
        }
    }

    public static void setBlassTrue() {
        blass = true;
        setBlass();
    }

    public static void setBlassFalse() {
        blass = false;
        Bukkit.getScheduler().cancelTask(blassShedular);
    }

    public static boolean isBlass() {
        return blass;
    }

    //

    private static void setWhite() {
        if (white) {
            whiteShedular = Bukkit.getScheduler().scheduleSyncRepeatingTask(CommunityPlugin.getSystem(), () -> {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (Bukkit.getOnlinePlayers().size() == 0) {
                        cancelAllTask();
                        return;
                    }

                    Particle particle2 = new Particle(EnumParticle.SNOWBALL, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-2"), 1, 1, 2, 1, 25);
                    Particle particle1 = new Particle(EnumParticle.SNOWBALL, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-1"), 1, 1, 2, 1, 25);
                    Particle particle3 = new Particle(EnumParticle.SNOWBALL, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-3"), 1, 1, 2, 1, 25);
                    Particle particle4 = new Particle(EnumParticle.SNOWBALL, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-4"), 1, 1, 2, 1, 25);
                    Particle particle5 = new Particle(EnumParticle.SNOWBALL, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-5"), 1, 1, 2, 1, 25);
                    Particle particle6 = new Particle(EnumParticle.SNOWBALL, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-6"), 1, 1, 2, 1, 25);
                    Particle particle7 = new Particle(EnumParticle.SNOWBALL, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-7"), 1, 1, 2, 1, 25);


                    Particle _particle2 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-2"), 3, 3, 5, 1, 35);
                    Particle _particle1 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-1"), 3, 3, 2, 1, 25);
                    Particle _particle3 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-3"), 5, 1, 7, 1, 25);
                    Particle _particle4 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-4"), 5, 3, 6, 1, 35);
                    Particle _particle5 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-5"), 5, 3, 2, 1, 25);
                    Particle _particle6 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-6"), 5, 3, 2, 1, 35);
                    Particle _particle7 = new Particle(EnumParticle.SPELL_MOB_AMBIENT, CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-7"), 5, 3, 5, 1, 25);

                    particle1.sendToAll(all);
                    particle2.sendToAll(all);
                    particle3.sendToAll(all);
                    particle4.sendToAll(all);
                    particle5.sendToAll(all);
                    particle6.sendToAll(all);
                    particle7.sendToAll(all);



                /*    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-1"), Effect.CRIT, 20, 1, 1, 1, 2, 2, 5, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-2"), Effect.CRIT, 20, 1, 1, 1, 2, 2, 5, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-3"), Effect.CRIT, 20, 1, 1, 1, 2, 2, 5, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-4"), Effect.CRIT, 20, 1, 1, 1, 2, 2, 5, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-5"), Effect.CRIT, 20, 1, 1, 1, 2, 2, 5, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-6"), Effect.CRIT, 20, 1, 1, 1, 2, 2, 5, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("white-7"), Effect.CRIT, 20, 1, 1, 1, 2, 2, 5, 20);

              */
                }
            }, 1, 2);
        }
    }

    public static void setWhiteTrue() {
        white = true;
        setWhite();
    }

    public static void setWhiteFalse() {
        white = false;
        Bukkit.getScheduler().cancelTask(whiteShedular);
    }

    public static boolean isWhite() {
        return white;
    }

    //

    private static void setFirePremiumEffect() {
        if (firePremium) {
            fireShedularPremium = Bukkit.getScheduler().scheduleSyncRepeatingTask(CommunityPlugin.getSystem(), () -> {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (Bukkit.getOnlinePlayers().size() == 0) {
                        cancelAllTask();
                        return;
                    }

                    Particle particle1 = new Particle(EnumParticle.LAVA, CommunityPlugin.getInstance().getCommunityWorld().getLocation("firePremium1"), 0, 1, 0, 1, 9);
                    Particle particle2 = new Particle(EnumParticle.LAVA, CommunityPlugin.getInstance().getCommunityWorld().getLocation("firePremium2"), 0, 1, 0, 1, 9);

                    particle1.sendToAll(all);
                    particle2.sendToAll(all);
                }
            }, 1, 2);
        }
    }

    public static void setFirePremiumTrue() {
        firePremium = true;
        setFirePremiumEffect();
    }

    public static void setFirePremiumFalse() {
        firePremium = false;
        Bukkit.getScheduler().cancelTask(fireShedularPremium);
    }

    public static boolean isFirePremium() {
        return firePremium;
    }

    //////

    private static void setFireEffect() {
        if (fire) {
            fireShedular = Bukkit.getScheduler().scheduleSyncRepeatingTask(CommunityPlugin.getSystem(), () -> {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (Bukkit.getOnlinePlayers().size() == 0) {
                        cancelAllTask();
                        return;
                    }
                    Particle particle1 = new Particle(EnumParticle.LAVA, CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire1"), 0, 2, 0, 10, 5);
                    Particle particle2 = new Particle(EnumParticle.LAVA, CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire2"), 0, 2, 0, 10, 5);
                    Particle particle3 = new Particle(EnumParticle.LAVA, CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire4"), 0, 2, 0, 10, 5);
                    Particle particle4 = new Particle(EnumParticle.LAVA, CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire3"), 0, 2, 0, 10, 5);


                    particle1.sendToAll(all);
                    particle2.sendToAll(all);
                    particle3.sendToAll(all);
                    particle4.sendToAll(all);

                /*    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire1"), Effect.LAVA_POP, 100, 1, 1, 1, 1, 3, 15, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire2"), Effect.LAVA_POP, 100, 1, 1, 1, 1, 3, 15, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire3"), Effect.LAVA_POP, 100, 1, 1, 1, 1, 3, 15, 20);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("fire4"), Effect.LAVA_POP, 100, 1, 1, 1, 1, 3, 15, 20);



                 */


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


    ///////////


    private static void setSmokeEffect() {
        if (smoke) {
            smokeShedular = Bukkit.getScheduler().scheduleSyncRepeatingTask(CommunityPlugin.getSystem(), () -> {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (Bukkit.getOnlinePlayers().size() == 0) {
                        cancelAllTask();
                        return;
                    }
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("smoke2"), Effect.LAVADRIP, 100, 10, 5, 5, 5, 1, 47, 25);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("smoke1"), Effect.LAVADRIP, 100, 10, 5, 5, 4, 1, 43, 25);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("smoke3"), Effect.LAVADRIP, 100, 10, 5, 5, 4, 1, 47, 25);
                    all.spigot().playEffect(CommunityPlugin.getInstance().getCommunityWorld().getLocation("smoke4"), Effect.LAVADRIP, 100, 10, 5, 5, 5, 1, 40, 25);
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
