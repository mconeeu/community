package eu.mcone.community;

import eu.mcone.community.commands.CommunityCMD;
import eu.mcone.community.commands.CurrentEventCMD;
import eu.mcone.community.commands.EffectCMD;
import eu.mcone.community.commands.EventCMD;
import eu.mcone.community.listener.*;
import eu.mcone.community.utils.Captures;
import eu.mcone.community.utils.EventManager;
import eu.mcone.community.utils.TimeManager;
import eu.mcone.community.utils.effects.ShieldManager;
import eu.mcone.community.utils.effects.StageEffectManager;
import eu.mcone.community.utils.vanish.VanishManager;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.listener.EntityDamageCanceller;
import eu.mcone.coresystem.api.bukkit.world.BuildSystem;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.coresystem.api.core.exception.MotionCaptureNotDefinedException;
import eu.mcone.gameapi.api.GamePlugin;
import eu.mcone.gameapi.api.Option;
import lombok.Getter;
import org.bukkit.ChatColor;

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
    @Getter
    private EventManager eventManager;
    @Getter
    private TimeManager timeManager;

    public CommunityPlugin() {
        super("Community", ChatColor.LIGHT_PURPLE, "community.prefix", Option.BACKPACK_MANAGER_REGISTER_OUTFIT_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_HAT_CATEGORY, Option.BACKPACK_MANAGER_REGISTER_TRAIL_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_EXCLUSIVE_CATEGORY, Option.BACKPACK_MANAGER_AUTO_SET_RANK_BOOTS);
    }

    @Override
    public void onGameEnable() {
        instance = this;
        communityWorld = CoreSystem.getInstance().getWorldManager().getWorld("Community");

        sendConsoleMessage("??aLoading StageEffectManager...");
        stageEffectManager = new StageEffectManager();

        sendConsoleMessage("??aLoading VanishManager...");
        vanishManager = new VanishManager(this);

        sendConsoleMessage("??aLoading ShieldManager...");
        shieldManager = new ShieldManager();

        sendConsoleMessage("??aLoading EventManager...");
        eventManager = new EventManager();

        sendConsoleMessage("??aLoading TimeManager...");
        timeManager = new TimeManager();

        sendConsoleMessage("??aLoading Captures...");
        loadCaptures();

        communityWorld = CoreSystem.getInstance().getWorldManager().getWorld("Community");
        CoreSystem.getInstance().enableSpawnCommand(this, communityWorld, 0);

        buildSystem = CoreSystem.getInstance().initialiseBuildSystem(BuildSystem.BuildEvent.BLOCK_BREAK, BuildSystem.BuildEvent.BLOCK_PLACE, BuildSystem.BuildEvent.INTERACT);
        buildSystem.addFilter(BuildSystem.BuildEvent.INTERACT, 69, 143, 77, 70, 72, 148, 147);

        sendConsoleMessage("??aLoading Commands, Events, CoreInventories...");
        registerEvents(
                new PlayerJoinListener(),
                new InventoryTriggerListener(),
                new GeneralPlayerListener(),
                new EntitiyDamageListener(),
                new PlayerQuitListener(),
                new ChangeListener(),
                new RegionEnterListener(),
                new NpcListener(),
                new EntityDamageCanceller()
              //  new RegionEnterPermissionCanceller(communityWorld, "community.stage", true, "stage", "stage-front")
        );

        registerCommands(
                new CommunityCMD(),
                new EffectCMD(),
                new EventCMD(),
                new CurrentEventCMD()
        );

        sendConsoleMessage("??aVersion ??f" + this.getDescription().getVersion() + "??a enabled...");
    }

    @Override
    public void onGameDisable() {
        sendConsoleMessage("??cPlugin disabled!");
    }


    private static void loadCaptures() {
        for (Captures capture : Captures.values()) {
            try {
                capture.getNpc().playMotionCapture(capture.getCapture());
                CoreSystem.getInstance().getNpcManager().getMotionCaptureHandler().getMotionCaptureScheduler().addNpc(capture.getNpc());
            } catch (MotionCaptureNotDefinedException e) {
                CommunityPlugin.getInstance().sendConsoleMessage("??cCould not load motion capture "+capture.getCapture());
            } catch (NullPointerException e) {
                CommunityPlugin.getInstance().sendConsoleMessage("??cCould not load npc "+capture.getNpcName()+" for capture motion  "+capture.name());
            }
        }
    }

}
