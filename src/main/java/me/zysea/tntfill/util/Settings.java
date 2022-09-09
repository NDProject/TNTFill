package me.zysea.tntfill.util;

import org.bukkit.configuration.file.FileConfiguration;

public class Settings {

    public static boolean useFactions = false;
    public static boolean useVault = false;
    public static boolean fillInHostile = false;
    public static boolean fillInAlly = true;

    public static String normalFill = "&9&l(!) &bYou've filled {dispensers} dispensers!";
    public static String payFill = "&9&l(!) &bYou've filled {dispensers} dispensers/n&bUsed TNT: &7{tntused}/n&bTotal cost: &7${cost}";
    public static String tntBankFill = "&9&l(!) &bYou've filled {dispensers} dispensers, your faction has {tntbank} tnt left.";
    public static String insufficientTNT = "&9&l(!) &bInsufficient TNT";
    public static String insufficientFunds = "&9&l(!) &bInsufficient funds";
    public static String missingArgument = "&9&l(!) &bMissing expected argument: <amount>";
    public static double tntCost = 59.5;

    public static void load(FileConfiguration config){
        useVault = config.getBoolean("useVault");
        fillInHostile = config.getBoolean("fillInHostile");
        fillInAlly = config.getBoolean("fillInAlly");
        normalFill = config.getString("normalFill").replace("/n", "\n");
        payFill = config.getString("payFill").replace("/n", "\n");
        tntBankFill = config.getString("tntBankFill").replace("/n", "\n");
        insufficientFunds = config.getString("insufficientFunds");
        insufficientTNT = config.getString("insufficientTNT");
        missingArgument = config.getString("missingArgument");
        tntCost = config.getDouble("tntCost");
    }

}
