package microsoft.graph.spring.authorization.code.grant.filter;

import microsoft.graph.spring.authorization.code.grant.graph.MicrosoftGraphTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The {@code CallbackController} requests authorization codes and uses them to
 * acquire access tokens.
 */
@RestController
@RequestMapping(value = "${redirect-url-endpoint}")
public class CallbackController {
    @Autowired
    private MicrosoftGraphTokenProvider tokenProvider;

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public String postAuthorizationCode(@RequestParam final String code, final HttpServletResponse response,
                                        final RedirectAttributes attributes) throws IOException {
        // String accessToken = this.tokenProvider.getAccessToken(code);
        //
        // return this.returnToAuthenticationFilter(accessToken);

        // ---

        // HttpHeaders headers = new HttpHeaders();
        // headers.set(HttpHeaders.AUTHORIZATION, "Bearer " +
        // this.tokenProvider.getAccessToken(code));
        //
        // return new
        // RestTemplate().exchange("http://localhost:8080/protected/contacts",
        // HttpMethod.GET,
        // new HttpEntity<String>(headers), String.class).getBody();

        // ---

        // response.setHeader(HttpHeaders.AUTHORIZATION,
        // this.tokenProvider.getAccessToken(code));
        //
        // attributes.addAttribute("attribute", "redirectWithRedirectView");
        // return new RedirectView("http://localhost:8080/protected/contacts");

        // ---

        // response.setHeader(HttpHeaders.AUTHORIZATION,
        // this.tokenProvider.getAccessToken(code));
        //
        // response.sendRedirect("http://localhost:8080/protected/contacts");

        // ---

        return "http://localhost:8080/protected/contacts";
    }

    private String returnToAuthenticationFilter(final String accessToken) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        return new RestTemplate()
                .exchange("http://localhost:8080/protected/contacts", HttpMethod.GET, new HttpEntity<String>(headers), String.class)
                .getBody();
    }
}
