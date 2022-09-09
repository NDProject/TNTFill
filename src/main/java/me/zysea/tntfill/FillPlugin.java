package me.zysea.tntfill;

import me.zysea.tntfill.commands.CmdFill;
import me.zysea.tntfill.hooks.FactionsHook;
import me.zysea.tntfill.util.Settings;
import me.zysea.tntfill.hooks.VaultHook;
import org.bukkit.plugin.java.JavaPlugin;

public class FillPlugin extends JavaPlugin {

    @Override
    public void onEnable(){
        if(getServer().getPluginManager().isPluginEnabled("FactionsBlue"))
            FactionsHook.register();

        saveDefaultConfig();
        Settings.load(getConfig());

        getCommand("tntfill").setExecutor(new CmdFill());

        if(Settings.useVault) VaultHook.setupEconomy();
    }

    @Override
    public void onDisable(){
        if(Settings.useFactions)
            FactionsHook.deregister();
    }

}
