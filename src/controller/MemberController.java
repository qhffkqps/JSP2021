package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.MCommand;
import command.MDeleteCommand;
import command.MListCommand;
import command.MUpdateCommand;
import command.MViewCommand;
import command.MinsertCommand;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	String viewPage = null;
	MCommand command = null;
	
	String uri = request.getRequestURI();   // uri :/member-mvc/insert.do
	String com = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf(".do")); // command : insert
	
	if(com !=null && com.trim().equals("list")) {
		command = new MListCommand();
		command.execute(request, response);
		viewPage = "/WEB-INF/view/mlist.jsp";
	} else if(com != null && com.trim().contentEquals("insertForm")) {
			//ȸ���� ����� �� �ִ� form�� ������� �Ѵ�.
		viewPage = "/WEB-INF/view/insertForm.jsp";
		} else if(com != null && com.trim().contentEquals("insert")) {
			// 1. MInsertCommand ����
			command = new MinsertCommand();
			// 2. execute 1�޼ҵ� ����
			command.execute(request, response);
			// 3. ����Ʈ�� ����
			viewPage = "list.do";
		} else if(com != null && com.trim().contentEquals("view")){
			//1. MViewCommand ����
			command = new MViewCommand();
			//2. execute �޼ҵ� ȣ��
			command.execute(request, response);
			//3. �ʿ��� View �������� ����
			viewPage = "/WEB-INF/view/mView.jsp";
		} else if(com != null && com.trim().contentEquals("update")) {
			//1. MViewCommand ����
			command = new MUpdateCommand();
			//2. execute �޼ҵ� ȣ��
			command.execute(request, response);
			//3. �ʿ��� View �������� ����
			viewPage = "list.do";
		} else if(com != null && com.trim().contentEquals("delete")) {
			command = new MDeleteCommand();
			//2. execute �޼ҵ� ȣ��
			command.execute(request, response);
			//3. �ʿ��� View �������� ����
			viewPage = "list.do";
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
		} 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException {
		// System.out.println("��Ʈ�ѷ� ����");
		// String uri = request.getRequestURI();
			doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException {
			doHandle(request, response);
	
	}
}