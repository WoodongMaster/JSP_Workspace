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

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private RequestDispatcher rdp;
	private MemberService msv;
	private int isOk;
	private String destPage;
	
    public MemberController() {
        msv = new MemberServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>path: " +path);
		
		
		switch(path) {
		case "join":
			destPage = "/member/join.jsp";
			break;
			
		case "register":
			try {
				String id = request.getParameter("id");
				int dupOk = msv.dupcheck(id);
				
				if(dupOk==0) {
					String password = request.getParameter("password");
					String email = request.getParameter("email");
					int age = Integer.parseInt(request.getParameter("age")) ;
					MemberVO mvo = new MemberVO(id,password, email,age);
					isOk = msv.register(mvo);
					log.info(">>> JOIN : "+(isOk > 0 ? "성공" : "실패"));
					if(isOk==0) {
						request.setAttribute("msg_regist", 0);
						destPage="/member/join.jsp";
						break; 
					}else {				
						destPage = "/";
						break;
					}
				}else {
					request.setAttribute("msg_regist", 0);
					destPage="/member/join.jsp";
					break;		
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		case "login":
			log.info(">>>login page 이동");
			destPage = "/member/login.jsp";
			break;
			
		case "login_auth":
			try {
				
				String id = request.getParameter("id");
				String password = request.getParameter("password");
				MemberVO mvo = new MemberVO(id,password);
				MemberVO loginMvo = msv.login(mvo);
				if(loginMvo != null) {
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginMvo);
					ses.setMaxInactiveInterval(10*60);
					destPage="/";
				}else {
					request.setAttribute("msg_login", 0);
					destPage="/member/login.jsp";
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "logout":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String id = mvo.getId();
				isOk = msv.lastLogin(id);
				log.info(">>> LogOut > " + (isOk > 0 ? "성공" : "실패"));
				ses.invalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "modify":
			destPage="/member/modify.jsp";
			break;
		
		case "edit":
			try {
				String id = request.getParameter("id");
				String password = request.getParameter("password");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				String reg_date = request.getParameter("reg_date");
				String last_login = request.getParameter("last_login");
				MemberVO mvo = new MemberVO(id, password,email,age,reg_date,last_login);
				isOk = msv.edit(mvo);
				
				if(isOk >0) {			
					request.setAttribute("mvo", mvo);
					request.setAttribute("edit_msg", 0);
					log.info("세션 변경 완료");
					HttpSession ses = request.getSession();
					ses.invalidate();
				}
				log.info(">>> 정보수정 > " + (isOk > 0 ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "delete":
			try {
				HttpSession ses = request.getSession();
				log.info("ses log => "+ses);
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String id = mvo.getId();
				ses.invalidate();
				isOk = msv.delete(id);
				log.info(">>> 회원탈퇴 > " + (isOk > 0 ? "성공" : "실패"));
				request.setAttribute("delete_msg", 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "list":
			try {
				List<MemberVO> list = msv.showlist();
				request.setAttribute("mem_list", list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/member/list.jsp";
			break;
		}
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
