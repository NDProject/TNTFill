package me.zysea.tntfill.util;

import me.zysea.tntfill.DispenserUtil;
import org.bukkit.Chunk;
import org.bukkit.block.Dispenser;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerUtil extends DispenserUtil {

    @Override
    public Collection<Dispenser> getDispensers(org.bukkit.entity.Player player, int radius) {
        Set<Dispenser> result = new HashSet<>();
        for(Chunk chunk: getChunks(player.getLocation().getChunk(), 2)){
            result.addAll(Arrays.stream(chunk.getTileEntities()).filter(state -> state instanceof Dispenser)
                    .map(state -> (Dispenser)state).collect(Collectors.toSet()));
        }

        return result;
    }

    public List<Chunk> getChunks(Chunk centerChunk, int radius){
        List<Chunk> chunks = new ArrayList<>();
        for (int x = centerChunk.getX()-radius; x < centerChunk.getX() + radius; x++) {
            for (int z = centerChunk.getZ()-radius; z < centerChunk.getZ() + radius; z++) {
                Chunk chunk = centerChunk.getWorld().getChunkAt(x, z);
                chunks.add(chunk);
            }
        }
        return chunks;
    }

}
