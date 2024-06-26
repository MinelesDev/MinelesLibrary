package net.mineles.library.redis.message;

import com.google.gson.JsonObject;
import net.mineles.library.redis.RedisClient;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface MessageListener {
    void onMessage(@NotNull RedisClient client, @NotNull JsonObject message);
}
