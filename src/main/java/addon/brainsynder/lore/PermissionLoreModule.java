package addon.brainsynder.lore;

import addon.brainsynder.lore.config.ConfigOption;
import lib.brainsynder.item.ItemBuilder;
import lib.brainsynder.utils.Colorize;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import simplepets.brainsynder.addon.AddonConfig;
import simplepets.brainsynder.addon.PetModule;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.event.inventory.PetInventoryAddPetItemEvent;
import simplepets.brainsynder.api.pet.PetType;

import java.util.ArrayList;
import java.util.List;

@Namespace(namespace = "PermissionLore")
public class PermissionLoreModule extends PetModule {

    @Override
    public void init() {}

    @Override
    public void loadDefaults(AddonConfig config) {
        ConfigOption.INSTANCE.getOptions().forEach((key, option) -> {
            config.addDefault(key, option.getValue(), option.getDescription().replace("{default}", String.valueOf(option.getDefaultValue())));
            option.setValue(config.get(key));
        });
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onPetItem (PetInventoryAddPetItemEvent event) {
        Player player = event.getUser().getPlayer();
        if (player == null) return;

        ItemStack stack;

        if (ConfigOption.INSTANCE.CHECK_PURCHASE_LIST.getValue()) {
            stack = handle(player, event.getType(), event.getUser().getOwnedPets().contains(event.getType()), event.getItem());
        }else{
            stack = handle(player, event.getType(), player.hasPermission(event.getType().getPermission()), event.getItem());
        }

        event.setItem(stack);
    }

    private ItemStack handle (Player player, PetType type, boolean check, ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        List<String> lore = (meta.hasLore() ? meta.getLore() : new ArrayList<>());
        if (check) {
            if (!ConfigOption.INSTANCE.HAS_LORE_ENABLED.getValue()) return stack;
            if (ConfigOption.INSTANCE.HAS_LORE_TEXT.getValue().isEmpty()) return stack;

            ConfigOption.INSTANCE.HAS_LORE_TEXT.getValue().forEach(string -> {
                lore.add(Colorize.translateBungeeHex(string
                        .replace("{player}", player.getName())
                        .replace("{type}", type.getName()))
                );
            });
        }else if (ConfigOption.INSTANCE.MISSING_LORE_ENABLED.getValue()) {
            if (ConfigOption.INSTANCE.MISSING_LORE_TEXT.getValue().isEmpty()) return stack;

            ConfigOption.INSTANCE.MISSING_LORE_TEXT.getValue().forEach(string -> {
                lore.add(Colorize.translateBungeeHex(string
                        .replace("{player}", player.getName())
                        .replace("{type}", type.getName()))
                );
            });
        }
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }
}
