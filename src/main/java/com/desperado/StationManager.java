package com.desperado;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StationManager {
    private Set<String> station_names; //站点名称
    private List<Station> stations; //所有站点

    public StationManager(){
        station_names = new HashSet<>();
        stations = new ArrayList<>();
    }


    public void addStation(String station_name,String line){
        if(station_names.contains(station_name)){
            for (Station station : stations){
                if(station.getStation().equals(station_name)){
                    station.getLine_numbers().add(Integer.parseInt(line));
                }
            }
        }else{
            Station station = new Station();
            station.setStation(station_name);
            List<Integer> list = new ArrayList<>();
            list.add(Integer.parseInt(line));
            station.setLine_numbers(list);
            stations.add(station);
        }
        station_names.add(station_name);
    }

    public Integer getStations_number(){
        return stations.size();
    }

    //获取所有直达线路
    public List getSameLines(Station fromStation, Station toStation){
        List<Integer> lines = new ArrayList<>();
        for (Integer line:fromStation.getLine_numbers()){
            if(toStation.getLine_numbers().contains(line)){
                lines.add(line);
            }
        }
        return lines;
    }

    public void printStationInfo(String station){
        System.out.println("Station: "+station+" ->");
        System.out.println("lines: ");
        for (Station station1 : stations) {
            if (station.equals(station1.getStation())) {
                for (Integer line : station1.getLine_numbers()){
                    System.out.print(line);
                }
            }
        }
    }

    public void printAllStation(){
        for (Station station :stations){
            printStationInfo(station.getStation());
            System.out.println(" ");
        }
    }

    public Set<String> getStation_names() {
        return station_names;
    }

    public void setStation_names(Set<String> station_names) {
        this.station_names = station_names;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
