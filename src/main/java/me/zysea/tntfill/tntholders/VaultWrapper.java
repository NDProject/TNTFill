package me.zysea.tntfill.tntholders;

import me.zysea.tntfill.hooks.VaultHook;
import org.bukkit.OfflinePlayer;

public class VaultWrapper extends TNTHolder{

    private OfflinePlayer offlinePlayer;
    private double costPerTNT;

    public VaultWrapper(OfflinePlayer offlinePlayer, double costPerTNT){
        this.offlinePlayer = offlinePlayer;
        this.costPerTNT = costPerTNT;
    }

    @Override
    public boolean remove(int amount) {
        if(VaultHook.getEcon().getBalance(offlinePlayer) < amount*costPerTNT)
            return false;

        return VaultHook.getEcon().withdrawPlayer(offlinePlayer, amount*costPerTNT).transactionSuccess();
    }

    @Override
    public boolean add(int amount) {
        return VaultHook.getEcon().depositPlayer(offlinePlayer, amount*costPerTNT).transactionSuccess();
    }

    @Override
    public int count() {
        setAmount((int) (VaultHook.getEcon().getBalance(offlinePlayer)/costPerTNT));
        return getAmount();
    }
}
