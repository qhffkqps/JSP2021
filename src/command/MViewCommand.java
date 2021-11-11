package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gangsunggengDAO.MemberDAO;
import gangsunggengDTO.MemberDTO;

public class MViewCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		//1. MemberDao�� ����
		MemberDAO dao = new MemberDAO();
		//2. �ʿ��� �޼ҵ带 ȣ��
		MemberDTO dto = dao.view(id);
		//3. ��ȯ�� ���� request �������� ����
		request.setAttribute("dto", dto);
	}

}
