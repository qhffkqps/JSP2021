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
		// DB 작업을 위해 DB처리를 하고 있는 MemberDAO 객체를 생성한다.
		MemberDAO dao = new MemberDAO();
		// DB에서 가져온 ArrayList 내의 MemberDTO 객체들을 dtos 변수에 저장한다.
		ArrayList<MemberDTO> dtos = dao.list();
		// dtos를 request scope에 저장하여 View가 화면출력 할 수 있도록 준비한다.
		request.setAttribute("dtos", dtos);

	}

}
