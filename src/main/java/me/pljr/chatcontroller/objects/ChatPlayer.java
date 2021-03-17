package me.pljr.chatcontroller.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class ChatPlayer {
    private final UUID uniqueId;
    private boolean spy;
    private boolean ignoring;
    private List<UUID> ignoreList;

    public ChatPlayer(UUID uniqueId){
        this.uniqueId = uniqueId;
    }
}
