package com.cargowhale.docker.client.core;

import com.spotify.docker.client.UnixConnectionSocketFactory;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.URI;

public class UnixComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    private static final String UNIX_SCHEME = "unix";
    private static final String SOCKET_FACTORY_REGISTRY = "http.socket-factory-registry";

    private final String dockerUri;

    public UnixComponentsClientHttpRequestFactory(final String dockerUri) {
        this.dockerUri = dockerUri;
    }

    @Override
    protected HttpContext createHttpContext(final HttpMethod httpMethod, final URI uri) {
        HttpClientContext context = HttpClientContext.create();

        final RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder
            .<ConnectionSocketFactory>create()
            .register("https", SSLConnectionSocketFactory.getSocketFactory())
            .register("http", PlainConnectionSocketFactory.getSocketFactory());

        if (uri.getScheme().equals(UNIX_SCHEME)) {
            registryBuilder.register(UNIX_SCHEME, buildUnixSocketFactory(URI.create(this.dockerUri)));
        }

        context.setAttribute(SOCKET_FACTORY_REGISTRY, registryBuilder.build());

        return context;
    }

    UnixConnectionSocketFactory buildUnixSocketFactory(final URI uri) {
        return new UnixConnectionSocketFactory(uri);
    }

    @Override
    protected HttpUriRequest createHttpUriRequest(final HttpMethod httpMethod, final URI uri) {
        return super.createHttpUriRequest(httpMethod, sanitizeUriForRequest(uri));
    }

    private URI sanitizeUriForRequest(final URI uri) {
        String newUriString = uri.toString().replace("unix:///var/run/docker.sock", "unix://localhost:80");
        return URI.create(newUriString);
    }
}
