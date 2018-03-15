package microsoft.graph.spring.authorization.code.grant.graph;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import microsoft.graph.spring.authorization.code.grant.dist.ClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The {@code Adal4jTokenProvider} uses the <i>Microsoft Azure Active Directory
 * Authentication Library</i> (ADAL) to obtain an access token.
 *
 * @see https://github.com/AzureAD/azure-activedirectory-library-for-java
 */
@Component
@Profile("adal4j")
public class Adal4jTokenProvider implements MicrosoftGraphTokenProvider {
    private final static String URL_TO_ACQUIRE_TOKEN = "https://login.microsoftonline.com/common/oauth2/token";
    private final static String RESOURCE = "https://graph.microsoft.com";

    @Autowired
    private ClientProperties clientProperties;

    @Override
    public String getAccessToken(final String authorizationCode) {
        final AuthenticationContext context;
        AuthenticationResult        result = null;
        final ExecutorService       service;

        try {
            service = Executors.newFixedThreadPool(1);
            context = new AuthenticationContext(URL_TO_ACQUIRE_TOKEN, true, service);
            final ClientCredential credential = new ClientCredential(this.clientProperties.getClientId(),
                                                                     this.clientProperties.getClientSecret());

            final Future<AuthenticationResult> future = context.acquireTokenByAuthorizationCode(authorizationCode,
                                                                                                new URI(this.clientProperties
                                                                                                                .getRedirectUrlHost() +
                                                                                                        this.clientProperties
                                                                                                                .getRedirectUrlEndpoint()),
                                                                                                credential, RESOURCE, null);

            result = future.get();
        }
        catch (final Exception e) {
            e.printStackTrace();
        }

        return result.getAccessToken();
    }
}
