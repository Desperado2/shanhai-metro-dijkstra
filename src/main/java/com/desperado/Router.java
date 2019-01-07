package com.desperado;

import org.omg.PortableInterceptor.INACTIVE;

public class Router {
    private String fromStation; //出发站
    private String toStation; //到达站
    private String line; //线路号
    private Integer stations = 9999; // 需要的站点数，9999代表不可达

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Integer getStations() {
        return stations;
    }

    public void setStations(Integer stations) {
        this.stations = stations;
    }
}
