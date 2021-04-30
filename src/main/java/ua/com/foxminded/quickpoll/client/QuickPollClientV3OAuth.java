package ua.com.foxminded.quickpoll.client;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import ua.com.foxminded.quickpoll.domain.Poll;

import java.util.ArrayList;
import java.util.List;

public class QuickPollClientV3OAuth {

    private static final String QUICK_POLL_URI_V3 = "http://localhost:8080/oauth2/v3/polls";

    private OAuth2RestTemplate restTemplate() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setGrantType("password");
        resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
        resourceDetails.setClientId("quickpolliOSClient");
        resourceDetails.setClientSecret("top_secret");

        List<String> scopes = new ArrayList<>();
        scopes.add("read"); scopes.add("write");
        resourceDetails.setScope(scopes);

        resourceDetails.setUsername("admin");
        resourceDetails.setPassword("admin");
        return new OAuth2RestTemplate(resourceDetails);
    }

    public Poll getPollById(Long pollId) {
        OAuth2RestTemplate restTemplate = restTemplate();
        return restTemplate.getForObject(QUICK_POLL_URI_V3 + "/{pollId}", Poll.class, pollId);
    }

    public static void main(String[] args) {
        QuickPollClientV3OAuth client = new QuickPollClientV3OAuth();
        Poll poll = client.getPollById(1L);
        System.out.println("Poll - " + poll);
    }

}
