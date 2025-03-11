package crawling;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasedSyncList {
	//private static final String SERVICE_KEY = "NhaEjaIw4x6+YcgsO33ZBENWFUl8t9rR+BJILNYRY8xpFq3CNUn/pyQ/Wh/61wynoAMKu6U8KYX+wf2UhTQLFw=="; // 김기현
	private static final String SERVICE_KEY = "NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D"; // 김기현
	//private static final String SERVICE_KEY = "765culrxSZiwbitzvOE7ivNnVycuS2XjHIdKoT0qNzo6EBctNdDnNLfYatyeMot4fjpvFMchhzqtdsXNqfVW6w=="; // 김나린
	//private static final String SERVICE_KEY = "765culrxSZiwbitzvOE7ivNnVycuS2XjHIdKoT0qNzo6EBctNdDnNLfYatyeMot4fjpvFMchhzqtdsXNqfVW6w%3D%3D"; // 김나린
	//private static final String SERVICE_KEY = "sG7+6n0dJE35jeE8/6e+TnIXB9exmJGPv7oN7J748WemRXlEKbpyKaXKBMpakA5lJ2kpDEKH4YiXyqYU/NOiXQ=="; // 김준홍
	//private static final String SERVICE_KEY = "sG7%2B6n0dJE35jeE8%2F6e%2BTnIXB9exmJGPv7oN7J748WemRXlEKbpyKaXKBMpakA5lJ2kpDEKH4YiXyqYU%2FNOiXQ%3D%3D"; // 김준홍
	//private static final String SERVICE_KEY = "8dk3iXDfjmaOx0J4oq9d0L3tydEBbhlKmbljieCGdczc0DCNLbmholv/Ynw62iIUxh5fDEW0+cRR57aC83QTmA=="; // 방다혜
	//private static final String SERVICE_KEY = "8dk3iXDfjmaOx0J4oq9d0L3tydEBbhlKmbljieCGdczc0DCNLbmholv%2FYnw62iIUxh5fDEW0%2BcRR57aC83QTmA%3D%3D"; // 방다혜
	//private static final String SERVICE_KEY = "SMiQJ/XxTL7+UI9iiszkSfrPNmZGONrDLzkkh9yUxDZ8MNXVM0BMN2wcXBORvkmr6GZRSB3OESZMDqAbVaobnw=="; // 김채연
	//private static final String SERVICE_KEY = "SMiQJ%2FXxTL7%2BUI9iiszkSfrPNmZGONrDLzkkh9yUxDZ8MNXVM0BMN2wcXBORvkmr6GZRSB3OESZMDqAbVaobnw%3D%3D"; // 김채연
    private static final String BASE_URL = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";

    private static final String DB_URL = "jdbc:oracle:thin:@211.238.142.124:1521:XE";
    private static final String DB_USER = "hr_2";
    private static final String DB_PASSWORD = "happy";
    
    static int count = 0;
    
    public static void main(String[] args) throws InterruptedException {
        int numOfRows = 100;  // 한 페이지에 가져올 데이터의 수
        int startPage = 17;
        int endPage = 46; // 마지막 페이지
        

        for (int pageNo = startPage; pageNo <= endPage; pageNo++) {
            String xml = fetchTourData(numOfRows, pageNo);
            if (xml != null && !xml.isEmpty()) {
                parseAndInsert(xml);
                System.out.println("CurrentPage: " + pageNo);
                Thread.sleep(1000);
            } else {
                System.out.println("페이지 " + pageNo + " 데이터 없음.");
            }
        }
    }

    // API 호출 (XML 데이터 가져오기)
    public static String fetchTourData(int numOfRows, int pageNo) {
        try {
            StringBuilder urlBuilder = new StringBuilder(BASE_URL);
            /*
             	**** 행사 / 축제 ****
             	https://apis.data.go.kr/B551011/KorService1/areaBasedSyncList1
             	?numOfRows=100
             	&MobileOS=etc
             	&MobileApp=test
             	&serviceKey=NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D
             	&listYN=Y
             	&arrange=O
             	&contentTypeId=15
             	**** 숙박 ****
             	https://apis.data.go.kr/B551011/KorService1/searchStay1
             	?numOfRows=1
             	&pageNo=1
             	&MobileOS=etc
             	&MobileApp=test
             	&listYN=Y
             	&arrange=O
             	&serviceKey=NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D
             */
            /*
            //**** 숙박 ****
            urlBuilder.append("?numOfRow=").append(numOfRows)
            		  .append("&pageNo=").append(pageNo)
            		  .append("&MobileOS=etc")
            		  .append("&MobileApp=test")
            		  .append("&listYN=Y")
            		  .append("&arrange=O")
            		  .append("&serviceKey=").append(SERVICE_KEY);
             */
            
            //**** 행사 / 축제 ****
            urlBuilder.append("?numOfRows=").append(numOfRows)
                      .append("&pageNo=").append(pageNo)
                      .append("&MobileOS=ETC")
                      .append("&MobileApp=Test")
                      .append("&serviceKey=").append(SERVICE_KEY)
                      .append("&listYN=Y")
                      .append("&arrange=O")
                      .append("&contentTypeId=39");
			
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");

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

    // XML 파싱 및 DB 삽입
    public static void parseAndInsert(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
            doc.getDocumentElement().normalize();

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            NodeList items = (NodeList) xpath.evaluate("/response/body/items/item", doc, XPathConstants.NODESET);
            System.out.println("XPath로 찾은 item 개수: " + items.getLength());

            if (items.getLength() == 0) {
                System.out.println("데이터 없음.");
                return;
            }

            // Oracle DB 연결
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            	/*
            	 	content_id NUMBER,
				    title VARCHAR2(150),
				    contenttype_id NUMBER,
				    addr1 VARCHAR2(500),
				    addr2 VARCHAR2(500),
				    zipcode VARCHAR(10),
				    sigungucode NUMBER,
				    areacode NUMBER,
				    first_image VARCHAR2(1000),
				    first_image2 VARCHAR2(1000),
				    mapx NUMBER(15, 10),
				    mapy NUMBER(15, 10),
				    tel VARCHAR2(400),
				    cat1 VARCHAR2(50),
				    cat2 VARCHAR2(50),
				    cat3 VARCHAR2(50)
            	 */
            	String sql = "UPDATE content SET overview = ? WHERE content_id = ?"; // overview 넣기
                
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    for (int i = 0; i < items.getLength(); i++) {
                        Node item = items.item(i);

                        ps.setString(2, getTagValue("overview", item));
                        ps.setInt(2, safeParseInt(getTagValue("contentid", item))); // 콘텐츠 ID

                        ps.executeUpdate();
                        count++;
                        if (count % 10 == 0) {
                            System.out.println(count + " 번째 데이터 삽입 완료");
                        }
                    }
                    System.out.println("데이터 삽입 완료!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 숫자 변환 예외 처리
    private static int safeParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static double safeParseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // XML 태그 값을 가져오는 메서드
    private static String getTagValue(String tag, Node item) {
        Element element = (Element) item;
        NodeList nList = element.getElementsByTagName(tag);
        if (nList.getLength() > 0) {
            String value = nList.item(0).getTextContent().trim();
            return value.isEmpty() ? "N/A" : value;
        }
        return "N/A";
    }
}
