package me.pndte.arenaz.core.other;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class ArmorSet {
    private final ItemStack _helmet;
    private final ItemStack _chestPlate;
    private final ItemStack _leggings;
    private final ItemStack _boots;

    public ArmorSet(ItemStack _helmet, ItemStack _chestPlate, ItemStack _leggings, ItemStack _boots) {
        this._helmet = _helmet;
        this._chestPlate = _chestPlate;
        this._leggings = _leggings;
        this._boots = _boots;
    }

    public ItemStack helmet() {return _helmet;}
    public ItemStack chestPlate() {return _chestPlate;}
    public ItemStack leggings() {return _leggings;}
    public ItemStack boots() {return _boots;}
}
