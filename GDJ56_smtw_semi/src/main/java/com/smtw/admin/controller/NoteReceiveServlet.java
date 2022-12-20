package com.smtw.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smtw.mypage.model.service.MypageService;
import com.smtw.mypage.model.vo.Note;

/**
 * Servlet implementation class NoteReceiveServlet
 */
@WebServlet("/admin/noteReceive.do")
public class NoteReceiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoteReceiveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		System.out.println(id);
		
		int cPage;
		int numPerpage=5;
		
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		System.out.println("cpage : "+cPage);
		List<Note> list = new MypageService().noteList(id, cPage, numPerpage);
		System.out.println(list);
		
		int totalData=new MypageService().selectNoteCount(id);
		System.out.println(totalData);
		
		String pageBar="";
		int pageBarSize=5;
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;//5
		int pageEnd=pageNo+pageBarSize-1;//9
		
		if(pageNo==1) {
			pageBar+="<button class='customBtn btnStyle'>이전</button>";
		}else {
			pageBar+="<button class='customBtn btnStyle'><a href='"+request.getContextPath()
				+"/mypage/mypageNoteReceive.do?cPage="+(pageNo-1)+"'>이전</a></button>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<button class='customBtn btnStyle'>"+pageNo+"</button>";
			}else {
				pageBar+="<a href='"+request.getContextPath()
				+"/mypage/mypageNoteReceive.do?cPage="+pageNo+"&id="+id+"'><button class='customBtn btnStyle'>"+pageNo+"</button></a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<button class='customBtn btnStyle'>다음</button>";
		}else {
			pageBar+="<a href='"+request.getContextPath()
				+"/mypage/mypageNoteReceive.do?cPage="+pageNo+"&id="+id+"'>다음</a>";
		}

		request.setAttribute("pageBar", pageBar);
		
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/admin/noteReceive.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
