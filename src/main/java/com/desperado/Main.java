package com.desperado;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    @Test
    public void test() {

        List list = GetStationInfo.getStation();
        StationManager stationManager = (StationManager) list.get(0);
        LineManger lineManger = (LineManger) list.get(1);
        int length = stationManager.getStations_number();
        Router[][] matrix = new Router[length][length];

        List<Station> stations = new ArrayList<>();
        Map<String, Integer> station_index = new HashMap<>();
        int index = 0;
        for (Station station : stationManager.getStations()) {
            station_index.put(station.getStation(), index);
            index++;
            stations.add(station);
        }

        //构造矩阵
        for (int i = 0; i < stations.size(); i++) {
            for (int j = 0; j < stations.size(); j++) {
                List sameLines = stationManager.getSameLines(stations.get(i), stations.get(j));
                matrix[i][j] = lineManger.getBestRouter(stations.get(i).getStation(), stations.get(j).getStation(), sameLines);
            }
        }

        String start_station = "徐家汇";
        Integer start_index = station_index.get(start_station);
        String stop_station = "曲阜路";
        Integer stop_index = station_index.get(stop_station);


        RouterManager[] managers = new RouterManager[matrix.length - 1];
        int[] book = new int[matrix.length - 1];


        //初始化数据
        for (int i = 0; i < matrix.length - 1; i++) {
            List<Router> routers1 = new ArrayList<>();
            routers1.add(matrix[start_index][i]);
            RouterManager manager = new RouterManager();
            manager.setValue(matrix[start_index][i].getStations());
            manager.setStations(routers1);
            managers[i] = manager;
            book[i] = 0;
        }

        book[0] = 1;
        int u = 0;
        int n = stations.size();

        //dijkstra 算法实现
        for (int i = 0; i < n - 1; i++) {
            RouterManager minRouterM = new RouterManager();
            minRouterM.setValue(9999);
            List<Router> routers = new ArrayList<>();
            routers.add(new Router());
            minRouterM.setStations(routers);

            for (int j = 0; j < n - 1; j++) {
                if (book[j] == 0 && managers[j].getValue() < minRouterM.getValue()) {
                    minRouterM = managers[j];
                    u = j;
                }
            }
            book[u] = 1;

            for (int v = 0; v < n - 1; v++) {
                if (matrix[u][v].getStations() <= 9999) {
                    if (book[v] == 0 && managers[v].getValue()>(managers[u].getValue() + matrix[u][v].getStations() + 30)) {
                        List<Router> list1 = new ArrayList<>();
                        for (Router router : managers[u].getStations()) {
                            list1.add(router);
                        }
                        list1.add(matrix[u][v]);
                        RouterManager routerManager = new RouterManager();
                        routerManager.setValue(managers[u].getValue() + matrix[u][v].getStations());
                        routerManager.setStations(list1);

                        managers[v] = routerManager;
                    }
                }
            }
        }
        for (Router router1 : managers[stop_index].getStations()) {
            System.out.println("在 "+router1.getFromStation()+" 站乘坐 "+router1.getLine()+"号线 "+"到 "+router1.getToStation()+" 站");
            lineManger.printStops(Integer.parseInt(router1.getLine()), router1.getFromStation(), router1.getToStation());
        }
    }
}
