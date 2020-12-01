package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.inventory.CommunitySettingsInventory;
import eu.mcone.community.inventory.EffectMainInventory;
import eu.mcone.community.inventory.EffectPremiumInventory;
import eu.mcone.community.inventory.NavigatorInventory;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Transl;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.gameapi.api.HotbarItem;
import eu.mcone.lobby.api.items.LobbyCategory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.event.entity.EntityDismountEvent;

public class InventoryTriggerListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null) {
                Material clicked = e.getClickedBlock().getType();

                if (clicked.equals(Material.COBBLESTONE_STAIRS) || clicked.equals(Material.WOOD_STAIRS) || clicked.equals(Material.DARK_OAK_STAIRS)
                        || clicked.equals(Material.BIRCH_WOOD_STAIRS) || clicked.equals(Material.ACACIA_STAIRS)
                        || clicked.equals(Material.BRICK_STAIRS)
                        || clicked.equals(Material.SPRUCE_WOOD_STAIRS)
                        || clicked.equals(Material.SMOOTH_STAIRS)) {

                    if (e.getBlockFace().getOppositeFace().equals(BlockFace.DOWN)) {
                        sitDownPlayer(p, e.getClickedBlock().getLocation().add(0.5, 0, 0.5));
                    }

                }


                if (clicked.equals(Material.STONE_BUTTON)) {
                    if (CommunityPlugin.getInstance().getCommunityWorld().getBlockLocation("effect").equals(e.getClickedBlock().getLocation())) {
                        if (p.hasPermission("community.effectmenu")) {
                            new EffectMainInventory(p);
                        } else {
                            CommunityPlugin.getInstance().getMessenger().sendTransl(p, "community.inventorytriggerlistener.noperms");
                        }
                    }
                }

                if (clicked == Material.STONE_BUTTON) {
                    if (CommunityPlugin.getInstance().getCommunityWorld().getBlockLocation("effect-premium").equals(e.getClickedBlock().getLocation())) {
                        if (p.hasPermission("community.premium.effectmenu")) {
                            new EffectPremiumInventory(p);
                        } else {
                            CommunityPlugin.getInstance().getMessenger().sendTransl(p, "community.inventorytriggerlistener.noperms");
                        }
                    }
                }

            }
            ItemStack i = p.getItemInHand();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }


            if (e.getItem().equals(HotbarItem.BACKPACK)) {
                CommunityPlugin.getInstance().getBackpackManager().openBackpackInventory(LobbyCategory.STORY_ITEMS.name(), p);
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Transl.get("system.inventorys.items.profile", cp))) {
                e.setCancelled(true);
                p.performCommand("profile");
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Transl.get("community.inventorys.items.navigator", cp))) {
                new NavigatorInventory(p);
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);

            } else if (e.getItem().getItemMeta().getDisplayName().equals(Transl.get("community.inventorys.items.settings", cp))) {
                e.setCancelled(true);
                new CommunitySettingsInventory(p);
            } else if (e.getItem().getType() == Material.EYE_OF_ENDER) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (p.hasPermission("community.shield")) {
                        e.setCancelled(true);
                        CommunityPlugin.getInstance().getShieldManager().auto(p);
                    }
                }
            }
        }
    }


    public void sitDownPlayer(Player p, Location spawnLocation) {
        Arrow arrow = (Arrow) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.ARROW);
        arrow.setCustomNameVisible(true);

        Bukkit.getScheduler().runTaskLater(GameAPI.getInstance(), () -> {
            arrow.setPassenger(p);
            CoreSystem.getInstance().createActionBar()
                    .message("§f§oBenutze LSHIFT um aufzustehen")
                    .send(p);
        }, 2L);
    }


    @EventHandler
    public void onSitUp(EntityDismountEvent e) {
        if (e.getDismounted().getType().equals(EntityType.ARROW)) {
            e.getDismounted().remove();
        }
    }
}
