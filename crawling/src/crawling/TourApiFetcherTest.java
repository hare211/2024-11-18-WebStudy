package crawling;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
/*
 	지역별 관광지
 	서울: 1
 	인천: 2
 	대전: 3
 	대구: 4
 	광주: 5
 	부산: 6
 	울산: 7
 	세종: 8
 	경기: 31
 	강원: 32
 	충북: 33
 	충남: 34
 	경북: 35
 	경남: 36
 	전북: 37
 	전남: 38
 	제주: 39
 */
public class TourApiFetcherTest {
    private static final String SERVICE_KEY = "NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D";
    private static final String BASE_URL = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
    
    // Oracle DB 연결 정보
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Oracle DB URL
    private static final String DB_USER = "hr"; // 사용자 이름
    private static final String DB_PASSWORD = "happy"; // 비밀번호

    public static void main(String[] args) {
    	int numOfRows = 100;  // 한 페이지에 가져올 데이터의 수
        int pageNo = 1;      // 첫 번째 페이지부터 시작

        // 데이터 페칭 및 DB 삽입
        String xml = fetchTourData(numOfRows, pageNo);
        System.out.println("🚀 받아온 XML 데이터:");
        System.out.println(xml);

        if (xml != null && !xml.isEmpty()) {
            parseAndInsert(xml);
        } else {
            System.out.println("데이터를 가져오는 데 실패했습니다.");
        }
    }

