package net.mineles.library.configuration.serializers;

import net.mineles.library.redis.RedisCredentials;
import org.checkerframework.checker.nullness.qual.Nullable;
import net.mineles.library.libs.configurate.ConfigurationNode;
import net.mineles.library.libs.configurate.serialize.SerializationException;
import net.mineles.library.libs.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;

public final class RedisCredentialsAdapter implements TypeSerializer<RedisCredentials> {
    public static final RedisCredentialsAdapter INSTANCE = new RedisCredentialsAdapter();

    private RedisCredentialsAdapter() {}

    @Override
    public RedisCredentials deserialize(Type type, ConfigurationNode node) throws SerializationException {
        if (!node.isMap()) {
            throw new SerializationException("Expected a map");
        }

        return RedisCredentials.fromNode(node);
    }

    @Override
    public void serialize(Type type, @Nullable RedisCredentials obj, ConfigurationNode node) throws SerializationException {
        throw new SerializationException("Cannot serialize RedisCredentials");
    }
}
