package eu.mcone.community.player;

import eu.mcone.coresystem.api.bukkit.player.profile.GameProfile;
import org.bukkit.entity.Player;

public class CommunityPlayerProfile extends GameProfile {

    public CommunityPlayerProfile(Player p) {
        super(p);
    }

    @Override
    public void doSetData(Player player) {}

}
