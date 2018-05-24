public class Airplane {
    private String icao24, callsign, origin_country, squawk;
    private Integer time_position, last_contact, position_source;
    private Double longitude, latitude, geo_altitude, velocity, heading, vertical_rate, baro_altitude;
    private Boolean on_ground, spi;

    @Override
    public String toString() {
        return "Airplane{" +
                "icao24='" + icao24 + '\'' +
                ", callsign='" + callsign + '\'' +
                ", origin_country='" + origin_country + '\'' +
                ", squawk='" + squawk + '\'' +
                ", time_position=" + time_position +
                ", last_contact=" + last_contact +
                ", position_source=" + position_source +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", geo_altitude=" + geo_altitude +
                ", velocity=" + velocity +
                ", heading=" + heading +
                ", vertical_rate=" + vertical_rate +
                ", baro_altitude=" + baro_altitude +
                ", on_ground=" + on_ground +
                ", spi=" + spi +
                '}';
    }

    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public String getSquawk() {
        return squawk;
    }

    public void setSquawk(String squawk) {
        this.squawk = squawk;
    }

    public Integer getTime_position() {
        return time_position;
    }

    public void setTime_position(Integer time_position) {
        this.time_position = time_position;
    }

    public Integer getLast_contact() {
        return last_contact;
    }

    public void setLast_contact(Integer last_contact) {
        this.last_contact = last_contact;
    }

    public Integer getPosition_source() {
        return position_source;
    }

    public void setPosition_source(Integer position_source) {
        this.position_source = position_source;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getGeo_altitude() {
        return geo_altitude;
    }

    public void setGeo_altitude(Double geo_altitude) {
        this.geo_altitude = geo_altitude;
    }

    public Double getVelocity() {
        return velocity;
    }

    public void setVelocity(Double velocity) {
        this.velocity = velocity;
    }

    public Double getHeading() {
        return heading;
    }

    public void setHeading(Double heading) {
        this.heading = heading;
    }

    public Double getVertical_rate() {
        return vertical_rate;
    }

    public void setVertical_rate(Double vertical_rate) {
        this.vertical_rate = vertical_rate;
    }

    public Double getBaro_altitude() {
        return baro_altitude;
    }

    public void setBaro_altitude(Double baro_altitude) {
        this.baro_altitude = baro_altitude;
    }

    public Boolean getOn_ground() {
        return on_ground;
    }

    public void setOn_ground(Boolean on_ground) {
        this.on_ground = on_ground;
    }

    public Boolean getSpi() {
        return spi;
    }

    public void setSpi(Boolean spi) {
        this.spi = spi;
    }
}
