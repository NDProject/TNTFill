package me.zysea.tntfill;

import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Fill {

    private Player player;
    private int tntUsed = 0;
    private Dispenser dispenser;

    public Fill(Dispenser dispenser){
        this.dispenser = dispenser;
    }


    public int fill(int amount){
        int toFill = countSpace(amount);

        if(toFill == 0)
            return 0;

        dispenser.getInventory().addItem(new ItemStack(Material.TNT, toFill));

        tntUsed+=toFill;
        return toFill;
    }



    public int countSpace(int fillAmount){
        int available = 0;

        for(ItemStack item: dispenser.getInventory()){
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

    public int getUsedTNT(){
        return tntUsed;
    }


}
