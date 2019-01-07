package com.desperado;

import java.util.*;

public class LineManger {
    private Set<String> line_numbers; //线路
    private List<Line> lines; //所有线路

    public LineManger(){
        line_numbers = new HashSet<>();
        lines = new ArrayList<>();
    }

    public void addLine(String line,String station_name){
        if(line_numbers.contains(line)){
            for (Line line1 : lines){
                if(line1.getLine_number() == Integer.parseInt(line)){
                    line1.getStations().add(station_name);
                }
            }
        }else{
            Line newLine = new Line();
            newLine.setLine_number(Integer.parseInt(line));
            List<String> list = new ArrayList<>();
            list.add(station_name);
            newLine.setStations(list);
            lines.add(newLine);
        }
        line_numbers.add(line);
    }

    public void printLineInfo(Integer line_number){
        System.out.println("Line: "+line_number);
        System.out.print("lines: ");
        for (Line line : lines){
            if(line_number == line.getLine_number()){
                for (String station :line.getStations()){
                    System.out.print(station+"->");
                }
            }
        }
    }

    public void pringAllInfo(){
        for (Line line : lines){
            printLineInfo(line.getLine_number());
            System.out.println("");
        }
    }

    //获取最短直达路线
    public Router getBestRouter(String fromStation,String tostation,List<Integer> lineList){
        Router router = new Router();
        router.setFromStation(fromStation);
        router.setToStation(tostation);
        router.setStations(9999);
        if(lineList == null || lineList.size() == 0){
            return router;
        }

            for (Line line : lines){
                if(lineList.contains(line.getLine_number())){
                    int start_index =0;
                    int stop_index = 0;
                    for (int i=0;i<line.getStations().size();i++){
                        if (line.getStations().get(i).equals(fromStation)){
                            start_index = i;
                        }else if(line.getStations().get(i).equals(tostation)){
                            stop_index = i;
                        }
                    }
                    Integer stops = Math.abs(start_index-stop_index);
                    if(stops < router.getStations()){
                        router.setStations(stops);
                        router.setLine(line.getLine_number()+"");
                    }
                }
            }

        return router;
    }

    public void printStops(Integer line_number,String fromStation, String toStation){
        Line line = null;
        for (Line item : lines){
            if(line_number == item.getLine_number()){
                line = item;
            }
        }
        Integer startIndex = 0;
        Integer stopIndex = 0;
        List<String> station1 = line.getStations();

        for (int i=0; i<station1.size(); i++){
            if(station1.get(i).equals(fromStation)){
                startIndex = i;
            }else if(station1.get(i).equals(toStation)){
                stopIndex = i;
            }
        }

        if(startIndex > stopIndex){
            Boolean start_print = false;
            Collections.reverse(station1);
            for (String station : station1){
                if(station.equals(fromStation)){
                    System.out.print(station+"->");
                    start_print = true;
                }else if(station.equals(toStation)){
                    System.out.print(station);
                    start_print = false;
                }else if(start_print){
                    System.out.print(station +"->");
                }
            }
        }else{
            Boolean start_print = false;
            for (String station : station1){
                if(station.equals(fromStation)){
                    System.out.print(station+"->");
                    start_print = true;
                }else if(station.equals(toStation)){
                    System.out.print(station);
                    start_print = false;
                }else if(start_print){
                    System.out.print(station +"->");
                }
            }
        }
        System.out.println("");
    }

    public Set<String> getLine_numbers() {
        return line_numbers;
    }

    public void setLine_numbers(Set<String> line_numbers) {
        this.line_numbers = line_numbers;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}
