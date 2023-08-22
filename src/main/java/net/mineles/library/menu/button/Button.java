package net.mineles.library.menu.button;

import com.cryptomorin.xseries.XSound;
import net.mineles.library.menu.misc.ClickResult;
import net.mineles.library.menu.misc.contexts.ClickContext;
import net.mineles.library.menu.misc.contexts.OpenContext;
import net.mineles.library.node.Node;
import net.mineles.library.property.AttributeMap;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface Button {
    @NotNull
    static DefaultButton.DefaultBuilder newBuilder() {
        return DefaultButton.newBuilder();
    }

    @NotNull ClickResult click(@NotNull ClickContext context);

    @NotNull Map<Integer, ItemStack> createItemStacks(@NotNull OpenContext context);

    @NotNull AttributeMap getAttributes();

    @Nullable <T> T getAttribute(@NotNull ButtonAttributes attribute);

    @Nullable <T> T getAttribute(@NotNull ButtonAttributes attribute, @NotNull Class<T> type);

    @NotNull Node getNode();

    @NotNull String getName();

    @NotNull ButtonType getType();

    int[] getSlots();

    boolean hasSlot(int slot);

    @Nullable XSound getClickSound();
}
