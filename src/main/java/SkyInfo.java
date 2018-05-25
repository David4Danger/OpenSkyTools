public class SkyInfo {
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

    // Various methods that use states
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
    }

    public static void main (String[] args) {
        SkyInfo sky = new SkyInfo(args[0], args[1]);
        sky.fastestPlane();
    }
}
