import org.json.*;
import org.springframework.web.client.RestTemplate;

public class OpenSkyAPI {

// Pass in OpenSky username and password as args[0] and args[1]
public static void main (String[] args) {
  System.out.println("OpenSkyAPI call");

  RestTemplate restTemplate = new RestTemplate();
  String response = restTemplate.getForObject("https://" + args[0] + ":" + args[1] + "@opensky-network.org/api/states/all", String.class);
  //System.out.println(response);

  Airplane[] planes;

  try {
    // Parse response string into array of state arrays
    JSONObject json = new JSONObject(response);
    JSONArray jsonStates = json.getJSONArray("states");

    JSONArray jsonState;
    planes = new Airplane[jsonStates.length()];

    for (int i = 0; i < jsonStates.length(); i++) {
      jsonState = jsonStates.getJSONArray(i);
      planes[i] = new Airplane();

      planes[i].setIcao24(jsonState.getString(0));
      planes[i].setOrigin_country(jsonState.getString(2));
      planes[i].setLast_contact(jsonState.getInt(4));
      planes[i].setOn_ground(jsonState.getBoolean(8));
      planes[i].setSpi(jsonState.getBoolean(15));
      planes[i].setPosition_source(jsonState.getInt(16));

      if (jsonState.get(1).toString() != "null") {
        planes[i].setCallsign(jsonState.getString(1));
      } else {
        planes[i].setCallsign(null);
      }

      if (jsonState.get(3).toString() != "null") {
        planes[i].setTime_position(jsonState.getInt(3));
      } else {
        planes[i].setTime_position(null);
      }

      if (jsonState.get(5).toString() != "null") {
        planes[i].setLongitude(jsonState.getDouble(5));
      } else {
        planes[i].setLongitude(null);
      }

      if (jsonState.get(6).toString() != "null") {
        planes[i].setLatitude(jsonState.getDouble(6));
      } else {
        planes[i].setLatitude(null);
      }

      if (jsonState.get(7).toString() != "null") {
        planes[i].setGeo_altitude(jsonState.getDouble(7));
      } else {
        planes[i].setGeo_altitude(null);
      }

      if (jsonState.get(9).toString() != "null") {
        planes[i].setVelocity(jsonState.getDouble(9));
      } else {
        planes[i].setVelocity(null);
      }

      if (jsonState.get(10).toString() != "null") {
        planes[i].setHeading(jsonState.getDouble(10));
      } else {
        planes[i].setHeading(null);
      }

      if (jsonState.get(11).toString() != "null") {
        planes[i].setVertical_rate(jsonState.getDouble(11));
      } else {
        planes[i].setVertical_rate(null);
      }

      // 12 is skipped as it's for user sensors only else null, not stored in airplane object

      if (jsonState.get(13).toString() != "null") {
        planes[i].setBaro_altitude(jsonState.getDouble(13));
      } else {
        planes[i].setBaro_altitude(null);
      }

      if (jsonState.get(14).toString() != "null") {
        planes[i].setSquawk(jsonState.getString(14));
      } else {
        planes[i].setSquawk(null);
      }
    }
  } catch (JSONException e) {
    e.printStackTrace();
  }

}

}
