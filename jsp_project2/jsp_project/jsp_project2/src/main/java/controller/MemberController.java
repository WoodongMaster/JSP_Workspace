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
import service.MemberServiceImp;


@WebServlet("/mem/*") //'/*'를 붙이는 이유는 하위 경로때문
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //log 설정
	//private static final Logger log = LoggerFactory.getLogger(클래스명.class);
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private RequestDispatcher rdp;
	private MemberService msv;
	private int isOk; //확인
	private String destPage; //목적지주소
	
	
  
    public MemberController() {
        msv = new MemberServiceImp(); //멤버의 서비스를 구현한 객체
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// charactorEncoding 설정 / contentType / uri경로 확인
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8");
		
		String uri = request.getRequestURI(); //전체 요청경로
		// uri = mem/join -> 요청에 대한 path만  남길래!
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>path: " +path);
		
		
		switch(path) {
		case "join":
			destPage = "/member/join.jsp";
			break;
			
		case "register":
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			int age = Integer.parseInt(request.getParameter("age")) ;
			//파라미터로 mvo객체 생성
			MemberVO mvo = new MemberVO(id,password, email,age);
			
			isOk = msv.register(mvo);
			log.info(">>> JOIN > " + (isOk > 0 ? "성공" : "실패"));
			destPage = "/"; //이렇게만 적어도 index페이지로 이동
			break;
			
		case "login":
			log.info(">>>login page 이동");
			destPage = "/member/login.jsp";
			break;
			
		case "login_auth":
			try {
				
				String id2 = request.getParameter("id");
				String password2 = request.getParameter("password");
				MemberVO mvo2 = new MemberVO(id2,password2);
				// 해당 id, password가 db상에 있는지 체크
				// 해당 객체(사용자)를 가져와서 세션에 담기
				MemberVO loginMvo = msv.login(mvo2);
				// 같은 정보가 있으면 객체를 리턴, 없으면 null 리턴
				if(loginMvo != null) {
					// 세션을 가져오기, 연결된 세션이 없다면 새로 생성
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginMvo);
					// 로그인 유지시간(초단위)
					ses.setMaxInactiveInterval(10*60);
				}else {
					request.setAttribute("msg_login", 0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "logout":
			try {
				// 세션 가져오기 (연결된 세션)
				HttpSession ses = request.getSession();
				MemberVO mvo2 = (MemberVO)ses.getAttribute("ses");
				String id2 = mvo2.getId();
				log.info(">>>> login ID : "+id2);
				// msv한테 로그인정보(id)를 주고 마지막 로그인 시간 기록
				isOk = msv.lastLogin(id2);
				log.info(">>> LogOut > " + (isOk > 0 ? "성공" : "실패"));
				ses.invalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "modify_rdy":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo2 = (MemberVO)ses.getAttribute("ses");
				log.info(">>>> modify_rdy mvo2 : "+ mvo2);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			destPage="/member/modify.jsp";
			break;
		
		case "modify":
			try {
				String id2 = request.getParameter("id");
				String password2 = request.getParameter("password");
				String email2 = request.getParameter("email");
				int age2 = Integer.parseInt(request.getParameter("age"));
				String reg_date2 = request.getParameter("reg_date");
				String last_login2 = request.getParameter("last_login");
				MemberVO mvo2 = new MemberVO(id2, password2,email2,age2,reg_date2,last_login2);
				isOk = msv.edit(mvo2);
				
				if(isOk >0) {			
					//세션 동기화 방법 2가지
					
					//case 1 :  session 객체에 값을 담아서 넘기기
					//HttpSession ses = request.getSession();
					//ses.setAttribute("ses", mvo2);
					
					//case 2 : login_auth로 mvo2 객체 보내기
					request.setAttribute("mvo", mvo2);
					log.info("세션 변경 완료");
					
					//case 3 : 서비스에서 login 호출하여 id,pw만 주고 객체정보 전체 가져옴
					//mvo2 = msv.login(id);
					//HttpSession ses = request.getSession();
					//ses.setAttribute("ses", mvo2);
				}
				log.info(">>> 정보수정 > " + (isOk > 0 ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="login_auth";
			break;
			
		case "delete":
			try {
				HttpSession ses = request.getSession();
				log.info("ses log => "+ses);
				MemberVO mvo2 = (MemberVO)ses.getAttribute("ses");
				String id2 = mvo2.getId();
				ses.invalidate();
				isOk = msv.delete(id2);
				log.info(">>> 회원탈퇴 > " + (isOk > 0 ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "list":
			try {
				List<MemberVO> list = msv.showlist();
				HttpSession ses = request.getSession();
				ses.setAttribute("ses", list);
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
		// TODO Auto-generated method stub
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
