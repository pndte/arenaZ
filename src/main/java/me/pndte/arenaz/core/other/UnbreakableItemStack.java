package me.pndte.arenaz.core.other;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class UnbreakableItemStack extends ItemStack {
    public UnbreakableItemStack(Material material, int count){
        super(material, count);
        var meta = getItemMeta();
        meta.setUnbreakable(true);
        setItemMeta(meta);
    }

    public UnbreakableItemStack(Material material){
        this(material, 1);
    }
}
