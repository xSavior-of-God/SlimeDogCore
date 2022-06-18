package dev.ratas.slimedogcore.impl.reload;

import java.util.LinkedHashSet;
import java.util.Set;

import dev.ratas.slimedogcore.api.reload.ReloadException;
import dev.ratas.slimedogcore.api.reload.SDCReloadManager;
import dev.ratas.slimedogcore.api.reload.SDCReloadable;

public class ReloadManager implements SDCReloadManager {
    // order may be important
    // but we do not want the same instance appearing multiple times
    private final Set<SDCReloadable> reloadables = new LinkedHashSet<SDCReloadable>();

    @Override
    public void register(SDCReloadable reloadable) {
        if (reloadables.contains(reloadable)) {
            throw new IllegalStateException();
        }
        reloadables.add(reloadable);
    }

    @Override
    public void reload() throws ReloadException {
        for (SDCReloadable reloadable : reloadables) {
            reloadable.reload();
        }
    }

}
