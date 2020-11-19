package eu.mcone.community;

import eu.mcone.community.listener.*;
import eu.mcone.community.commands.CommunityCMD;
import eu.mcone.community.commands.EffectCMD;
import eu.mcone.community.utils.effects.ShieldManager;
import eu.mcone.community.utils.effects.StageEffectManager;
import eu.mcone.community.utils.vanish.VanishManager;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.listener.RegionEnterPermissionCanceller;
import eu.mcone.coresystem.api.bukkit.world.BuildSystem;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.gameapi.api.GamePlugin;
import eu.mcone.gameapi.api.Option;
import eu.mcone.lobby.api.items.LobbyCategory;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

public class CommunityPlugin extends GamePlugin {

    @Getter
    private static CommunityPlugin instance;

    @Getter
    private BuildSystem buildSystem;
    @Getter
    private CoreWorld communityWorld;
    @Getter
    private StageEffectManager stageEffectManager;
    @Getter
    private VanishManager vanishManager;
    @Getter
    private ShieldManager shieldManager;

    public CommunityPlugin() {
        super("Community", ChatColor.LIGHT_PURPLE, "community.prefix", Option.BACKPACK_MANAGER_REGISTER_OUTFIT_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_HAT_CATEGORY, Option.BACKPACK_MANAGER_REGISTER_TRAIL_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_EXCLUSIVE_CATEGORY, Option.BACKPACK_MANAGER_AUTO_SET_RANK_BOOTS);
    }

    @Override
    public void onGameEnable() {
        instance = this;
        communityWorld = CoreSystem.getInstance().getWorldManager().getWorld("Community");

        sendConsoleMessage("§aLoading StageEffectManager...");
        stageEffectManager = new StageEffectManager();

        sendConsoleMessage("§aLoading VanishManager...");
        vanishManager = new VanishManager(this);

        sendConsoleMessage("§aLoading ShieldManager...");
        shieldManager = new ShieldManager();

        communityWorld = CoreSystem.getInstance().getWorldManager().getWorld("Community");
        CoreSystem.getInstance().enableSpawnCommand(this, communityWorld, 0);

        buildSystem = CoreSystem.getInstance().initialiseBuildSystem(BuildSystem.BuildEvent.BLOCK_BREAK, BuildSystem.BuildEvent.BLOCK_PLACE, BuildSystem.BuildEvent.INTERACT);
        buildSystem.addFilter(BuildSystem.BuildEvent.INTERACT, 69, 143, 77, 70, 72, 148, 147);

        Listener l = new RegionEnterPermissionCanceller(communityWorld, "community.buehne", true, "buehne");
        sendConsoleMessage("§aLoading Commands, Events, CoreInventories...");
        registerEvents(
                new PlayerJoinListener(),
                new InventoryTriggerListener(),
                new GeneralPlayerListener(),
                new EntitiyDamageListener(),
                new PlayerQuitListener(),
                new PlayerMoveListener(),
                new PermissionChangeListener(),
                l
        );
        System.out.println("registered "+l);

        registerCommands(
                new CommunityCMD(),
                new EffectCMD()
        );

        sendConsoleMessage("§aVersion §f" + this.getDescription().getVersion() + "§a enabled...");
    }

    @Override
    public void onGameDisable() {
        sendConsoleMessage("§cPlugin disabled!");
    }

}
