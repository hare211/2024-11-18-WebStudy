package crawling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.*;

public class HotelFacility {
	//private static final String SERVICE_KEY = "NhaEjaIw4x6+YcgsO33ZBENWFUl8t9rR+BJILNYRY8xpFq3CNUn/pyQ/Wh/61wynoAMKu6U8KYX+wf2UhTQLFw=="; // 김기현
	//private static final String SERVICE_KEY = "NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D"; // 김기현
	//private static final String SERVICE_KEY = "765culrxSZiwbitzvOE7ivNnVycuS2XjHIdKoT0qNzo6EBctNdDnNLfYatyeMot4fjpvFMchhzqtdsXNqfVW6w=="; // 김나린
	//private static final String SERVICE_KEY = "sG7+6n0dJE35jeE8/6e+TnIXB9exmJGPv7oN7J748WemRXlEKbpyKaXKBMpakA5lJ2kpDEKH4YiXyqYU/NOiXQ=="; // 김준홍
	//private static final String SERVICE_KEY = "8dk3iXDfjmaOx0J4oq9d0L3tydEBbhlKmbljieCGdczc0DCNLbmholv/Ynw62iIUxh5fDEW0+cRR57aC83QTmA=="; // 방다혜
	private static final String SERVICE_KEY = "8dk3iXDfjmaOx0J4oq9d0L3tydEBbhlKmbljieCGdczc0DCNLbmholv%2FYnw62iIUxh5fDEW0%2BcRR57aC83QTmA%3D%3D"; // 방다혜
	//private static final String SERVICE_KEY = "SMiQJ/XxTL7+UI9iiszkSfrPNmZGONrDLzkkh9yUxDZ8MNXVM0BMN2wcXBORvkmr6GZRSB3OESZMDqAbVaobnw=="; // 김채연
	//private static final String SERVICE_KEY = "SMiQJ%2FXxTL7%2BUI9iiszkSfrPNmZGONrDLzkkh9yUxDZ8MNXVM0BMN2wcXBORvkmr6GZRSB3OESZMDqAbVaobnw%3D%3D"; // 김채연
    private static final String BASE_URL = "https://apis.data.go.kr/B551011/KorService1/detailIntro1";

    private static final String DB_URL = "jdbc:oracle:thin:@211.238.142.124:1521:XE";
    private static final String DB_USER = "hr_2";
    private static final String DB_PASSWORD = "happy";
    
    static int count = 0;
    static int realCount = 0;
    /*
        System.out.println(contentIds.size());
        for (int contentid : contentIds) {
			System.out.println("contentid: " + contentid);
		}
     */
    public static void main(String[] args) throws InterruptedException {
        // DB에서 contentid 목록을 조회
    	List<Integer> contentIds = getContentIdsFromDB();
        int lastProcessedContentId = loadLastProcessedContentId(); // 🔹 마지막 성공한 contentid 불러오기

        System.out.println("마지막으로 처리된 contentid: " + lastProcessedContentId);

        boolean startProcessing = (lastProcessedContentId == 0); // 처음 실행이면 true
        for (int contentid : contentIds) {
            if (!startProcessing) {
                if (contentid == lastProcessedContentId) {
                    startProcessing = true; // 저장된 contentid 이후부터 실행
                }
                System.out.println("pass");
                continue;
            }

            String xml = fetchTourData(contentid);
            if (xml != null && !xml.isEmpty()) {
                parseAndInsert(xml);
                saveLastProcessedContentId(contentid); // 성공한 contentid 저장
                System.out.println("contentid: " + contentid + " 데이터 처리 완료");
                //Thread.sleep(100);
            } else {
                System.out.println("contentid: " + contentid + " 데이터 없음.");
            }
        }
        
    }

