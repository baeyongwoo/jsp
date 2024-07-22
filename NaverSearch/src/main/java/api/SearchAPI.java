package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchAPI
 */
@WebServlet("/NaverSearchAPI.do")
public class SearchAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletresponseonse responseonse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		        // 1. 인증 정보 설정
		        String clientId = "pUys5uiamifJGNwFbNxm";
		        String clientSecret = "kMgcg_KVmh";

		        // 2. 검색 조건 설정
		        String startNum = "0";    // 검색 시작 위치
		        String text = null;  // 검색어
		        try {
		             startNum = (request.getParameter("startNum"));
		             String searchText = request.getParameter("keyword");
		             text = URLEncoder.encode(searchText, "UTF-8");
		        } catch (UnsupportedEncodingException e) {
		            throw new RuntimeException("검색어 인코딩 실패", e);
		        }

		        // 3. API URL 조합
		        //블로그 검색
//		        String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text
//		                        + "&display=10&start=" + startNum;  // json 결과
		        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query=" + text;  // xml 결과
		        
		        //전체검색
//		        String apiURL = "https://openapi.naver.com/v1/search/webkr.json?query=" + text
//		        				+ "&display=10&start=" + startNum;  // json 결과
		        //특정 사이트에서 검색
		        String url = "okky.kr";
		        String apiURL = "https://openapi.naver.com/v1/search/webkr.json?query=" + text + "+site%3A"+url  
        				+ "&display=10&start=" + startNum;  // json 결과		
		        		
		        // 4. API 호출
		        Map<String, String> requestHeaders = new HashMap<>();
		        requestHeaders.put("X-Naver-Client-Id", clientId);
		        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		        String responseonseBody = get(apiURL, requestHeaders);

		        // 5. 결과 출력
		        System.out.println(responseonseBody);  // 콘솔에 출력

		        response.setContentType("text/html; charset=utf-8");
		        response.getWriter().write(responseonseBody);  // 서블릿에서 즉시 출력
		    }

		    private static String get(String apiUrl, Map<String, String> requestHeaders){
		        HttpURLConnection con = connect(apiUrl);
		        try {
		            con.setRequestMethod("GET");
		            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
		                con.setRequestProperty(header.getKey(), header.getValue());
		            }


		            int responseonseCode = con.getResponseCode();
		            if (responseonseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
		                return readBody(con.getInputStream());
		            } else { // 에러 발생
		                return readBody(con.getErrorStream());
		            }
		        } catch (IOException e) {
		            throw new RuntimeException("API 요청과 응답 실패", e);
		        } finally {
		            con.disconnect();
		        }
		    }


		    private static HttpURLConnection connect(String apiUrl){
		        try {
		            URL url = new URL(apiUrl);
		            return (HttpURLConnection)url.openConnection();
		        } catch (MalformedURLException e) {
		            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		        } catch (IOException e) {
		            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		        }
		    }


		    private static String readBody(InputStream body){
		        InputStreamReader streamReader = new InputStreamReader(body);


		        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
		            StringBuilder responseonseBody = new StringBuilder();


		            String line;
		            while ((line = lineReader.readLine()) != null) {
		                responseonseBody.append(line);
		            }


		            return responseonseBody.toString();
		        } catch (IOException e) {
		            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		        }
		    }
		
		

		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletresponseonse responseonse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse responseonse) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, responseonse);
	}

}
