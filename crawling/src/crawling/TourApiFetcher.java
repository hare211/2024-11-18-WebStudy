package crawling;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
/*
 	숙박업소
 	
 */
public class TourApiFetcher {
    private static final String SERVICE_KEY = "NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D";
    private static final String BASE_URL = "https://apis.data.go.kr/B551011/KorService1/searchStay1";

    public static String fetchTourData(int numOfRows, int pageNo, String areaCode) {
        try {
            StringBuilder urlBuilder = new StringBuilder(BASE_URL);
            urlBuilder.append("?numOfRows=").append(numOfRows);
            urlBuilder.append("&pageNo=").append(pageNo);
            urlBuilder.append("&MobileOS=ETC");
            urlBuilder.append("&MobileApp=Test");
            urlBuilder.append("&_type=XML");
            urlBuilder.append("&listYN=Y");
            urlBuilder.append("&arrange=A");
            urlBuilder.append("&areaCode=").append(URLEncoder.encode(areaCode, "UTF-8"));
            //urlBuilder.append("&sigunguCode=").append(URLEncoder.encode(sigunguCode, "UTF-8"));
            urlBuilder.append("&serviceKey=").append(SERVICE_KEY);
            
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HTTP 요청 실패: " + responseCode);
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            conn.disconnect();
            
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
    	
        String jsonResponse = fetchTourData(10, 1, "1");
        System.out.println(jsonResponse);
    }
}

