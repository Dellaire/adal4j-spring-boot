package microsoft.graph.spring.authorization.code.grant.dist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * {@code ClientProperties} loads and provides all configurable properties of
 * this application. It is made to be injected into classes, where the
 * properties are needed.
 */
@Component
public class ClientProperties {
    @Value("${client-id}")
    private String clientId;

    @Value("${client-secret}")
    private String clientSecret;

    @Value("${redirect-url-host}")
    private String redirectUrlHost;

    @Value("${redirect-url-endpoint}")
    private String redirectUrlEndpoint;

    public String getClientId() {
        return this.clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public String getRedirectUrlHost() {
        return this.redirectUrlHost;
    }

    public String getRedirectUrlEndpoint() {
        return this.redirectUrlEndpoint;
    }
}
