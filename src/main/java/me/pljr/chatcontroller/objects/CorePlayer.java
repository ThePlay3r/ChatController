package me.pljr.chatcontroller.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CorePlayer {
    private boolean spy;
    private boolean ignoring;
    private List<UUID> ignoreList;

    public CorePlayer(){
        this.ignoring = false;
        this.ignoreList = new ArrayList<>();
    }

    public boolean isSpy() {
        return spy;
    }
    public void setSpy(boolean spy) {
        this.spy = spy;
    }

    public boolean isIgnoring() {
        return ignoring;
    }
    public void setIgnoring(boolean ignoring) {
        this.ignoring = ignoring;
    }

    public List<UUID> getIgnoreList() {
        return ignoreList;
    }
    public void setIgnoreList(List<UUID> ignoreList) {
        this.ignoreList = ignoreList;
    }
}
