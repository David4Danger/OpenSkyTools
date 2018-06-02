import org.json.*;
import org.springframework.web.client.RestTemplate;

public class OpenSkyAPI {
  /*
   * This class makes a call to the OpenSky 'all state' endpoint and stores all plane
   * states in an array of Airplane objects.
   */

  private Airplane[] planes;
  private String username, password;
  private Integer lastCheck;

  public OpenSkyAPI(String username, String password) {
    this.username = username;
    this.password = password;
    this.lastCheck = -1;
  }

  /*
   * This method returns the Airplane state array. Can be called multiple times in order to
   * update a state array. Until more endpoints are used, only one OpenSkyAPI object should
   * be created, but this won't be enforced.
   */
  public Airplane[] getStates() {
    RestTemplate restTemplate = new RestTemplate();
    String response = restTemplate.getForObject("https://" + username + ":" + password + "@opensky-network.org/api/states/all", String.class);
    //System.out.println(response);

    try {
      // Parse response string into array of state arrays
      JSONObject json = new JSONObject(response);
      lastCheck = json.getInt("time");
      JSONArray jsonStates = json.getJSONArray("states");

      JSONArray jsonState;
      planes = new Airplane[jsonStates.length()];

      for (int i = 0; i < jsonStates.length(); i++) {
        jsonState = jsonStates.getJSONArray(i);
        planes[i] = new Airplane();

        // The next 6 fields cannot be null
        planes[i].setIcao24(jsonState.getString(0));
        planes[i].setOrigin_country(jsonState.getString(2));
        planes[i].setLast_contact(jsonState.getInt(4));
        planes[i].setOn_ground(jsonState.getBoolean(8));
        planes[i].setSpi(jsonState.getBoolean(15));
        planes[i].setPosition_source(jsonState.getInt(16));

        // The remaining fields can be null
        if (jsonState.get(1).toString() != "null") {
          planes[i].setCallsign(jsonState.getString(1));
        } else {
          planes[i].setCallsign("null");
        }

        if (jsonState.get(3).toString() != "null") {
          planes[i].setTime_position(jsonState.getInt(3));
        } else {
          planes[i].setTime_position(0);
        }

        if (jsonState.get(5).toString() != "null") {
          planes[i].setLongitude(jsonState.getDouble(5));
        } else {
          planes[i].setLongitude(0.0);
        }

        if (jsonState.get(6).toString() != "null") {
          planes[i].setLatitude(jsonState.getDouble(6));
        } else {
          planes[i].setLatitude(0.0);
        }

        if (jsonState.get(7).toString() != "null") {
          planes[i].setGeo_altitude(jsonState.getDouble(7));
        } else {
          planes[i].setGeo_altitude(0.0);
        }

        if (jsonState.get(9).toString() != "null") {
          planes[i].setVelocity(jsonState.getDouble(9));
        } else {
          planes[i].setVelocity(0.0);
        }

        if (jsonState.get(10).toString() != "null") {
          planes[i].setHeading(jsonState.getDouble(10));
        } else {
          planes[i].setHeading(0.0);
        }

        if (jsonState.get(11).toString() != "null") {
          planes[i].setVertical_rate(jsonState.getDouble(11));
        } else {
          planes[i].setVertical_rate(0.0);
        }

        // 12 is skipped as it's for user sensors only else null, not stored in airplane object

        if (jsonState.get(13).toString() != "null") {
          planes[i].setBaro_altitude(jsonState.getDouble(13));
        } else {
          planes[i].setBaro_altitude(0.0);
        }

        if (jsonState.get(14).toString() != "null") {
          planes[i].setSquawk(jsonState.getString(14));
        } else {
          planes[i].setSquawk("null");
        }
      }

      return planes;
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }

  // Grab the timestamp of the latest call to the OpenSky API
  public Integer getTimestamp() {
    return lastCheck;
  }
}
