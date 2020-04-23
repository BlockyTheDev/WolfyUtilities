package me.wolfyscript.utilities.api.custom_items;

import me.wolfyscript.utilities.api.config.ConfigAPI;
import me.wolfyscript.utilities.api.config.JsonConfiguration;
import me.wolfyscript.utilities.api.utils.NamespacedKey;
import org.bukkit.Material;

public class CustomConfig extends JsonConfiguration {

    private String namespace;
    private String id;
    private NamespacedKey namespacedKey;


    public CustomConfig(ConfigAPI configAPI, String namespace, String name, String path, String defaultPath, String defaultName, boolean override) {
        super(configAPI, path, name, defaultPath, defaultName, override);
        this.namespace = namespace;
        this.namespacedKey = new NamespacedKey(namespace, name);
        this.id = namespace + ":" + name;
        setPathSeparator('.');
    }

    public CustomConfig(String jsonData, ConfigAPI configAPI, String namespace, String name, String defaultPath, String defaultName) {
        super(jsonData, configAPI, name, defaultPath, defaultName);
        this.namespace = namespace;
        this.namespacedKey = new NamespacedKey(namespace, name);
        this.id = namespace + ":" + name;
        setPathSeparator('.');
    }

    public CustomConfig(ConfigAPI configAPI, String namespace, String name, String defaultPath, String defaultName) {
        super(configAPI, name, defaultPath, defaultName);
        this.namespace = namespace;
        this.namespacedKey = new NamespacedKey(namespace, name);
        this.id = namespace + ":" + name;
        setPathSeparator('.');
    }

    /*
    Should no longer be used!
    Won't be used in the upcoming updates! fileType variable is unused, due to the change to Json only config.
     */
    @Deprecated
    public CustomConfig(ConfigAPI configAPI, String namespace, String name, String path, String defaultPath, String defaultName, boolean override, String fileType) {
        this(configAPI, namespace, name, path, defaultPath, defaultName, override);
    }

    public NamespacedKey getNamespacedKey() {
        return namespacedKey;
    }

    @Deprecated
    public String getNamespace() {
        return namespace;
    }

    @Deprecated
    public String getId() {
        return id;
    }

    public void saveCustomItem(String path, CustomItem customItem) {
        if (customItem != null) {
            if (customItem.getNamespacedKey() != null) {
                set(path + ".item_key", customItem.getNamespacedKey().toString());
                set(path + ".custom_amount", customItem.getAmount() != CustomItems.getCustomItem(customItem.getNamespacedKey()).getAmount() ? customItem.getAmount() : 0);
            } else {
                setItem(path + ".item", customItem.getItemStack());
            }
        } else {
            setItem(path + ".item", null);
        }
    }

    public CustomItem getCustomItem(String path) {
        String id = getString(path + ".item_key");
        if (id != null && !id.isEmpty()) {
            CustomItem customItem = CustomItems.getCustomItem(new NamespacedKey(id.split(":")[0], id.split(":")[1]));
            int i = getInt(path + ".custom_amount");
            if (i != 0) {
                customItem.setAmount(i);
            }
            return customItem;
        } else if (getItem(path + ".item") != null) {
            return new CustomItem(getItem(path + ".item"));
        }
        return new CustomItem(Material.AIR);
    }

    public void linkToFile(String namespace, String name, String path) {
        this.namespace = namespace;
        this.setName(name);
        this.id = namespace + ":" + name;
        linkToFile(path + "/" + name);
    }
}
