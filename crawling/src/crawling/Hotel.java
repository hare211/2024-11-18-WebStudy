package crawling;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Hotel {
	private static final String SERVICE_KEY = "NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D";
    private static final String BASE_URL = "https://apis.data.go.kr/B551011/KorService1/searchStay1";

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "hr";
    private static final String DB_PASSWORD = "happy";
    
    static int count = 0;
    
    public static void main(String[] args) {
        int numOfRows = 200;  // 한 페이지에 가져올 데이터의 수
        int startPage = 1;
        int endPage = 20; // 마지막 페이지

        for (int pageNo = startPage; pageNo <= endPage; pageNo++) {
            String xml = fetchTourData(numOfRows, pageNo);
            if (xml != null && !xml.isEmpty()) {
                parseAndInsert(xml);
                System.out.println("CurrentPage: " + pageNo);
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
             	https://apis.data.go.kr/B551011/KorService1/searchStay1
             	?numOfRows=1
             	&MobileOS=etc
             	&MobileApp=test
             	&listYN=Y
             	&arrange=O
             	&serviceKey=NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D
             */
            urlBuilder.append("?numOfRows=").append(numOfRows)
                      .append("&pageNo=").append(pageNo)
                      .append("&MobileOS=ETC")
                      .append("&MobileApp=Test")
                      .append("&listYN=Y")
                      .append("&arrange=O")
                      .append("&serviceKey=").append(SERVICE_KEY);

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
                String sql = "INSERT INTO hotel "
                		+ "(contentid, title, addr1, addr2, areacode, cat1, cat2, cat3, createdtime, firstimage, firstimage2, mapx, mapy, modifiedtime, tel) "
                		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
                
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    for (int i = 0; i < items.getLength(); i++) {
                        Node item = items.item(i);

                        ps.setString(1, getTagValue("addr1", item)); // 주소 1
                        ps.setInt(3, safeParseInt(getTagValue("areacode", item))); // 지역 코드
                        
                        ps.setInt(1, safeParseInt(getTagValue("contentid", item)));
                        ps.setString(2, getTagValue("title", item));
                        ps.setString(3, getTagValue("addr1", item));
                        ps.setString(4, getTagValue("addr2", item));
                        ps.setInt(5, safeParseInt(getTagValue("areacode", item)));
                        ps.setString(6, getTagValue("cat1", item));
                        ps.setString(7, getTagValue("cat2", item));
                        ps.setString(8, getTagValue("cat3", item));
                        ps.setString(9, getTagValue("createdtime", item));
                        ps.setString(10, getTagValue("firstimage", item));
                        ps.setString(11, getTagValue("firstimage2", item));
                        ps.setDouble(12, safeParseDouble(getTagValue("mapx", item)));
                        ps.setDouble(13, safeParseDouble(getTagValue("mapy", item)));
                        ps.setString(14, getTagValue("modifiedtime", item));
                        ps.setString(15, getTagValue("tel", item));
                        

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
