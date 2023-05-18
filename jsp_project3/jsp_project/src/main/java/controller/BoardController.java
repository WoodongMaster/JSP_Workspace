package controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import service.BoardService;
import service.BoardServiceImpl;


@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(BoardController.class);
    private RequestDispatcher rdp;
    private String destPage;
    private int isOk;
    private BoardService bsv;
    
    public BoardController() {
    	bsv = new BoardServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8");
		
		String uri = request.getRequestURI();
		log.info(">>> uri : "+uri);
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>> path : "+path);
		
		switch(path) {
		case "register":
			destPage="/board/register.jsp";
			break;
		case "insert":
			try {
				String title = request.getParameter("title");
				String post = request.getParameter("post");
				String writer = request.getParameter("writer");
				BoardVO bvo = new BoardVO(title,writer,post);
				isOk = bsv.insert(bvo);
				log.info(">>> isOk 값 : "+isOk);
				log.info(">>> 게시글 등록"+ ( (isOk>0) ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/brd/list";
			break;
		case "list":
			try {
				List<BoardVO> board_list =  bsv.list();
				request.setAttribute("list", board_list);
				log.info(">>> 리스트 출력"+ ( board_list==null ? "실패" : "성공"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/board/list.jsp";
			break;
			
		case "post":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String sesID= mvo.getId();
				int bnum = Integer.parseInt(request.getParameter("bnum"));
				BoardVO bvo = bsv.post(bnum);
				log.info(">>> 게시글 출력"+ ( bvo==null ? "실패" : "성공"));
				request.setAttribute("post", bvo);
				log.info(">> ses.id : "+sesID+"writer : "+bvo.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/board/post.jsp";
			break;
			
		case "modify":
			try {
				int bnum = Integer.parseInt(request.getParameter("bnum"));
				BoardVO bvo = bsv.post(bnum);
				request.setAttribute("post", bvo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/board/modify.jsp";
			break;
			
		case "edit":
			try {
				int bnum = Integer.parseInt(request.getParameter("bnum"));
				String title = request.getParameter("title");
				String post = request.getParameter("post");
				BoardVO bvo = new BoardVO(bnum,title,post);
				isOk = bsv.edit(bvo);
				log.info(">>> 게시글 수정"+ ( (isOk>0) ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/brd/list";
			break;
		
		case "remove":
			try {
				int bnum = Integer.parseInt(request.getParameter("bnum"));
				isOk = bsv.remove(bnum);
				log.info(">>> 게시글 삭제"+ ( (isOk>0) ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/brd/list";
			break;	
		}
		
		
		rdp=request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}

}
