package net.mineles.library.menu;

import com.google.common.collect.Maps;
import net.kyori.adventure.text.Component;
import net.mineles.library.utils.text.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public final class InventoryProperties {
    private final @NotNull String title;
    private final @NotNull InventoryType type;
    private final int size;

    InventoryProperties(@NotNull String title,
                        @NotNull InventoryType type,
                        int size) {
        this.title = title;
        this.type = type;
        this.size = size;
    }

    InventoryProperties(@NotNull String title,
                        @NotNull InventoryType type) {
        this(title, type, type.getDefaultSize());
    }

    InventoryProperties(@NotNull String title,
                        int size) {
        this(title, InventoryType.CHEST, size);
    }

    InventoryProperties(@NotNull String title) {
        this(title, InventoryType.CHEST);
    }

    public @NotNull Inventory createInventory() {
        return createInventory(ComponentSerializer.deserialize(getTitle(), Maps.newHashMap()));
    }

    public @NotNull Inventory createInventory(@NotNull Map<String, String> placeholders) {
        Component title = ComponentSerializer.deserialize(getTitle(), placeholders);
        return createInventory(title);
    }

    public @NotNull Inventory createInventory(@NotNull Player player,
                                              @NotNull Map<String, String> placeholders) {
        Component title = ComponentSerializer.deserialize(player, getTitle(), placeholders);
        return createInventory(title);
    }

    public @NotNull Inventory createInventory(@NotNull Component parsedTitle) {
        return this.type == InventoryType.CHEST
                ? Bukkit.createInventory(null, this.size, parsedTitle)
                : Bukkit.createInventory(null, this.type, parsedTitle);
    }

    public @NotNull String getTitle() {
        return this.title;
    }

    public @NotNull InventoryType getType() {
        return this.type;
    }

    public int getSize() {
        return this.size;
    }

    public static @NotNull Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String title;
        private InventoryType type;
        private int size;

        private Builder() {}

        public @Nullable String title() {
            return this.title;
        }

        public @NotNull Builder title(@NotNull String title) {
            this.title = title;
            return this;
        }

        public @Nullable InventoryType type() {
            return this.type;
        }

        public @NotNull Builder type(@NotNull InventoryType type) {
            this.type = type;
            return this;
        }

        public @NotNull Builder type(@Nullable String type) {
            if (type != null) {
                this.type = InventoryType.valueOf(type);
            }
            return this;
        }

        public int size() {
            return this.size;
        }

        public  @NotNull Builder size(int size) {
            this.size = size;
            return this;
        }

        public @NotNull InventoryProperties build() {
            if (this.type == null) {
                this.type = InventoryType.CHEST;
            }

            return new InventoryProperties(this.title, this.type, this.size);
        }
    }
}
