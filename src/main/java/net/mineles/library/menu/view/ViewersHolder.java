package net.mineles.library.menu.view;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.Map;
import java.util.UUID;

public final class ViewersHolder {
    private final Map<UUID, Viewer> viewers;

    public ViewersHolder() {
        this.viewers = Maps.newHashMap();
    }

    public ViewersHolder(Map<UUID, Viewer> viewers) {
        this.viewers = viewers;
    }

    public static ViewersHolder create() {
        return new ViewersHolder();
    }

    public static ViewersHolder create(Map<UUID, Viewer> viewers) {
        return new ViewersHolder(viewers);
    }

    public Map<UUID, Viewer> getViewers() {
        return this.viewers;
    }

    public Map<UUID, Viewer> getViewersSafe() {
        return Maps.newHashMap(this.viewers);
    }

    public @Nullable Viewer getViewer(@NotNull UUID uuid) {
        return this.viewers.get(uuid);
    }

    public Viewer addViewer(@NotNull Viewer viewer) {
        this.viewers.put(viewer.getUniqueId(), viewer);
        return viewer;
    }

    public @UnknownNullability Viewer removeViewer(@NotNull UUID uuid) {
        return this.viewers.remove(uuid);
    }

    public @UnknownNullability Viewer removeViewer(@NotNull Viewer viewer) {
        return this.viewers.remove(viewer.getUniqueId());
    }

    public void clear() {
        this.viewers.clear();
    }

    public boolean containsViewer(@NotNull UUID uuid) {
        return this.viewers.containsKey(uuid);
    }

    public boolean containsViewer(@NotNull Viewer viewer) {
        return this.viewers.containsKey(viewer.getUniqueId());
    }

    public boolean isEmpty() {
        return this.viewers.isEmpty();
    }

    public int size() {
        return this.viewers.size();
    }

    public Map<UUID, Viewer> toMap() {
        return Maps.newHashMap(this.viewers);
    }
}
