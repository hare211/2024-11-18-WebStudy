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
    private static final String SERVICE_KEY = "NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D";
    private static final String BASE_URL = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";

    private static final String DB_URL = "jdbc:oracle:thin:@211.238.142.124:1521:XE";
    private static final String DB_USER = "hr";
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
                      .append("&contentTypeId=15");
			
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
            	String sql = "INSERT INTO content (content_id, title, contenttype_id, addr1, addr2, zipcode, sigungucode, areacode, "
            			+ "first_image, first_image2, mapx, mapy, tel, cat1, cat2, cat3) "
            			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    for (int i = 0; i < items.getLength(); i++) {
                        Node item = items.item(i);

                        ps.setInt(1, safeParseInt(getTagValue("contentid", item))); // 콘텐츠 ID
                        ps.setString(2, getTagValue("title", item)); // 제목
                        ps.setInt(3, safeParseInt(getTagValue("contenttypeid", item))); // 콘텐츠 타입 ID
                        ps.setString(4, getTagValue("addr1", item)); // 주소 1
                        ps.setString(5, getTagValue("addr2", item)); // 주소 2
                        ps.setString(6, getTagValue("zipcode", item)); // 우편번호
                        ps.setInt(7, safeParseInt(getTagValue("sigungucode", item))); // 시군구 코드
                        ps.setInt(8, safeParseInt(getTagValue("areacode", item))); // 지역 코드
                        ps.setString(9, getTagValue("firstimage", item)); // 첫 번째 이미지
                        ps.setString(10, getTagValue("firstimage2", item)); // 두 번째 이미지
                        ps.setDouble(11, safeParseDouble(getTagValue("mapx", item))); // 경도
                        ps.setDouble(12, safeParseDouble(getTagValue("mapy", item))); // 위도
                        ps.setString(13, getTagValue("tel", item)); // 전화번호
                        ps.setString(14, getTagValue("cat1", item)); // 카테고리 1
                        ps.setString(15, getTagValue("cat2", item)); // 카테고리 2
                        ps.setString(16, getTagValue("cat3", item)); // 카테고리 3

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
