package me.zysea.tntfill.util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InventoryUtil {

    public static int countSpace(Inventory inventory, int fillAmount){
        int available = 0;

        for(int i=0; i<35; i++){
            ItemStack item = inventory.getItem(i);

            if(item == null){
                available+=64;
                continue;
            }

            if(item.getType() != Material.TNT || item.getAmount() >= 64)
                continue;

            available+=64-item.getAmount();
        }

        return available > fillAmount ? fillAmount : available;
    }
}
