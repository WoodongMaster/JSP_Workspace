package controller;

import java.io.File;



import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
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
	private String savePath;
	private final String UTF8 = "utf-8";

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
				savePath = getServletContext().getRealPath("/_fileUpload"); 
				log.info(">>> 파일 저장 경로 : "+savePath);
				File fileDir = new File(savePath);
				
				DiskFileItemFactory FIF = new DiskFileItemFactory();
				FIF.setRepository(fileDir); 
				FIF.setSizeThreshold(2*1024*1024); 
				
				BoardVO bvo = new BoardVO();

				ServletFileUpload fileUpload = new ServletFileUpload(FIF);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString(UTF8)); 
						break;
					case "writer":
						bvo.setWriter(item.getString(UTF8));
						break;
					case "post":
						bvo.setPost(item.getString(UTF8));
						break;
					case "imgfile":
						log.info(">>> item 사이즈 : "+item.getSize());
						if(item.getSize() > 0) {
							String fileName = item.getName().substring(item.getName().lastIndexOf("/")+1); 
							fileName = System.currentTimeMillis()+"_"+fileName;
							log.info(">>> fileName : "+fileName);
							
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							log.info(">>> 실제 파일 경로 : "+ uploadFilePath);
							
							try {
								item.write(uploadFilePath);
								bvo.setImgfile(fileName);
										
								Thumbnails.of(uploadFilePath).size(75,75).toFile(new File(fileDir+File.separator+"th_"+fileName));
								
							} catch (Exception e) {
								log.info(">>> file writer 실패");
								e.printStackTrace();
							}
						}
						break;
					}
				}
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

				if (request.getParameter("pageNum") != null) { 
					pageNum = Integer.parseInt(request.getParameter("pageNum"));
					qty = Integer.parseInt(request.getParameter("qty"));
				}
				if (request.getParameter("type") != null) {
					type = request.getParameter("type");
					keyword = request.getParameter("keyword");
					log.info(">>> type : " + type + " keyword : " + keyword);
				}

				PagingVO pgvo = new PagingVO(pageNum, qty); 
				pgvo.setType(type);
				pgvo.setKeyword(keyword);
				log.info(">>> pgvo : " + pgvo);
				int totalCount = bsv.getTotal(pgvo);
				log.info(">>>>총 개수 : " + totalCount);

				List<BoardVO> list = bsv.getPageList(pgvo);
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
				log.info(">>> 저장된 imgfile : "+ bvo.getImgfile());
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
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				
				DiskFileItemFactory FIF = new DiskFileItemFactory();
				FIF.setRepository(fileDir);
				FIF.setSizeThreshold(2*1024*1024);
				
				BoardVO bvo = new BoardVO();
				ServletFileUpload fileUpload = new ServletFileUpload(FIF);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				
				String old_imgfile = "";
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "bnum":
						bvo.setBnum(Integer.parseInt(item.getString(UTF8)));
						break;
					case "title":
						bvo.setTitle(item.getString(UTF8));
						break;
					case "post":
						bvo.setPost(item.getString(UTF8));
						break;
					case "imgfile":
						old_imgfile = item.getString(UTF8);
						break;
					case "new_imgfile":
						log.info(">>> item : "+item);
						if(item.getSize()>0) { 
							if(old_imgfile!=null) {						
								FileHandler fh = new FileHandler();
								isOk = fh.deleteFile(old_imgfile, savePath);
								log.info((isOk==1 ? "교체성공" : "교체실패"));
							}

							String fileName = item.getName().substring(item.getName().lastIndexOf("/")+1);
							log.info(">>> new_imgfileName : "+fileName);

							fileName = System.currentTimeMillis()+"_"+fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							try {
								item.write(uploadFilePath);
								bvo.setImgfile(fileName);
								log.info(">>> bvo.imgfile : "+bvo.getImgfile());
								
								Thumbnails.of(uploadFilePath).size(75, 75).toFile(new File(fileDir+File.separator+"th_"+fileName));
							} catch (Exception e2) {
								log.info(">>> img file update 오류");
								e2.printStackTrace();
							}
						}else {
							bvo.setImgfile(old_imgfile);
						}
						break;
					}
				}
				isOk = bsv.edit(bvo);
				log.info(">>> 게시글 수정" + ((isOk > 0) ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "/brd/page";
			break;

		case "remove":
			try {
				int bnum = Integer.parseInt(request.getParameter("bnum"));
				savePath = getServletContext().getRealPath("/_fileUpload");
				
				FileHandler fh = new FileHandler();
				String fileName = request.getParameter("imgName");
				fh.deleteFile(fileName, savePath);
				isOk = bsv.remove(bnum);
				log.info(">>> 게시글 삭제" + ((isOk > 0) ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "/brd/page";
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
