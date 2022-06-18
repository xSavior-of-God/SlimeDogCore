package dev.ratas.slimedogcore.impl.reload;

import java.util.ArrayList;
import java.util.List;

import dev.ratas.slimedogcore.api.reload.ReloadException;
import dev.ratas.slimedogcore.api.reload.SDCReloadManager;
import dev.ratas.slimedogcore.api.reload.SDCReloadable;

public class ReloadManager implements SDCReloadManager {
    private final List<SDCReloadable> reloadables = new ArrayList<>();

    @Override
    public void register(SDCReloadable reloadable) {
        reloadables.add(reloadable);
    }

    @Override
    public void reload() throws ReloadException {
        for (SDCReloadable reloadable : reloadables) {
            reloadable.reload();
        }
    }

}
