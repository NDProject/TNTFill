package me.zysea.tntfill.commands;

import me.zysea.factions.faction.role.Permissions;
import me.zysea.tntfill.DispenserUtil;
import me.zysea.tntfill.Fill;
import me.zysea.tntfill.tntholders.InventoryWrapper;
import me.zysea.tntfill.tntholders.TNTBankWrapper;
import me.zysea.tntfill.tntholders.TNTHolder;

import me.zysea.tntfill.tntholders.VaultWrapper;
import me.zysea.tntfill.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.stream.Collectors;

public class FillAction {

    private Player player;
    private TNTHolder tntHolder;

    public FillAction(Player player, TNTHolder tntHolder){
        this.player = player;
        this.tntHolder = tntHolder;
    }

    public void perform(int amount){
        int usable = tntHolder.count();
        int used = 0;
        int dispensers = 0;

        boolean bypass = tntHolder instanceof InventoryWrapper && player.getGameMode() == GameMode.CREATIVE;

        if(amount > usable && !bypass) {
            if(tntHolder instanceof VaultWrapper)
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.insufficientFunds));
            else
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.insufficientTNT));

            return;
        }
        
        Collection<Dispenser> disSet = DispenserUtil.getInstance().getDispensers(this.player, 1).stream().filter(b ->
            Math.abs(player.getLocation().getBlockY() - b.getLocation().getBlockY()) <= 20).collect(Collectors.toSet());
        
        for(Dispenser dispenser: disSet){
            if(amount > usable && !bypass)
                break;

            Fill fill = new Fill(dispenser);
            usable-=fill.fill(amount);
            used+=fill.getUsedTNT();
            if(fill.getUsedTNT()>0)
                dispensers++;
        }

        if(!bypass)
            tntHolder.remove(used);

        String msg;

        if(tntHolder instanceof TNTBankWrapper)
            msg = Settings.tntBankFill
                    .replace("{tntused}", String.valueOf(used))
                    .replace("{dispensers}", String.valueOf(dispensers))
                    .replace("{tntbank}", String.valueOf(tntHolder.count()));
        else if(tntHolder instanceof VaultWrapper){
            msg = Settings.payFill
                    .replace("{cost}", String.valueOf(used*Settings.tntCost))
                    .replace("{dispensers}", String.valueOf(dispensers))
                    .replace("{tntused}", String.valueOf(used));
        }else
            msg = Settings.normalFill.replace("{tntused}", String.valueOf(used)).replace("{dispensers}", String.valueOf(dispensers));



        sendMessage(msg.replace("/n", "\n"));
    }

    private void sendMessage(String msg){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

}
