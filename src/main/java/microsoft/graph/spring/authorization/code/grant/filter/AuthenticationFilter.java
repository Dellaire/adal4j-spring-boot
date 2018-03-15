package microsoft.graph.spring.authorization.code.grant.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The {@code AuthenticationFilter} is invoken, in case of calls to protected
 * resources. It makes sure, that every of this calls contains an authentication
 * header.<br/><br/>
 * <p>
 * If they do not, the filter redirects to <i>Azure Active Directory</i>, where
 * the caller has to authenticate. After a successful authentication, an
 * authorization code is sent to the {@link CallbackController}.
 */
@Configuration
public class AuthenticationFilter implements Filter {
    private final static String URL_TO_ACQUIRE_AUTHORIZATION_CODE =
            "https://login.microsoftonline.com/common/oauth2/authorize" + "?response_type=code" + "&redirect_uri=%s" + "&client_id=%s";

    private String clientId;

    private String redirectUrl;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if (httpRequest.getHeader("Authorization") == null) {
            final String urlWithParameters = String.format(URL_TO_ACQUIRE_AUTHORIZATION_CODE, this.redirectUrl, this.clientId);
            ((HttpServletResponse) servletResponse).sendRedirect(urlWithParameters);

            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        this.clientId = filterConfig.getInitParameter("clientId");
        this.redirectUrl = filterConfig.getInitParameter("redirectUrl");
    }
}
