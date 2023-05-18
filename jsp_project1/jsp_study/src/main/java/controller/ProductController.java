package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductVO;
import service.ProductService;
import service.Service;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service svc;
	
    public ProductController() {
        svc = new ProductService();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주 작업영역
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		String uri = request.getRequestURI();
		System.out.println(">>> uri : "+uri);
		String context = request.getContextPath();
		System.out.println(">>> context : "+context);
		
		String destPage = "";
		
		switch(uri) {
		case "/register.pd":
			destPage="/register.jsp";	 // 루트위치 : webapp , jsp가 폴더 안에 있으면 /폴더명/..jsp
			break;
		case "/insert.pd":
			// DB연결, 등록처리
			// JSP에서 객체 가져옴
			// service 에서 가져온 데이터를 객체화하여 db에 저장
			
			// 1.객체생성
			String pname = request.getParameter("pname");
			int price = Integer.parseInt(request.getParameter("price"));
			String madeby = request.getParameter("madeby");
			ProductVO pvo = new ProductVO(pname,price,madeby);
			
			// 2.service에 객체주고 요청
			int isOk = svc.register(pvo);
			System.out.println(">>> 상품등록 "+((isOk>0)?"성공":"실패"));
			
			destPage="/list.pd";
			break;
		case "/list.pd":
			List<ProductVO> list = new ArrayList<>();
			
			list =svc.list();
			System.out.println(list.size());
			request.setAttribute("list", list);
			System.out.println("list 출력 성공");
			destPage ="/list.jsp";
			break;
			
		case "/detail.pd":
			int pno = Integer.parseInt(request.getParameter("pno"));
			ProductVO detail_p = new ProductVO();
			detail_p = svc.detail(pno);
			request.setAttribute("pvo", detail_p);
			System.out.println("상품 상세 출력 성공");
			destPage="/detail.jsp";
			break;
			
		case "/remove.pd":
			int remove_pno = Integer.parseInt(request.getParameter("pno"));
			int remove_Ok = svc.remove(remove_pno);
			System.out.println(">>> 상품삭제 "+((remove_Ok>0)?"성공":"실패"));
			destPage="/list.pd";
			break;
			
		case "/edit.pd":
			pno = Integer.parseInt(request.getParameter("pno"));
			detail_p = new ProductVO();
			detail_p = svc.detail(pno);
			request.setAttribute("pvo", detail_p);
			System.out.println("수정페이지 상품 상세 출력 성공");
			destPage="/modify.jsp";
			break;
			
		case "/modify.pd":
			int modify_pno = Integer.parseInt(request.getParameter("pno"));
			String modify_pname = request.getParameter("pname");
			int modify_price = Integer.parseInt(request.getParameter("price"));
			String modify_madeby = request.getParameter("madeby");
			ProductVO modify_pvo = new ProductVO(modify_pno, modify_pname,modify_price,modify_madeby);
			int modify_Ok = svc.modify(modify_pvo);
			System.out.println(">>> 상품수정 "+((modify_Ok>0)?"성공":"실패"));
			destPage="/list.pd";
			break;
		}
		
		
		RequestDispatcher rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 오는 값을 체킹
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식으로 오는 값을 체킹
		service(request, response);
	}

}
