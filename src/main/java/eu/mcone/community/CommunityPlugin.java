package eu.mcone.community;

import eu.mcone.community.Listener.*;
import eu.mcone.community.player.CommunityPlayer;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.world.BuildSystem;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.gameapi.api.GamePlugin;
import eu.mcone.gameapi.api.Option;
import eu.mcone.lobby.api.enums.Category;
import lombok.Getter;
import org.bukkit.ChatColor;

public class CommunityPlugin extends GamePlugin<CommunityPlayer> {

    @Getter
    private static CommunityPlugin instance;

    @Getter
    private BuildSystem buildSystem;
    @Getter
    private CoreWorld communityWorld;

    public CommunityPlugin() {
        super("community", ChatColor.LIGHT_PURPLE, "community.prefix", Option.BACKPACK_MANAGER_REGISTER_OUTFIT_CATEGORY, Option.BACKPACK_MANAGER_REGISTER_HAT_CATEGORY, Option.BACKPACK_MANAGER_REGISTER_TRAIL_CATEGORY, Option.BACKPACK_MANAGER_REGISTER_EXCLUSIVE_CATEGORY, Option.BACKPACK_MANAGER_USE_RANK_BOOTS);
    }

    @Override
    public void onEnable() {

        instance = this;
        communityWorld = CoreSystem.getInstance().getWorldManager().getWorld("Community");
        CoreSystem.getInstance().getTranslationManager().loadCategories(this);
        CoreSystem.getInstance().enableSpawnCommand(this, communityWorld, 0);

        buildSystem = CoreSystem.getInstance().initialiseBuildSystem(BuildSystem.BuildEvent.BLOCK_BREAK, BuildSystem.BuildEvent.BLOCK_PLACE, BuildSystem.BuildEvent.INTERACT);
        buildSystem.addFilter(BuildSystem.BuildEvent.INTERACT, 69);

        sendConsoleMessage("§aLoading Commands, Events, CoreInventories...");
        registerEvents(
                new PlayerJoinListener(),
                new InventoryTriggerListener(),
                new GeneralPlayerListener(),
                new WeatherChangeListener(),
                new EntitiyDamageListener(),
                new PlayerQuitListener(),
                new PlayerMoveListener()
        );

        getBackpackManager().loadAdditionalCategories(Category.STORY_ITEMS.name());

        sendConsoleMessage("§aVersion §f" + this.getDescription().getVersion() + "§a enabled...");
    }

    @Override
    public void onDisable() {
        sendConsoleMessage("§cPlugin disabled!");
    }

}
