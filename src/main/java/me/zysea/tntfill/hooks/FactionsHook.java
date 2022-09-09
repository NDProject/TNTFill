package me.zysea.tntfill.hooks;

import me.zysea.factions.FPlugin;
import me.zysea.factions.api.FactionsApi;
import me.zysea.factions.faction.role.Permissions;
import me.zysea.tntfill.commands.CmdTNT;
import me.zysea.tntfill.commands.CmdTNTFill;
import me.zysea.tntfill.util.Settings;
import org.bukkit.Bukkit;

public class FactionsHook {

    public static void register(){
        FactionsApi.registerPermission("tnt", "Add & take TNT");
        Bukkit.getLogger().info("Using factions, registered role permission: tnt | commands: /f tnt & /f tntfill.");
        Settings.useFactions = true;
        FactionsApi.addSubCommand(new CmdTNTFill(), 3);
        FactionsApi.addSubCommand(new CmdTNT(), 3);
    }

    public static void deregister(){
        FPlugin.getInstance().getfCommand().delSub("tnt");
        FPlugin.getInstance().getfCommand().delSub("tntfill");
        Permissions.PERMISSIONS.remove("tnt");
    }


}
