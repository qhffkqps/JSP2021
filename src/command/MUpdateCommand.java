package command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gangsunggengDAO.MemberDAO;
import gangsunggengDTO.MemberDTO;

public class MUpdateCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("Update");
		//1. 변경된 데이터 가져오기 - dto 에 담는다.
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setJoinDate(Date.valueOf(request.getParameter("joinDate")));
		
		//2. dto를 DB에 업데이트 할 수 있는 MemberDAO 메소드에 전달
		MemberDAO dao = new MemberDAO();
		dao.update(dto);
	}

}
