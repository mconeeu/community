package eu.mcone.community.utils.vanish;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum VanishPlayerVisibility {

    EVERYBODY(
            "Spieler sichtbar",
            new ItemBuilder(Material.INK_SACK, 1, DyeColor.LIME.getDyeData())
                    .displayName("§3§lSpieler Verstecken §8» §7§oAlle Spieler sichtbar")
                    .create()
    ),
    ONLY_VIPS(
            "Nur VIPs sichtbar",
            new ItemBuilder(Material.INK_SACK, 1, DyeColor.PURPLE.getDyeData())
                    .displayName("§3§lSpieler Anzeigen §8» §7§oNur VIPs sichtbar")
                    .create()
    ),
    //TODO: Add ONLY_FRIENDS (waiting for coresystem friend system feature)
    NOBODY(
            "Spieler unsichtbar",
            new ItemBuilder(Material.INK_SACK, 1, DyeColor.GRAY.getDyeData())
                    .displayName("§3§lSpieler Anzeigen §8» §7§oAlle Spieler unsichtbar")
                    .create()
    );

    private final String name;
    private final ItemStack item;

    VanishPlayerVisibility(String name, ItemStack item) {
        this.name = name;
        this.item = item;
    }

    public VanishPlayerVisibility getNextVanishPlayerVisibility() {
        int i = ordinal();

        if (i >= 2) {
            return EVERYBODY;
        } else return values()[++i];
    }

}
