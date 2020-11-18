package eu.mcone.community.player;

import eu.mcone.coresystem.api.bukkit.player.profile.GameProfile;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2018 - 2019 © All rights reserved
 * You are not allowed to decompile the codee
 * Created by Marvin Hülsmann on 10.12.2019 in community.
 */
public class CommunityPlayerProfile extends GameProfile {

    public CommunityPlayerProfile(Player p) {
        super(p);
    }

    @Override
    public void doSetData(Player player) {}

}
