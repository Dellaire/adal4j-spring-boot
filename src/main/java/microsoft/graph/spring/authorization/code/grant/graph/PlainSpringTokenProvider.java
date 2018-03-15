package microsoft.graph.spring.authorization.code.grant.graph;

import microsoft.graph.spring.authorization.code.grant.dist.ClientProperties;
import microsoft.graph.spring.authorization.code.grant.filter.TokenContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * The {@code PlainSpringTokenProvider} uses plain Java, Spring and HTTP to
 * obtain access tokens. It builds a HTTP-call (POST) by using the specified
 * authorization code and some more credentials. In contrast to the (more
 * convenient) {@link Adal4jTokenProvider}, all details of the triggered call
 * are visible.
 */
@Component
@Profile("default")
public class PlainSpringTokenProvider implements MicrosoftGraphTokenProvider {
    private final static String URL_TO_ACQUIRE_TOKEN = "https://login.microsoftonline.com/common/oauth2/token";
    private final static String BODY_TO_ACQUIRE_TOKEN =
            "grant_type=authorization_code" + "&redirect_uri=%s" + "&resource=https://graph.microsoft.com" + "&client_id=%s" +
            "&client_secret=%s" + "&code=%s";

    @Autowired
    private ClientProperties clientProperties;

    @Override
    public String getAccessToken(final String authorizationCode) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final String bodyWithParameters = String
                .format(BODY_TO_ACQUIRE_TOKEN, this.clientProperties.getRedirectUrlHost() + this.clientProperties.getRedirectUrlEndpoint(),
                        this.clientProperties.getClientId(), this.clientProperties.getClientSecret(), authorizationCode);

        final TokenContainer tokenContainer = new RestTemplate()
                .exchange(URL_TO_ACQUIRE_TOKEN, HttpMethod.POST, new HttpEntity<String>(bodyWithParameters, headers), TokenContainer.class)
                .getBody();

        return tokenContainer.getAccess_token();
    }
}
