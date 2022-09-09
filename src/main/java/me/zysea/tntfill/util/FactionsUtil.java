package me.zysea.tntfill.util;

import me.zysea.factions.objects.Claim;
import me.zysea.tntfill.DispenserUtil;
import me.zysea.factions.api.FactionsApi;
import me.zysea.factions.faction.Faction;
import org.bukkit.Chunk;
import org.bukkit.block.Dispenser;

import java.util.*;
import java.util.stream.Collectors;

public class FactionsUtil extends DispenserUtil {

    @Override
    public Collection<Dispenser> getDispensers(org.bukkit.entity.Player player, int radius) {
        Set<Dispenser> result = new HashSet<>();
        Faction sender = FactionsApi.getFaction(player);
        for(Chunk chunk: getChunks(sender, new Claim(player.getLocation()), 2)){
            result.addAll(Arrays.stream(chunk.getTileEntities()).filter(state -> state instanceof Dispenser)
                    .map(state -> (Dispenser)state).collect(Collectors.toSet()));
        }

        return result;
    }

    private Collection<Chunk> getChunks(Faction sender, Claim center, int radius) {
        Set<Chunk> claims = new HashSet<>();
        for(int x=-radius; x<radius; x++)
            for(int z=-radius; z<radius; z++) {
                Claim claim = center.getRelative(x, z);
                Faction owner = FactionsApi.getOwner(claim);
                if(claim.isOutsideBorder() || owner.isNormal() && !owner.equals(sender) && !Settings.fillInHostile
                || owner.isNormal() && !owner.equals(sender) && owner.isAlliedTo(sender) && !Settings.fillInAlly)
                    continue;

                claims.add(claim.asChunk());
            }

        return claims;
    }


}
