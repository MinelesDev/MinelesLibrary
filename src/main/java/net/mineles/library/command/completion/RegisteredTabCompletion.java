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

package net.mineles.library.command.completion;

import java.util.Objects;

public final class RegisteredTabCompletion {
    private final int index;
    private final TabCompletion tabCompletion;

    public RegisteredTabCompletion(int index, TabCompletion tabCompletion) {
        this.index = index;
        this.tabCompletion = tabCompletion;
    }

    public static RegisteredTabCompletion of(int index, TabCompletion tabCompletion) {
        return new RegisteredTabCompletion(index, tabCompletion);
    }

    public int getIndex() {
        return this.index;
    }

    public TabCompletion getTabCompletion() {
        return this.tabCompletion;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RegisteredTabCompletion)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        RegisteredTabCompletion other = (RegisteredTabCompletion) obj;
        return Objects.equals(this.index, other.index) && Objects.equals(this.tabCompletion, other.tabCompletion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.index, this.tabCompletion);
    }

    @Override
    public String toString() {
        return "RegisteredTabCompletion{" +
                "index=" + this.index +
                ", tabCompletion=" + this.tabCompletion +
                "}";
    }
}
