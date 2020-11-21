package eu.mcone.community.utils.effects;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Transl;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import lombok.Getter;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class ShieldManager {

    @Getter
    public HashMap<Player, BukkitRunnable> run = new HashMap<>();

    public void auto(Player p) {
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);

        if (!cp.isVanished()) {
            if (run.containsKey(p)) {
                CommunityPlugin.getInstance().getMessenger().sendTransl(cp.bukkit(), "community.protect.deactivated");
                CoreSystem.getInstance().getSoundManager().play(p, Sound.SUCCESSFUL_HIT);
                run.get(p).cancel();
                run.remove(p);
            } else if (!run.containsKey(p)) {
                run.put(p, new BukkitRunnable() {


                    @Override
                    public void run() {
                        p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 100, 100);
                        p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 100, 100);

                    }

                });
                run.get(p).runTaskTimer(CommunityPlugin.getInstance(), 20, 20);
                CommunityPlugin.getInstance().getMessenger().sendTransl(cp.bukkit(), "community.protect.active");
                CoreSystem.getInstance().getSoundManager().play(p, Sound.SUCCESSFUL_HIT);
            }
        } else {
            if (run.containsKey(p)) {
                run.get(p).cancel();
                run.remove(p);
            }
            CommunityPlugin.getInstance().getMessenger().sendTransl(cp.bukkit(), "community.protect.vanish");
        }
    }

    public void push(Player p) {
        for (Player players : run.keySet()) {
            CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);
            if (!p.hasPermission("community.bypass") || corePlayer.isNicked()) {
                if (p.getLocation().distance(players.getLocation()) <= 5) {
                    math(players, p, false);
                }
            }
        }

        if (run.containsKey(p)) {
            for (Entity entity : p.getNearbyEntities(3, 3, 3)) {
                if (entity instanceof Player) {
                    Player target = (Player) entity;
                    if (p != target) {
                        CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(target);
                        if (!target.hasPermission("community.bypass") || corePlayer.isNicked()) {
                            math(target, p, true);
                        }
                    }
                }
            }
        }
    }

    private void math(Player target, Player p, boolean first) {
        double Ax = p.getLocation().getX();
        double Ay = p.getLocation().getY();
        double Az = p.getLocation().getZ();

        double Bx = target.getLocation().getX();
        double By = target.getLocation().getY();
        double Bz = target.getLocation().getZ();

        if (first) {
            double x = Bx - Ax;
            double y = By - Ay;
            double z = Bz - Az;

            Vector v = new Vector(x, y, z).normalize();
            target.setVelocity(v);

        } else {

            double x = Ax - Bx;
            double y = Ay - By;
            double z = Az - Bz;
            Vector v = new Vector(x, y, z).normalize();
            p.setVelocity(v);

        }
    }
}
