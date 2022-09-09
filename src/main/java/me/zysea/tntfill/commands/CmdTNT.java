package me.zysea.tntfill.commands;

import me.zysea.factions.util.backend.Messages;
import me.zysea.tntfill.util.InventoryUtil;
import me.zysea.tntfill.tntholders.InventoryWrapper;
import me.zysea.tntfill.tntholders.TNTBankWrapper;
import me.zysea.factions.commands.internal.Argument;
import me.zysea.factions.commands.internal.SubCommand;
import me.zysea.factions.faction.FPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CmdTNT extends SubCommand {

    public CmdTNT() {
        super("tnt", "Add or take tnt", "tntbank");
        setPermission("tntfill.factions");
        setRequiresFaction(true);
        setRolePermission("tnt");
        setArgument(0, new Argument("add/take", true, String.class, null));
        setArgument(1, new Argument("amount", true, Integer.class, null));
    }

    @Override
    public void onCommand(Player player, FPlayer fPlayer, String[] args) {
        int amount = Integer.parseInt(args[1]);
        TNTBankWrapper tntBank = new TNTBankWrapper(fPlayer.getFaction());
        InventoryWrapper inventory = new InventoryWrapper(player.getInventory());

        if(args[0].equalsIgnoreCase("add")){
            int tnt = inventory.count();

            if(tnt < amount){
                Messages.send(player, "&9&l(!) &bYou cannot add this much TNT to the bank.");
                return;
            }

            inventory.remove(amount);
            tntBank.add(amount);
            Messages.send(player, "&9&l(!) &bAdded " + amount + " to the tnt bank, your faction now has " + tntBank.count() + " TNT!");
        }

        if(args[0].equalsIgnoreCase("take")){
            int tnt = tntBank.count();

            int space = InventoryUtil.countSpace(player.getInventory(), amount);
            Bukkit.broadcastMessage("Space: " + space);
            if(amount > space)
                amount = space;

            if(amount > tnt){
                Messages.send(player, "&9&l(!) &bYour faction only has " + tnt + " TNT left.");
                return;
            }

            inventory.add(amount);
            tntBank.remove(amount);
            Messages.send(player, "&9&l(!) &bTook " + amount + " from the tnt bank, your faction has " + (tnt-amount) + " TNT left.");
        }
    }
}
