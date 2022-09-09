package me.zysea.tntfill;

import me.zysea.tntfill.util.FactionsUtil;
import me.zysea.tntfill.util.PlayerUtil;
import me.zysea.tntfill.util.Settings;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;

import java.util.Collection;

public abstract class DispenserUtil {

    private static DispenserUtil instance;

    public static DispenserUtil getInstance() {
        if(instance == null)
            instance = Settings.useFactions ?
                    new FactionsUtil() : new PlayerUtil();

        return instance;
    }

    public abstract Collection<Dispenser> getDispensers(Player player, int radius);


}
