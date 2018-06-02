public class SkyInfo {
    /*
     * The SkyInfo class is used to define common methods which make use of the
     * OpenSkyAPI object. Common cases such as the fastest plane in the sky, or the
     * closest plane to a cardinal coordinate location are created by this object.
     */

    private OpenSkyAPI API;
    private Airplane[] states;
    private Integer lastTimestamp;

    // Constructor creates initial plane state
    private SkyInfo(String username, String password) {
        this.API = new OpenSkyAPI(username, password);
        this.states = API.getStates();
        this.lastTimestamp = API.getTimestamp();
    }

    // Function to update to latest plane state information
    private void planeUpdate() {
        this.states = API.getStates();
        this.lastTimestamp = API.getTimestamp();
    }

    // Find the current fastest plane recorded in m/s, output it's callsign
    private void fastestPlane() {
        Double curVelocity = 0.0;
        String curCallsign = "";

        for (Airplane plane : states) {
            if (plane.getVelocity() > curVelocity) {
                curVelocity = plane.getVelocity();
                curCallsign = plane.getCallsign();
            }
        }

        System.out.println("Current fastest plane: " + curCallsign.trim() + ", at " + curVelocity + "m/s");
        System.out.println("Updated at: " + lastTimestamp);
    }

    /*
     * Find the closest plane to the given coordinates. Uses Haversine method to calculate.
     * Sample inputs:
     * Eiffel Tower: 48.8584, 2.2945
     * JFK Airport: 40.6413, 73.7781
     */
    private void closestPlane(Double Longitude, Double Latitude) {
        Double closestDistance = Double.MAX_VALUE;
        String curCallsign = "";
        final int earthRadius = 6371; // Earth's Radius in km

        for (Airplane plane : states) {
            if (plane.getLatitude() == 0.0 || plane.getLongitude() == 0.0) {
                continue;
            }

            double dLat = Math.toRadians(Latitude - plane.getLatitude());
            double dLng = Math.toRadians(Longitude - plane.getLongitude());
            double sindLat = Math.sin(dLat / 2);
            double sindLng = Math.sin(dLng / 2);
            double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                    * Math.cos(Math.toRadians(plane.getLatitude())) * Math.cos(Math.toRadians(Latitude));
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = earthRadius * c;

            if (distance < closestDistance) {
                curCallsign = plane.getCallsign();
                closestDistance = distance;
            }

        }
        int finalDistance = (int) Math.round(closestDistance);

        if (curCallsign != "") {
            System.out.println("Current closest plane to source: " + curCallsign.trim() +
                    ", " + finalDistance + "m away.");
        } else {
            System.out.println("Current closest plane to source: " + finalDistance + "m away.");
        }
        System.out.println("Updated at: " + lastTimestamp);
    }

    public static void main (String[] args) {
        SkyInfo sky = new SkyInfo(args[0], args[1]);
        sky.fastestPlane();
        sky.closestPlane(40.6413, 73.7781);
    }
}
