# PermissionLoreAddon
This is an addon for the SimplePets v5 plugin.
This addon adds a lore to the pet items if they do/don't have permission or if they have purchased the pet on the server.

### Default configuration (Located in `plugins/SimplePets/Addons/config/PermissionLore.yml`)
```yaml
# Should the addon check the players purchased pet list instead of their permissions?
# 
# Default: false
check-purchase-list-instead: true

missing-permission:
  lore:
    # If the player does not have permission to this pet should the text below be added to the pets item?
    # 
    # NOTE: This option only works if the 'Only-Show-Pets-Player-Can-Access' is set to false in the SimplePets config file
    # 
    # Default: true
    enabled: true
    # This list of text gets added to the pets item if they do not have permission for the pet
    # 
    # NOTE: This requires the 'missing-permission' lore to be enabled
    # 
    # Placeholders:
    # - {player} = the players name
    # - {type} = the pets name
    # 
    # Default: [&cSorry &7{player}, &cYou do not have permission, &cfor this pet :(]
    text:
    - '&cSorry &7{player}'
    - '&cYou do not have permission'
    - '&cfor this pet :('

has-permission:
  lore:
    # If the player does have permission to this pet should the text below be added to the pets item?
    # 
    # Default: false
    enabled: false
    # This list of text gets added to the pets item if they do not have permission for the pet
    # 
    # NOTE: This requires the 'has-permission' lore to be enabled
    # 
    # Placeholders:
    # - {player} = the players name
    # - {type} = the pets name
    # 
    # Default: []
    text: []

```