package eu.mcone.community.commands;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.gameapi.api.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FreeEntranceCMD extends CorePlayerCommand {

    public FreeEntranceCMD() {
        super("FreeEntrance");
    }


    public static boolean freeEntrance = false;

    @Override
    public boolean onPlayerCommand(Player player, String[] args) {
        if (player.hasPermission("community.freeentrance")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    if (!freeEntrance) {
                        freeEntrance = true;
                        CommunityPlugin.getInstance().getMessager().send(player, "§aDer Eintritt ist nun für jeden gratis!");

                        for (GamePlayer gamePlayer : CommunityPlugin.getInstance().getOnlineGamePlayers()) {
                            if (!gamePlayer.bukkit().hasPermission("community.bypass.entrance")) {
                                if (!gamePlayer.hasDefaultItem(DefaultItem.FESTIVAL_ENTRANCE)) {
                                    CommunityPlugin.getInstance().getMessager().send(gamePlayer.bukkit(), "§aDer Festival Eintritt ist nun gratis!");
                                }
                            }
                        }
                    } else {
                        CommunityPlugin.getInstance().getMessager().send(player, "§4Der Eintritt ist bereits für jeden gratis!");
                    }

                } else if (args[0].equalsIgnoreCase("off")) {
                    if (freeEntrance) {
                        freeEntrance = false;

                        player.closeInventory();
                        CommunityPlugin.getInstance().getMessager().send(player, "§aDer Eintritt ist nun nicht mehr für jeden gratis!");

                        for (GamePlayer gamePlayer : CommunityPlugin.getInstance().getOnlineGamePlayers()) {
                            if (!gamePlayer.bukkit().hasPermission("community.bypass.entrance")) {
                                if (!gamePlayer.hasDefaultItem(DefaultItem.FESTIVAL_ENTRANCE)) {
                                    CommunityPlugin.getInstance().getMessager().send(gamePlayer.bukkit(), "§4Der Festival Eintritt ist nun nicht mehr gratis, und du wurdest rausgeschmissen!");
                                    CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(gamePlayer.bukkit(), "entrance-out");
                                }
                            }
                        }
                    } else {
                        CommunityPlugin.getInstance().getMessager().send(player, "§4Der Festival gratis Eintritt ist bereits deaktivert!");
                    }


                }
            } else {
                CommunityPlugin.getInstance().getMessager().send(player, "§4Bitte benutze; §c/freeentrance [<on/off>]");
            }
        } else {
            CommunityPlugin.getInstance().getMessager().send(player, "§4Du hast keine Berechtingung für diesen Befehl!");
        }

        return false;
    }
}