    // 🟢 1. API 호출 (XML 데이터 가져오기)
    public static String fetchTourData(int numOfRows, int pageNo) {
        try {
            StringBuilder urlBuilder = new StringBuilder(BASE_URL);
            /*
             https://apis.data.go.kr/B551011
             /KorService1
              * /areaBasedList1?numOfRows=1
              * &pageNo=1
              * &MobileOS=ETC
              * &MobileApp=test
              * &listYN=Y
              * &arrange=A
              * &contentTypeId=12
              * &serviceKey=NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D
             
             */
            urlBuilder.append("?numOfRows=").append(numOfRows);
            urlBuilder.append("&pageNo=").append(pageNo);
            urlBuilder.append("&MobileOS=ETC");
            urlBuilder.append("&MobileApp=Test");
            urlBuilder.append("&_type=XML");
            urlBuilder.append("&listYN=Y");
            urlBuilder.append("&arrange=A");
            urlBuilder.append("&contentTypeId=12");
            urlBuilder.append("&areaCode=1");
            urlBuilder.append("&serviceKey=").append(SERVICE_KEY);
            
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

    // 🟢 2. XML 파싱 및 DB 삽입
    public static void parseAndInsert(String xml) {
        try {
            // XML을 Document 객체로 변환
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	factory.setNamespaceAware(false); // 네임스페이스 무시
        	DocumentBuilder builder = factory.newDocumentBuilder();
        	Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        	doc.getDocumentElement().normalize();

        	XPathFactory xPathFactory = XPathFactory.newInstance();
        	XPath xpath = xPathFactory.newXPath();

        	// 디버깅 코드 추가
        	System.out.println("Root Element: " + doc.getDocumentElement().getNodeName()); 
        	System.out.println("response 태그 개수: " + doc.getElementsByTagName("response").getLength());
        	System.out.println("body 태그 개수: " + doc.getElementsByTagName("body").getLength());
        	System.out.println("items 태그 개수: " + doc.getElementsByTagName("items").getLength());
        	System.out.println("item 태그 개수: " + doc.getElementsByTagName("item").getLength());

        	// XPath 실행 (경로 수정)
        	NodeList items = (NodeList) xpath.evaluate("/response/body/items/item", doc, XPathConstants.NODESET);
        	System.out.println("XPath로 찾은 item 개수: " + items.getLength());

            if (items.getLength() == 0) {
                System.out.println("데이터 없음.");
                return;
            }

            
            // Oracle DB 연결
            /*
             	ID            NOT NULL NUMBER        
				ADDR1                  VARCHAR2(255) 
				ADDR2                  VARCHAR2(255) 
				AREACODE               NUMBER(10)    
				BOOKTOUR               NUMBER(1)     
				CAT1                   VARCHAR2(10)  
				CAT2                   VARCHAR2(10)  
				CAT3                   VARCHAR2(20)  
				CONTENTID              NUMBER(10)    
				CONTENTTYPEID          NUMBER(10)    
				CREATEDTIME            TIMESTAMP(6)  
				FIRSTIMAGE             VARCHAR2(255) 
				FIRSTIMAGE2            VARCHAR2(255) 
				CPYRHTDIVCD            VARCHAR2(50)  
				MAPX                   NUMBER(15,10) 
				MAPY                   NUMBER(15,10) 
				MLEVEL                 NUMBER(10)    
				MODIFIEDTIME           TIMESTAMP(6)  
				SIGUNGUCODE            NUMBER(10)    
				TEL                    VARCHAR2(50)  
				TITLE                  VARCHAR2(255) 
				ZIPCODE                VARCHAR2(10) 
             */
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            	String sql = "INSERT INTO tourist_spots (" +
                        "id, addr1, addr2, areacode, booktour, cat1, cat2, cat3, contentid, " +
                        "contenttypeid, createdtime, firstimage, firstimage2, cpyrhtDivCd, " +
                        "mapx, mapy, mlevel, modifiedtime, sigungucode, tel, title, zipcode) " +
                        "VALUES (ts_id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_TIMESTAMP(?, 'YYYYMMDDHH24MISS'), ?, ?, ?, ?, ?, ?, TO_TIMESTAMP(?, 'YYYYMMDDHH24MISS'), ?, ?, ?, ?)";

           try (PreparedStatement ps = conn.prepareStatement(sql)) {
               for (int i = 0; i < items.getLength(); i++) {
                   Node item = items.item(i);

                   ps.setString(1, getTagValue("addr1", item));
                   ps.setString(2, getTagValue("addr2", item));
                   ps.setInt(3, safeParseInt(getTagValue("areacode", item)));
                   ps.setInt(4, safeParseInt(getTagValue("booktour", item)));  
                   ps.setString(5, getTagValue("cat1", item));  
                   ps.setString(6, getTagValue("cat2", item));  
                   ps.setString(7, getTagValue("cat3", item));  
                   ps.setInt(8, safeParseInt(getTagValue("contentid", item)));
                   ps.setInt(9, safeParseInt(getTagValue("contenttypeid", item)));
                   ps.setString(10, getTagValue("createdtime", item));  
                   ps.setString(11, getTagValue("firstimage", item));  
                   ps.setString(12, getTagValue("firstimage2", item)); 
                   ps.setString(13, getTagValue("cpyrhtDivCd", item)); 
                   ps.setDouble(14, Double.parseDouble(getTagValue("mapx", item)));
                   ps.setDouble(15, Double.parseDouble(getTagValue("mapy", item)));
                   ps.setInt(16, safeParseInt(getTagValue("mlevel", item)));
                   ps.setString(17, getTagValue("modifiedtime", item));  
                   ps.setInt(18, safeParseInt(getTagValue("sigungucode", item)));
                   ps.setString(19, getTagValue("tel", item));
                   ps.setString(20, getTagValue("title", item));
                   ps.setString(21, getTagValue("zipcode", item));  

                   ps.executeUpdate();
               }
               System.out.println("데이터 삽입 완료!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static int safeParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // 숫자가 아니면 기본값 0 반환하거나 null 반환
            return 0;  // 기본값 0 설정
        }
    }

    // XML 태그 값을 가져오는 메서드
    private static String getTagValue(String tag, Node item) {
        Element element = (Element) item;
        NodeList nList = element.getElementsByTagName(tag);
        if (nList.getLength() > 0) {
            String value = nList.item(0).getTextContent().trim();
            return value.isEmpty() ? "N/A" : value;  // 비어 있으면 "N/A"를 반환
        }
        return "N/A";  // 태그가 없으면 "N/A"를 반환
    }
}
