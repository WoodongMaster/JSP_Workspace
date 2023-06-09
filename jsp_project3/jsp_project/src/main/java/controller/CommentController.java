package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;



@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);
    private int isOk;
    private CommentService csv;
    
    public CommentController() {
    	csv = new CommentServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8");
		
		String uri = request.getRequestURI();
		log.info(">>> uri : "+uri);
		String pathUri = uri.substring("/cmt/".length());
		String path = pathUri;
		String pathVar = "";
		if(pathUri.contains("/")) {
			path = pathUri.substring(0, pathUri.lastIndexOf("/"));
			pathVar = pathUri.substring(pathUri.lastIndexOf("/")+1);
		}
		
		log.info(path);
		log.info(pathVar);
		switch(path) {
		case "post":
			try {
				// js에서 보낸 데이터를 StringBuffer로 읽어들이는 작업.
				StringBuffer sb = new StringBuffer();
				String line ="";
				BufferedReader br = request.getReader();
				while((line = br.readLine()) != null) { // 값이 남아 있다면
					sb.append(line); // StringBuffer sb에 line 추가 sb.append(line)
				}
				log.info(">>>> sb : "+sb.toString());
				
				JSONParser parser = new JSONParser(); // JSON 데이터를 읽어올 수 있는 JSONParser 호출
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString()); // JSONParser를 통해 StringBuffer(sb)를 JSON Object로 변환
				
				int bnum = Integer.parseInt(jsonObj.get("bnum").toString()); 
				String writer = jsonObj.get("writer").toString();
				String comment = jsonObj.get("comment").toString();
				CommentVO cvo = new CommentVO(bnum,writer,comment);
				isOk = csv.post(cvo);
				log.info(">>>> post : "+(isOk>0?"성공":"실패"));
				// 결과 전송
				PrintWriter out = response.getWriter(); // AJAX의 경우 destPage 역할을 printWriter가 함
				out.print(isOk);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			break;
				
		case "list":
			try {
				int bnum = Integer.parseInt(pathVar);
				List<CommentVO> list = csv.getList(bnum);
				log.info(">>>> list 가져오기 : "+(list!=null?"성공":"실패"));
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				JSONArray jsonObjList = new JSONArray();
				for(int i=0; i<list.size(); i++) {
					jsonObjArr[i] = new JSONObject();
					jsonObjArr[i].put("cnum", list.get(i).getCnum());
					jsonObjArr[i].put("bnum", list.get(i).getBnum());
					jsonObjArr[i].put("writer", list.get(i).getWriter());
					jsonObjArr[i].put("comment", list.get(i).getComment());
					jsonObjArr[i].put("reg_date", list.get(i).getReg_date());
					
					jsonObjList.add(jsonObjArr[i]);
				}
				String jsonData = jsonObjList.toJSONString();
				log.info(">>>> jsonObjectList : "+ jsonData);
				PrintWriter out = response.getWriter(); // AJAX의 경우 destPage 역할을 printWriter가 함
				out.print(jsonData);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "remove":
			try {
				int cnum = Integer.parseInt(request.getParameter("cnumVal"));
				log.info(">>> cnum : "+cnum);
				isOk = csv.remove(cnum);
				log.info("댓글 삭제 : "+ ((isOk>0)?"성공":"실패"));
				PrintWriter out = response.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				log.info("remove error!");
				e.printStackTrace();
			}
			break;
			
		case "modify":
			try {
				StringBuffer sb = new StringBuffer();
				String line ="";
				BufferedReader br = request.getReader();
				while((line = br.readLine()) != null) { // 값이 남아 있다면
					sb.append(line); // StringBuffer sb에 line 추가 sb.append(line)
				}
				log.info(">>>> sb : "+sb.toString());
				
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				
				int cnum = Integer.parseInt(jsonObj.get("cnum").toString()); 
				String writer = jsonObj.get("writer").toString();
				String comment = jsonObj.get("comment").toString();
				CommentVO cvo = new CommentVO(writer,comment,cnum);
				isOk = csv.modify(cvo);
				log.info(">>>> modify : "+(isOk>0?"성공":"실패"));
				// 결과 전송
				PrintWriter out = response.getWriter(); // AJAX의 경우 destPage 역할을 printWriter가 함
				out.print(isOk);
			} catch (Exception e) {
				log.info("modify error!");
				e.printStackTrace();
			}
			break;
		}

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}

}
