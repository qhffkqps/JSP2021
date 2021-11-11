package gangsunggengDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import gangsunggengDTO.MemberDTO;


public class MemberDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDAO() {
		System.out.println("dao");
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/KSK");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(con !=null) {
				con.close();
				con=null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	


public ArrayList<MemberDTO> list(){
	String sql = "SELECT * FROM MEMBER";
	
	ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		rs= pstmt.executeQuery();
		
		while (rs.next()) {
			MemberDTO dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setPwd(rs.getNString("pwd"));
			dto.setName(rs.getNString("name"));
			dto.setEmail(rs.getNString("email"));
			dto.setJoinDate(rs.getDate("joinDate"));
			dtos.add(dto); // MemberDTO객체를 ArrayList에 추가한다.
		}
		rs.close();
		pstmt.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close();
	}
	return dtos;
	}
// DB에 회원정보를 저장할 메소드 작성
	public void insert(MemberDTO dto) {
		// 1. SQL - insert
		String sql = "Insert Into MEMBER(id,pwd,name,email,joinDate) values(?,?,?,?,SYSDATE)";
		// 2. Connection 얻어오기
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
		public MemberDTO view(String id) {
		
			String sql = "SELECT * FROM MEMBER WHERE ID = ? ";
			
			MemberDTO dto = new MemberDTO();
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setNString(1,id);
				rs= pstmt.executeQuery();
				rs.next();
				
				dto.setId(id);
				dto.setPwd(rs.getNString("pwd"));
				dto.setName(rs.getNString("name"));
				dto.setEmail(rs.getNString("email"));
				dto.setJoinDate(rs.getDate("joinDate"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dto;
	}
		//update
		public void update(MemberDTO dto) {
			String sql = "UPDATE MEMBER SET PWD = ?, NAME = ?, EMAIL = ?, JOINDATE = ? WHERE ID = ?";
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, dto.getPwd());
				pstmt.setString(2, dto.getName());
				pstmt.setString(3, dto.getEmail());
				pstmt.setDate(4, dto.getJoinDate());
				pstmt.setString(5, dto.getId());
				pstmt.executeLargeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close();
			}
		}
		//delete
		public void delete(String id) {
			String sql = "DELETE FROM MEMBER WHERE ID = ?";
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,id);
				
				pstmt.executeUpdate();
				pstmt.close();
				
			} catch(SQLException e){
				e.printStackTrace();
			}finally {
				close();
			}
		}
}