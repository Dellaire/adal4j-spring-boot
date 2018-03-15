package microsoft.graph.spring.authorization.code.grant.graph;

/**
 * The {@code MicrosoftGraphTokenProvider} provides a method to obtain access
 * tokens, which can be used to access data via <i>Microsoft Graph</i>.
 */
public interface MicrosoftGraphTokenProvider {
    public String getAccessToken(String authorizationCode);
}
