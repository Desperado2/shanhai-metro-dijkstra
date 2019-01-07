package com.desperado;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetStationInfo {

    public static List getStation(){
        //获取站点信息
        StationManager stationManager = new StationManager();
        LineManger lineManger = new LineManger();
        String url ="http://m.shmetro.com/core/shmetro/mdstationinfoback_new.ashx";
        Map<String,String> params = new HashMap<>();
        params.put("act","getAllStations");
        String result = HttpUtils.sendGet(url, params);
        if(result != null && result.length() > 0){
            JSONArray array = JSONArray.parseArray(result);
            for (int i=0; i<array.size();i++){
                JSONObject object = array.getJSONObject(i);
                String line = object.getString("key").substring(0,2);
                String station = object.getString("value");
                stationManager.addStation(station,line);
                lineManger.addLine(line,station);
            }
        }
        List list = new ArrayList();
        list.add(stationManager);
        list.add(lineManger);
        return list;
    }
}
