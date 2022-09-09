package me.zysea.tntfill.tntholders;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryWrapper extends TNTHolder {

    private Inventory inventory;

    public InventoryWrapper(Inventory inventory){
        this.inventory = inventory;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public boolean remove(int amount){
        inventory.removeItem(new ItemStack(Material.TNT, amount));
        return true;
    }

    public boolean add(int amount){
        inventory.addItem(new ItemStack(Material.TNT, amount));
        return true;
    }

    @Override
    public int count() {
        int count = 0;
        for (ItemStack item : inventory) {
            if (item != null && item.getType() == Material.TNT)
                count += item.getAmount();
        }
        setAmount(count);
        return count;
    }
}
