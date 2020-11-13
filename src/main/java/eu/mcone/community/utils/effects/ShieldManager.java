package eu.mcone.community.utils.effects;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
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
                CommunityPlugin.getInstance().getMessenger().send(p, "§cDas Schutzschild wurde deaktiviert");
                p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 2F, 0.5F);
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
                CommunityPlugin.getInstance().getMessenger().send(p, "§aDas Schutzschild wurde aktiviert");
                p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 2F, 1F);
            }
        } else {
            if (run.containsKey(p)) {
                run.get(p).cancel();
                run.remove(p);
            }
            CommunityPlugin.getInstance().getMessenger().send(p, "§4Du darfst im Vanish Modus das Schutzschild nicht benutzen!");
        }
    }

    public void push(Player p) {
        for (Player players : run.keySet()) {
            if (!p.hasPermission("community.bypass")) {
                if (p.getLocation().distance(players.getLocation()) <= 5) {

                    double Ax = p.getLocation().getX();
                    double Ay = p.getLocation().getY();
                    double Az = p.getLocation().getZ();

                    double Bx = players.getLocation().getX();
                    double By = players.getLocation().getY();
                    double Bz = players.getLocation().getZ();

                    double x = Ax - Bx;
                    double y = Ay - By;
                    double z = Az - Bz;
                    Vector v = new Vector(x, y, z).normalize();
                    p.setVelocity(v);

                }
            }
        }

        if (run.containsKey(p)) {
            for (Entity entity : p.getNearbyEntities(3, 3, 3)) {
                if (entity instanceof Player) {
                    Player target = (Player) entity;
                    if (p != target) {
                        if (!target.hasPermission("community.bypass")) {

                            double Ax = p.getLocation().getX();
                            double Ay = p.getLocation().getY();
                            double Az = p.getLocation().getZ();

                            double Bx = target.getLocation().getX();
                            double By = target.getLocation().getY();
                            double Bz = target.getLocation().getZ();

                            double x = Bx - Ax;
                            double y = By - Ay;
                            double z = Bz - Az;
                            Vector v = new Vector(x, y, z).normalize();
                            target.setVelocity(v);

                        }
                    }
                }
            }
        }
    }

}
