package me.pljr.chatcontroller.managers;

import lombok.AllArgsConstructor;
import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.objects.ChatPlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

@AllArgsConstructor
public class PlayerManager {
    private final static int AUTOSAVE = 12000;

    private final HashMap<UUID, ChatPlayer> players = new HashMap<>();
    private final JavaPlugin plugin;
    private final QueryManager queryManager;
    private final boolean cachePlayers;

    public void getPlayer(UUID uuid, Consumer<ChatPlayer> consumer){
        if (!players.containsKey(uuid)){
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                ChatPlayer player = queryManager.loadPlayer(uuid);
                setPlayer(uuid, player);
                consumer.accept(player);
            });
        }else{
            consumer.accept(players.get(uuid));
        }
    }

    public void setPlayer(UUID uuid, ChatPlayer player){
        players.put(uuid, player);
    }

    public void savePlayer(UUID uuid){
        queryManager.savePlayer(players.get(uuid));
        if (!cachePlayers) players.remove(uuid);
    }

    public void initAutoSave(){
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            ChatController.log.info("Saving players..");
            for (Map.Entry<UUID, ChatPlayer> entry : players.entrySet()){
                savePlayer(entry.getKey());
            }
            ChatController.log.info("All players were saved.");
        }, AUTOSAVE, AUTOSAVE);
    }
}
