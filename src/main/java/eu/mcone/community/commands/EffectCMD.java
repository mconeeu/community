package eu.mcone.community.commands;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.inventory.EffectMainInventory;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import org.bukkit.entity.Player;

public class EffectCMD extends CorePlayerCommand {

    public EffectCMD() {
        super("effect");
    }

    @Override
    public boolean onPlayerCommand(Player p, String[] args) {
        if (p.hasPermission("community.effectmenu")) {
            new EffectMainInventory(p);
        } else {
            CommunityPlugin.getInstance().getMessenger().send(p, "§4Du hast für diesen Befehl keine Berechtigung!");
        }

        return false;
    }
}
