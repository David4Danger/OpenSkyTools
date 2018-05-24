import org.springframework.web.client.RestTemplate;

public class OpenSkyAPI {

// Pass in OpenSky username and password as args[0] and args[1]
public static void main (String[] args) {
  System.out.println("OpenSkyAPI call");

  RestTemplate restTemplate = new RestTemplate();
  String URL = restTemplate.getForObject("https://" + args[0] + ":" + args[1] + "@opensky-network.org/api/states/all", String.class);
  System.out.println(URL);
}

}
