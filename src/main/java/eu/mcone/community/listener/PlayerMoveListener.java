package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.gameapi.api.player.GamePlayer;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();


        //entrance
        Location buehne1 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen3");
        Location buehne2 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen4");
        Location buehne3 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen1");
        Location buehne4 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen2");
        Location buehne5 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen5");
        Location buehne6 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen6");


        //back stairs out/entrance
        //       Location backStairs1 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehne-s2");
        //     Location backStairs2 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehne-s1");


        //backstage entrance big
        Location back1 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehne-p1");
        Location back2 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehne-p2");
        Location back3 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehne-p3");

        //premium buehne
        Location premiumBuehne1 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("pbuehne-1");
        Location premiumBuehne2 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("pbuehne-2");
        Location premiumBuehne3 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("pbuehne-3");


        //backstage
        if (!p.hasPermission("community.premium.buehne")) {
            if (
                    p.getLocation().distance(premiumBuehne1) <= 2 ||
                            p.getLocation().distance(premiumBuehne2) <= 2 ||
                            p.getLocation().distance(premiumBuehne3) <= 2) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setX(0.7);
                p.setVelocity(v1);

            }
        }

        if (!p.hasPermission("community.buehne")) {
            //entrance left
            if (p.getLocation().distance(buehne1) <= 2 ||
                    p.getLocation().distance(buehne2) <= 2 ||
                    p.getLocation().distance(buehne3) <= 2) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setZ(-0.7);
                p.setVelocity(v1);
            } else if (
                //right
                    p.getLocation().distance(buehne4) <= 2 ||
                            p.getLocation().distance(buehne5) <= 2 ||
                            p.getLocation().distance(buehne6) <= 2) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setZ(-0.7);
                p.setVelocity(v1);
            }
        }
        //entrance backstairs
         /*   if (p.getLocation().distance(backStairs1) <= 2 ||
                    p.getLocation().distance(backStairs2) <= 2) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setZ(-0.7);
                p.setVelocity(v1);
            }
            //entrance backstage big
        }*/
        if (!p.hasPermission("community.teamhouse") || !p.hasPermission("community.buehne")) {
            if (
                    p.getLocation().distance(back1) <= 2 ||
                            p.getLocation().distance(back2) <= 2 ||
                            p.getLocation().distance(back3) <= 2) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setZ(0.7);
                p.setVelocity(v1);

            }
        }


        for (Player players : InventoryTriggerListener.run.keySet()) {
            if (!p.hasPermission("community.bypass")) {
                if (p.getLocation().distance(players.getLocation()) <= 3) {

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
        if (InventoryTriggerListener.run.containsKey(p)) {
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
