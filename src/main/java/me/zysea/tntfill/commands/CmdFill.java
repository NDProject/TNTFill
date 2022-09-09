package me.zysea.tntfill.commands;

import me.zysea.tntfill.tntholders.InventoryWrapper;
import me.zysea.tntfill.tntholders.TNTHolder;
import me.zysea.tntfill.tntholders.VaultWrapper;

import me.zysea.tntfill.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdFill implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player))
            return true;

        if(args.length == 0 || !args[0].chars().allMatch(Character::isDigit)){
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.missingArgument));
            return true;
        }

        Player player = (Player)commandSender;
        TNTHolder tntHolder;

        if(Settings.useVault && args.length > 1 && args[1].equalsIgnoreCase("pay")
                && commandSender.hasPermission("tntfill.pay"))
            tntHolder = new VaultWrapper(Bukkit.getOfflinePlayer(player.getUniqueId()), Settings.tntCost);
        else tntHolder = new InventoryWrapper(player.getInventory());


        FillAction fillCommand = new FillAction(player, tntHolder);
        fillCommand.perform(Integer.parseInt(args[0]));
        return false;
    }
}
