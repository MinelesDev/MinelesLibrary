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

package net.mineles.library.command;

import com.google.common.collect.ImmutableList;
import net.mineles.library.command.completion.RegisteredTabCompletion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class AbstractCommand implements Command {
    private final CommandProperties properties;
    private final List<Command> subCommands;
    private final List<RegisteredTabCompletion> tabCompletions;

    public AbstractCommand(CommandProperties properties) {
        this(properties, ImmutableList.of());
    }

    public AbstractCommand(CommandProperties properties, List<Command> subCommands) {
        this(properties, subCommands, ImmutableList.of());
    }

    public AbstractCommand(CommandProperties properties, List<Command> subCommands, List<RegisteredTabCompletion> tabCompletions) {
        this.properties = properties;
        this.subCommands = subCommands;
        this.tabCompletions = tabCompletions;
    }

    @Override
    public CommandProperties getProperties() {
        return this.properties;
    }

    @Override
    public String getName() {
        return this.properties.getName();
    }

    @Override
    public List<String> getAliases() {
        return this.properties.getAliases();
    }

    @Override
    public boolean isAlias(@NotNull String alias) {
        return this.properties.getAliases().stream().anyMatch(a -> a.equalsIgnoreCase(alias));
    }

    @Override
    public String getDescription() {
        return this.properties.getDescription();
    }

    @Override
    public String getUsage() {
        return this.properties.getUsage();
    }

    @Override
    public @Nullable String getPermission() {
        return this.properties.getPermission();
    }

    @Override
    public List<Command> getSubCommands() {
        return this.subCommands;
    }

    @Override
    public Command findSubCommand(@NotNull String sub) {
        return this.subCommands.stream()
                .filter(command -> command.isAlias(sub))
                .findFirst()
                .orElse(this);
    }

    @Override
    public Command findSubCommand(@NotNull String... subs) {
        checkArgument(subs.length > 0, "Sub commands cannot be empty.");

        if (subs.length == 1) {
            return findSubCommand(subs[0]);
        } else {
            String[] newSubs = Arrays.copyOfRange(subs, 1, subs.length);
            return findSubCommand(subs[0]).findSubCommand(newSubs);
        }
    }

    @Override
    public int findSubCommandIndex(@NotNull String... subs) {
        checkArgument(subs.length > 0, "Sub commands cannot be empty.");

        for (int i = 0; i < subs.length; i++) {
            if (!this.isAlias(subs[i])) {
                return i;
            }
        }
        return subs.length;
    }

    @Override
    public List<RegisteredTabCompletion> getTabCompletions() {
        return this.tabCompletions;
    }

    @Override
    public List<RegisteredTabCompletion> getTabCompletions(int index) {
        return this.tabCompletions.stream()
                .filter(registeredTabCompletion -> registeredTabCompletion.getIndex() == index)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Command)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Command command = (Command) obj;
        return Objects.equals(this.properties, command.getProperties())
                && Objects.equals(this.subCommands, command.getSubCommands())
                && Objects.equals(this.tabCompletions, command.getTabCompletions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.properties, this.subCommands, this.tabCompletions);
    }

    @Override
    public String toString() {
        return "AbstractCommand{" +
                "properties=" + this.properties +
                ", subCommands=" + this.subCommands +
                ", tabCompletions=" + this.tabCompletions +
                "}";
    }
}
