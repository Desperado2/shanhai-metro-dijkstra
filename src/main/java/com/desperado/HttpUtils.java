package com.desperado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class HttpUtils {

    public static String sendGet(String url , Map<String,String> map){
        String result ="";
        BufferedReader br = null;
        String realUrl = "";
        if(map == null || map.size() == 0){
            realUrl = url;
        }else{
            realUrl = url +"?" +getParams(map);
        }
        try {
            URL url1 = new URL(realUrl);
            URLConnection connection = url1.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();

            br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;

    }


    public static String getParams(Map<String,String> map){
        String result = "";
        for (Map.Entry entry :map.entrySet()){
            result += entry.getKey()+"="+entry.getValue()+"&";
        }
        return result.substring(0,result.length()-1);
    }
}
