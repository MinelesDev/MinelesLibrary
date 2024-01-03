package net.mineles.library.configuration.serializers;

import com.github.dockerjava.api.model.RestartPolicy;
import net.mineles.library.cluster.binding.env.EnvironmentVariableBinding;
import net.mineles.library.cluster.binding.folder.FolderBinding;
import net.mineles.library.cluster.binding.port.PortBinding;
import net.mineles.library.cluster.container.ContainerResourceLimit;
import net.mineles.library.cluster.container.request.CreateContainerRequest;
import net.mineles.library.cluster.container.request.CreateContainerRequestBuilder;
import net.mineles.library.cluster.image.RemoteImageTag;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang.RandomStringUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.List;

public final class CreateContainerRequestAdapter implements TypeSerializer<CreateContainerRequest> {
    public static final CreateContainerRequestAdapter INSTANCE = new CreateContainerRequestAdapter();

    private CreateContainerRequestAdapter() {}

    @Override
    public CreateContainerRequest deserialize(Type type, ConfigurationNode node) throws SerializationException {
        if (!node.isMap()) {
            throw new SerializationException("Expected a map");
        }

        RemoteImageTag imageTag = RemoteImageTag.parse(node.node("image").getString());
        String name = node.node("name").getString() + "-" + RandomStringUtils.random(8);
        //String hostName = node.node("host-name").getString();
        //List<String> commands = node.node("commands").getList(String.class);

        List<EnvironmentVariableBinding> environmentVariableBindings = node.node("environments").getList(String.class, Lists.newArrayList()).stream()
                .map(EnvironmentVariableBinding::fromBinding)
                .toList();
        List<PortBinding> portBindings = node.node("ports").getList(String.class, Lists.newArrayList()).stream()
                .map(PortBinding::fromBinding)
                .toList();
        List<FolderBinding> folderBindings = node.node("volumes").getList(String.class, Lists.newArrayList()).stream()
                .map(FolderBinding::fromBinding)
                .toList();

        RestartPolicy restartPolicy = RestartPolicy.parse(node.node("restart-policy").getString("no"));
        ContainerResourceLimit resourceLimit = node.node("resource-limit").get(ContainerResourceLimit.class);

        return new CreateContainerRequestBuilder(imageTag)
                .name(name)
                //.hostName(hostName)
                //.command(commands)
                .environmentVariables(environmentVariableBindings)
                .ports(portBindings)
                .volumes(folderBindings)
                .restartPolicy(restartPolicy)
                .resourceLimit(resourceLimit)
                .build();
    }

    @Override
    public void serialize(Type type, @Nullable CreateContainerRequest obj, ConfigurationNode node) throws SerializationException {
        throw new SerializationException("Cannot serialize");
    }
}
