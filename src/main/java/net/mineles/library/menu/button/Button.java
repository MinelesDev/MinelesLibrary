package net.mineles.library.menu.button;

import com.cryptomorin.xseries.XSound;
import net.mineles.library.menu.misc.ClickResult;
import net.mineles.library.menu.misc.contexts.ClickContext;
import net.mineles.library.node.Node;
import net.mineles.library.property.AttributeMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Button {
    @NotNull ClickResult click(@NotNull ClickContext context);

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
