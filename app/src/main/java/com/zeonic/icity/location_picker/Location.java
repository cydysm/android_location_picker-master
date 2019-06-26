package com.zeonic.icity.location_picker;

import java.io.Serializable;

/**
 * Created by IrrElephant on 17/5/8.
 */

public class Location implements Serializable{
    Double latitude;
    Double longitude;
    String  remark;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude( double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
