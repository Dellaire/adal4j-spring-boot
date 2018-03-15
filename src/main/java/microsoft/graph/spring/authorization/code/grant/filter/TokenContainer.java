package microsoft.graph.spring.authorization.code.grant.filter;

/**
 * The {@code TokenContainer} maps JSON-data containing an access token to a
 * Java-object and provides its payload.
 * 
 * @since 19.01.2017
 */
public class TokenContainer
{
    private String access_token;

    public String getAccess_token()
    {
        return this.access_token;
    }

    public void setAccess_token(String accessToken)
    {
        this.access_token = accessToken;
    }
}