    // DB에서 contentid 목록을 조회하는 메서드
    public static List<Integer> getContentIdsFromDB() {
        List<Integer> contentIds = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "select content_id FROM content WHERE contenttype_id = 32 ORDER BY content_id ASC"; 
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    contentIds.add(rs.getInt("content_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentIds;
    }
    // API 호출 (contentid에 따라 XML 데이터 가져오기)
    
    static int requestCount = 0; // 요청 횟수 추적

    public static String fetchTourData(int contentid) {
        try {
            requestCount++; // 요청 횟수 증가
            StringBuilder urlBuilder = new StringBuilder(BASE_URL);
            /*
         	https://apis.data.go.kr/B551011/KorService1/detailIntro1
         	?MobileOS=etc
         	&MobileApp=test
         	&contentId=2574195
         	&contentTypeId=32
         	&serviceKey=NhaEjaIw4x6%2BYcgsO33ZBENWFUl8t9rR%2BBJILNYRY8xpFq3CNUn%2FpyQ%2FWh%2F61wynoAMKu6U8KYX%2Bwf2UhTQLFw%3D%3D
         */
            urlBuilder.append("?MobileOS=ETC")
                      .append("&MobileApp=Test")
                      .append("&contentId=").append(contentid)
                      .append("&contentTypeId=32")
                      .append("&serviceKey=").append(SERVICE_KEY);
            System.out.println("urlBuilder: " + urlBuilder); // URL
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

            // 요청 횟수 및 응답 확인
            String responseStr = response.toString();
            if (responseStr.contains("LIMITED_NUMBER_OF_SERVICE_REQUESTS_EXCEEDS_ERROR")) {
                System.out.println("요청 제한 초과! " + requestCount + "번째 요청에서 발생");
                System.exit(0);
            }

            return responseStr;
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
            } else {
            	realCount++;
				System.out.println("데이터 실제 삽입: " + realCount);
			}

            // Oracle DB 연결
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            	String sql = "INSERT INTO hotel (content_id, check_in_time, check_out_time, accom_count, chkcooking, info_center, parking, room_count, "
            			+ "subfacility, barbecue, beauty, beverage, bicycle, campfire, karaoke, fitness, publicbath, sauna, seminar, sports, refund_regulation) "
            			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    for (int i = 0; i < items.getLength(); i++) {
                        Node item = items.item(i);

                        ps.setInt(1, safeParseInt(getTagValue("contentid", item)));
                        ps.setString(2, getTagValue("checkintime", item));
                        ps.setString(3, getTagValue("checkouttime", item));
                        ps.setString(4, getTagValue("accomcountlodging", item));
                        ps.setString(5, getTagValue("chkcooking", item));
                        ps.setString(6, getTagValue("infocenterlodging", item));
                        ps.setString(7, getTagValue("parkinglodging", item));
                        ps.setString(8, getTagValue("roomcount", item));
                        ps.setString(9, getTagValue("subfacility", item));
                        ps.setInt(10, safeParseInt(getTagValue("barbecue", item)));
                        ps.setInt(11, safeParseInt(getTagValue("beauty", item)));
                        ps.setInt(12, safeParseInt(getTagValue("beverage", item)));
                        ps.setInt(13, safeParseInt(getTagValue("bicycle", item)));
                        ps.setInt(14, safeParseInt(getTagValue("campfire", item)));
                        ps.setInt(15, safeParseInt(getTagValue("karaoke", item)));
                        ps.setInt(16, safeParseInt(getTagValue("fitness", item)));
                        ps.setInt(17, safeParseInt(getTagValue("publicbath", item)));
                        ps.setInt(18, safeParseInt(getTagValue("sauna", item)));
                        ps.setInt(19, safeParseInt(getTagValue("seminar", item)));
                        ps.setInt(20, safeParseInt(getTagValue("sports", item)));
                        ps.setString(21, getTagValue("refundregulation", item));
                        
                        ps.executeUpdate();
                        count++;
                        if (count % 10 == 0) {
                            System.out.println(count + " 번째 데이터 삽입 완료");
                        }
                    }
                    //System.out.println("데이터 삽입 완료!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int safeParseInt(String value) {
        try {
            return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static double safeParseDouble(String value) {
        try {
            return (value != null && !value.isEmpty()) ? Double.parseDouble(value) : 0.0;
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
            return (value != null && !value.isEmpty()) ? value : "N/A";
        }
        return "N/A";
    }
 // 성공한 contentid 저장
    private static void saveLastProcessedContentId(int contentid) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("last_contentid.txt"))) {
            writer.write(String.valueOf(contentid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 저장된 contentid 불러오기
    private static int loadLastProcessedContentId() {
        File file = new File("last_contentid.txt");
        if (!file.exists()) return 0; // 파일이 없으면 처음부터 실행

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return Integer.parseInt(reader.readLine().trim());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
