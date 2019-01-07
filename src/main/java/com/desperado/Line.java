package com.desperado;

import java.util.List;

public class Line {
    private Integer line_number; // 线路
    private List<String> stations; //站点名称

    public Integer getLine_number() {
        return line_number;
    }

    public void setLine_number(Integer line_number) {
        this.line_number = line_number;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }
}
