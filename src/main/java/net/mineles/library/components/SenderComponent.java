/*
 * MIT License
 *
 * Copyright (c) 2023 MinelesNetwork
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.mineles.library.components;

import net.mineles.library.utils.text.PlaceholderParser;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class SenderComponent {
    private final @NotNull CommandSender sender;

    private SenderComponent(@NotNull CommandSender sender) {
        this.sender = sender;
    }

    @NotNull
    public static SenderComponent of(@NotNull CommandSender sender) {
        return new SenderComponent(sender);
    }

    public @NotNull CommandSender getHandle() {
        return this.sender;
    }

    public void sendMessage(@NotNull String message) {
        sendMessage(message, Map.of());
    }

    public void sendMessage(@NotNull String message,
                            @NotNull Map<String, String> placeholders) {
        Component component = PlaceholderParser.parseComponent(message, placeholders);
        sendMessage(component);
    }

    public void sendMessage(@NotNull List<String> messages) {
        messages.forEach(this::sendMessage);
    }

    public void sendMessage(@NotNull List<String> messages,
                            @NotNull Map<String, String> placeholders) {
        messages.forEach(message -> sendMessage(message, placeholders));
    }

    public void sendMessage(@NotNull Component component) {
        this.sender.sendMessage(component);
    }
}
