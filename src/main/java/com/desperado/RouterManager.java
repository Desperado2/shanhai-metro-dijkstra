package com.desperado;

import java.util.List;

public class RouterManager {
    private int value; //到达需要的站点

    private List<Router> stations; //经过的站点

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Router> getStations() {
        return stations;
    }

    public void setStations(List<Router> stations) {
        this.stations = stations;
    }
}
