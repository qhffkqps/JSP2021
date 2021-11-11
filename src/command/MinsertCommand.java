package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gangsunggengDAO.MemberDAO;
import gangsunggengDTO.MemberDTO;

public class MinsertCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		request.setCharacterEncoding("utf-8");
		}catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		// 1. ����� �Է��� ������ �޴´�
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("Pwd"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		
		//2. DB�� ����ǵ��� dto �� ����
		MemberDAO dao = new MemberDAO();
		dao.insert(dto);
	}

}
