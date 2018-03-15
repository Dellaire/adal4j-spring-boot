package microsoft.graph.spring.authorization.code.grant.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/protected")
public class ResourceController
{
    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ResponseEntity<ContactCollection> getContactFromGraph(HttpServletRequest request)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        ContactCollection contactContainer = restTemplate
                .exchange("https://graph.microsoft.com/v1.0/me/contacts", HttpMethod.GET,
                        new HttpEntity<String>(headers), ContactCollection.class)
                .getBody();

        return new ResponseEntity<ContactCollection>(contactContainer, HttpStatus.OK);
    }
}
