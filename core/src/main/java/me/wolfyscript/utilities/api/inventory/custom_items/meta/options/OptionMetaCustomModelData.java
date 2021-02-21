package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OptionMetaCustomModelData extends SimpleMetaOption {

    public static final Creator<OptionMetaCustomModelData> CREATOR = new Creator<OptionMetaCustomModelData>() {
        @Override
        public OptionMetaCustomModelData create(CustomItem customItem) {
            return new OptionMetaCustomModelData();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("custom_model_data");
        }
    };

    public OptionMetaCustomModelData() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        if (customItem.getItemMeta().hasCustomModelData()) {
            return thatMeta.hasCustomModelData() && customItem.getItemMeta().getCustomModelData() == thatMeta.getCustomModelData();
        } else return !thatMeta.hasCustomModelData();
    }
}
