package net.mineles.library.docker.config;

import org.spongepowered.configurate.ConfigurationNode;

import java.time.Duration;

public record DockerConfig(
        String dockerHost,
        String registryUsername,
        String registryPassword,
        String registryEmail,
        String registryUrl,
        Duration connectionTimeout
) {

    public static DockerConfig fromNode(ConfigurationNode node) {
        return new DockerConfigBuilder()
                .dockerHost(node.node("host").getString())
                .registryUsername(node.node("user-name").getString())
                .registryPassword(node.node("password").getString())
                .registryEmail(node.node("mail").getString())
                .registryUrl(node.node("url").getString())
                .build();
    }

    public static DockerConfigBuilder builder() {
        return new DockerConfigBuilder();
    }
}
