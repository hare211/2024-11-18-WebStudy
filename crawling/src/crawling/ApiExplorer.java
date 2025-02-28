package crawling;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
    	// NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D
        StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_cltur_fstvl_api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + 
        		"NhaEjaIw4x6+YcgsO33ZBENWFUl8t9rR+BJILNYRY8xpFq3CNUn/pyQ/Wh/61wynoAMKu6U8KYX+wf2UhTQLFw=="); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("5", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*XML/JSON 여부*/
        urlBuilder.append("&" + URLEncoder.encode("fstvlNm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*축제명*/
        urlBuilder.append("&" + URLEncoder.encode("opar","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*개최장소*/
        urlBuilder.append("&" + URLEncoder.encode("fstvlStartDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*축제시작일자*/
        urlBuilder.append("&" + URLEncoder.encode("fstvlEndDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*축제종료일자*/
        urlBuilder.append("&" + URLEncoder.encode("fstvlCo","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*축제내용*/
        urlBuilder.append("&" + URLEncoder.encode("mnnstNm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주관기관명*/
        urlBuilder.append("&" + URLEncoder.encode("auspcInsttNm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주최기관명*/
        urlBuilder.append("&" + URLEncoder.encode("suprtInsttNm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*후원기관명*/
        urlBuilder.append("&" + URLEncoder.encode("phoneNumber","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*전화번호*/
        urlBuilder.append("&" + URLEncoder.encode("homepageUrl","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*홈페이지주소*/
        urlBuilder.append("&" + URLEncoder.encode("relateInfo","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*관련정보*/
        urlBuilder.append("&" + URLEncoder.encode("rdnmadr","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*소재지도로명주소*/
        urlBuilder.append("&" + URLEncoder.encode("lnmadr","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*소재지지번주소*/
        urlBuilder.append("&" + URLEncoder.encode("latitude","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*위도*/
        urlBuilder.append("&" + URLEncoder.encode("longitude","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*경도*/
        urlBuilder.append("&" + URLEncoder.encode("referenceDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*데이터기준일자*/
        urlBuilder.append("&" + URLEncoder.encode("instt_code","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*제공기관코드*/
        urlBuilder.append("&" + URLEncoder.encode("instt_nm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*제공기관기관명*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}
//NhaEjaIw4x6+YcgsO33ZBENWFUl8t9rR+BJILNYRY8xpFq3CNUn/pyQ/Wh/61wynoAMKu6U8KYX+wf2UhTQLFw==
