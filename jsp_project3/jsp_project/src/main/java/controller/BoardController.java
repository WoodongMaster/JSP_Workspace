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
import domain.LikesList;
import domain.MemberVO;
import domain.PagingVO;
import handler.PagingHandler;
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

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String uri = request.getRequestURI();
		log.info(">>> uri : " + uri);
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		log.info(">>> path : " + path);

		switch (path) {
		case "register":
			destPage = "/board/register.jsp";
			break;
		case "insert":
			try {
				String title = request.getParameter("title");
				String post = request.getParameter("post");
				String writer = request.getParameter("writer");
				BoardVO bvo = new BoardVO(title, writer, post);
				isOk = bsv.insert(bvo);
				log.info(">>> isOk 값 : " + isOk);
				log.info(">>> 게시글 등록" + ((isOk > 0) ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "/brd/page";
			break;
		case "list":
			try {
				List<BoardVO> board_list = bsv.list();
				request.setAttribute("list", board_list);
				log.info(">>> 리스트 출력" + (board_list == null ? "실패" : "성공"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "/board/list.jsp";
			break;

		case "page":
			try {
				int pageNum = 1;
				int qty = 10;
				String type = "";
				String keyword = "";

				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO) ses.getAttribute("ses");

				if (request.getParameter("pageNum") != null) { // 값이 없다면 limit 1,10으로 있다면 그 값으로 limit
					pageNum = Integer.parseInt(request.getParameter("pageNum"));
					qty = Integer.parseInt(request.getParameter("qty"));
				}
				if (request.getParameter("type") != null) {
					type = request.getParameter("type");
					keyword = request.getParameter("keyword");
					log.info(">>> type : " + type + " keyword : " + keyword);
				}

				PagingVO pgvo = new PagingVO(pageNum, qty); // 이미 1,10 default로 설정되어있음
				pgvo.setType(type);
				pgvo.setKeyword(keyword);
				log.info(">>> pgvo : " + pgvo);
				int totalCount = bsv.getTotal(pgvo);
				log.info(">>>>총 개수 : " + totalCount);

				List<BoardVO> list = bsv.getPageList(pgvo); // limit를 이용한 select List를 호출(startPage, qty)
				log.info(">>>> 가져온 리스트 사이즈 : " + list.size());

				PagingHandler ph = new PagingHandler(pgvo, totalCount);
				request.setAttribute("pgh", ph);
				request.setAttribute("list", list);
				log.info(">>>> 페이지 리스트 성공");

			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "/board/list.jsp";
			break;

		case "post":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO) ses.getAttribute("ses");
				if (mvo != null) {
					String sesID = mvo.getId();
					request.setAttribute("sesID", sesID);
				}

				int bnum = Integer.parseInt(request.getParameter("bnum"));
				BoardVO bvo = bsv.post(bnum);
				log.info(">>> 게시글 출력" + (bvo == null ? "실패" : "성공"));
				request.setAttribute("post", bvo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "/board/post.jsp";
			break;

		case "modify":
			try {
				int bnum = Integer.parseInt(request.getParameter("bnum"));
				BoardVO bvo = bsv.post(bnum);
				request.setAttribute("post", bvo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "/board/modify.jsp";
			break;

		case "edit":
			try {
				int bnum = Integer.parseInt(request.getParameter("bnum"));
				String title = request.getParameter("title");
				String post = request.getParameter("post");
				BoardVO bvo = new BoardVO(bnum, title, post);
				isOk = bsv.edit(bvo);
				log.info(">>> 게시글 수정" + ((isOk > 0) ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "/brd/list";
			break;

		case "remove":
			try {
				int bnum = Integer.parseInt(request.getParameter("bnum"));
				isOk = bsv.remove(bnum);
				log.info(">>> 게시글 삭제" + ((isOk > 0) ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "/brd/list";
			break;

		case "like":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO) ses.getAttribute("ses");
				String id = mvo.getId();
				int bnum = Integer.parseInt(request.getParameter("bnum"));
				boolean value = Boolean.parseBoolean(request.getParameter("value"));
				LikesList ll = new LikesList(id, bnum, value);
				isOk = bsv.like(ll);
				log.info(">>> 게시글 추천" + ((isOk > 0) ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "/brd/list";
			break;

		}

		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

}
