package me.zysea.tntfill.commands;

import me.zysea.factions.util.backend.Messages;
import me.zysea.tntfill.tntholders.TNTBankWrapper;
import me.zysea.tntfill.tntholders.TNTHolder;
import me.zysea.factions.commands.internal.Argument;
import me.zysea.factions.commands.internal.SubCommand;
import me.zysea.factions.faction.FPlayer;
import me.zysea.tntfill.util.Settings;
import org.bukkit.entity.Player;

public class CmdTNTFill extends SubCommand {

    public CmdTNTFill() {
        super("tntfill", "Fill dispensers with TNT", "fill");
        setPermission("tntfill.factions");
        setRequiresFaction(true);
        setRolePermission("tnt");
        setArgument(0, new Argument("amount", true, Integer.class, null));
    }

    @Override
    public void onCommand(Player player, FPlayer fPlayer, String[] args) {
        if(args.length == 0 || !args[0].chars().allMatch(Character::isDigit)){
            Messages.send( player, Settings.missingArgument);
            return;
        }

        TNTHolder tntHolder = new TNTBankWrapper(fPlayer.getFaction());
        FillAction fillCommand = new FillAction(player, tntHolder);
        fillCommand.perform(Integer.parseInt(args[0]));
    }

}
