package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gangsunggengDAO.MemberDAO;
import gangsunggengDTO.MemberDTO;

public class MListCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DB �۾��� ���� DBó���� �ϰ� �ִ� MemberDAO ��ü�� �����Ѵ�.
		MemberDAO dao = new MemberDAO();
		// DB���� ������ ArrayList ���� MemberDTO ��ü���� dtos ������ �����Ѵ�.
		ArrayList<MemberDTO> dtos = dao.list();
		// dtos�� request scope�� �����Ͽ� View�� ȭ����� �� �� �ֵ��� �غ��Ѵ�.
		request.setAttribute("dtos", dtos);

	}

}
