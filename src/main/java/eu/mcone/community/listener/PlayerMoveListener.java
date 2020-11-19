package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();


        //TODO must be reworked

        //entrance
        Location buehne1 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen3");
        Location buehne2 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen4");
        Location buehne3 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen1");
        Location buehne4 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen2");
        Location buehne5 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen5");
        Location buehne6 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen6");


        Location backStairs1 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("backstage1");
        Location backStairs2 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("backstage2");
        Location backStairs3 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("backstage3");
        Location backStairs4 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("backstage4");
        Location backStairs5 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("backstage5");
        Location backStairs6 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("backstage6");
        Location backStairs7 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("backstage7");
        Location backStairs8 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("backstage8");

        //teamhouse
        Location back1 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehne-p1");
        Location back2 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehne-p2");
        Location back3 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehne-p3");

        //premium buehne
        Location premiumBuehne1 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("pbuehne-1");
        Location premiumBuehne2 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("pbuehne-2");
        Location premiumBuehne3 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("pbuehne-3");


        //backstage
        if (!p.hasPermission("community.premium.stage")) {
            if (
                    p.getLocation().distance(premiumBuehne1) <= 2 ||
                            p.getLocation().distance(premiumBuehne2) <= 2 ||
                            p.getLocation().distance(premiumBuehne3) <= 2) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setX(0.7);
                p.setVelocity(v1);

            }
        }

        if (!p.hasPermission("community.stage")) {
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
        if (!p.hasPermission("community.stage")) {
            if (p.getLocation().distance(backStairs1) <= 2 ||
                    p.getLocation().distance(backStairs2) <= 2 ||
                    p.getLocation().distance(backStairs3) <= 2 ||
                    p.getLocation().distance(backStairs4) <= 2
            ) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setX(0.7);
                p.setVelocity(v1);
            } else if (p.getLocation().distance(backStairs5) <= 2 ||
                    p.getLocation().distance(backStairs6) <= 2 ||
                    p.getLocation().distance(backStairs7) <= 2 ||
                    p.getLocation().distance(backStairs8) <= 2
            ) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setX(-0.7);
                p.setVelocity(v1);
            }


        }
        //entrance backstage big


        if (!p.hasPermission("community.teamhouse")) {
            if (
                    p.getLocation().distance(back1) <= 2 ||
                            p.getLocation().distance(back2) <= 2 ||
                            p.getLocation().distance(back3) <= 2) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setZ(0.7);
                p.setVelocity(v1);

            }
        }


        CommunityPlugin.getInstance().

                getShieldManager().

                push(p);

    }
}
