package com.desperado;


import java.util.List;

public class Station {
    private String station; //站点名称
    private List<Integer> line_numbers; //线路

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public List<Integer> getLine_numbers() {
        return line_numbers;
    }

    public void setLine_numbers(List<Integer> line_numbers) {
        this.line_numbers = line_numbers;
    }
}
