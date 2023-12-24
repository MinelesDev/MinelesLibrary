/*
 * MIT License
 *
 * Copyright (c) 2023 Kafein
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

package net.mineles.library.command.context;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public final class CommandContextMap {
    private static final CommandContextMap DEFAULTS;

    static {
        DEFAULTS = new CommandContextMap();
        DEFAULTS.register(String.class, (sender, s) -> s);
        DEFAULTS.register(Integer.class, DefaultCommandContexts.INTEGER);
        DEFAULTS.register(Double.class, DefaultCommandContexts.DOUBLE);
        DEFAULTS.register(Float.class, DefaultCommandContexts.FLOAT);
        DEFAULTS.register(Long.class, DefaultCommandContexts.LONG);
        DEFAULTS.register(Player.class, DefaultCommandContexts.PLAYER);
    }

    private final Map<Class<?>, CommandContext<?>> commandContexts;

    public CommandContextMap() {
        this(Maps.newHashMap());
    }

    public CommandContextMap(Map<Class<?>, CommandContext<?>> commandContexts) {
        this.commandContexts = commandContexts;
    }

    public Map<Class<?>, CommandContext<?>> getCommandContexts() {
        return this.commandContexts;
    }

    @SuppressWarnings("unchecked")
    public <T> @Nullable CommandContext<T> getCommandContext(@NotNull Class<T> clazz) {
        return (CommandContext<T>) this.commandContexts.get(clazz);
    }

    public <T> void register(@NotNull Class<T> clazz, @NotNull CommandContext<T> commandContext) {
        this.commandContexts.put(clazz, commandContext);
    }

    public <T> void unregister(@NotNull Class<T> clazz) {
        this.commandContexts.remove(clazz);
    }

    public static CommandContextMap getDefaults() {
        return DEFAULTS;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CommandContextMap)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        CommandContextMap other = (CommandContextMap) obj;
        return this.commandContexts.equals(other.commandContexts);
    }

    @Override
    public int hashCode() {
        return this.commandContexts.hashCode();
    }

    @Override
    public String toString() {
        return "CommandContextMap{" +
                "commandContexts=" + this.commandContexts +
                "}";
    }
}
