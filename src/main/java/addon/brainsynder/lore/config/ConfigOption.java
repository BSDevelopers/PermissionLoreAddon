package addon.brainsynder.lore.config;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public enum ConfigOption {
    INSTANCE;
    private final Map<String, ConfigEntry> options = new LinkedHashMap<>();

    public final ConfigEntry<Boolean> CHECK_PURCHASE_LIST = createOption("check-purchase-list-instead", false,
            """
                    Should the addon check the players purchased pet list instead of their permissions?
                    
                    Default: {default}""");


    public final ConfigEntry<Boolean> MISSING_LORE_ENABLED = createOption("missing-permission.lore.enabled", true,
            """
                    If the player does not have permission to this pet should the text below be added to the pets item?
                    
                    NOTE: This option only works if the 'Only-Show-Pets-Player-Can-Access' is set to false in the SimplePets config file
                    
                    Default: {default}""");
    public final ConfigEntry<List<String>> MISSING_LORE_TEXT = createOption("missing-permission.lore.text", Lists.newArrayList("&cSorry &7{player}", "&cYou do not have permission", "&cfor this pet :("),
            """
                    This list of text gets added to the pets item if they do not have permission for the pet
                    
                    NOTE: This requires the 'missing-permission' lore to be enabled
                    
                    Placeholders:
                    - {player} = the players name
                    - {type} = the pets name
                    
                    Default: {default}""");



    public final ConfigEntry<Boolean> HAS_LORE_ENABLED = createOption("has-permission.lore.enabled", false,
            """
                    If the player does have permission to this pet should the text below be added to the pets item?
                                        
                    Default: {default}""");
    public final ConfigEntry<List<String>> HAS_LORE_TEXT = createOption("has-permission.lore.text", Lists.newArrayList(),
            """
                    This list of text gets added to the pets item if they do not have permission for the pet
                    
                    NOTE: This requires the 'has-permission' lore to be enabled
                    
                    Placeholders:
                    - {player} = the players name
                    - {type} = the pets name
                    
                    Default: {default}""");






    private <T> ConfigEntry<T> createOption(String key, T value, String description) {
        ConfigEntry<T> option = new ConfigEntry<>(key, value, description);
        options.put(key, option);
        return option;
    }
    private <T> ConfigEntry<T> createOption(String key, T value) {
        ConfigEntry<T> option = new ConfigEntry<>(key, value);
        options.put(key, option);
        return option;
    }

    public Map<String, ConfigEntry> getOptions() {
        return options;
    }
}
