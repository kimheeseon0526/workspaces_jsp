package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Member;
import util.DButil;

//DAO
//Data Access Object
public class MemberDao {
	// insert
	// select

	public static void main(String[] args) {
//		new MemberDao().insert(Member.builder().id("sample").pw("1234").name("개똥이").build());
		System.out.println(new MemberDao().selectOne("sae"));
		System.out.println(new MemberDao().selectOne("jae"));
		System.out.println(new MemberDao().selectOne("3L"));
		
	}

	public void insert(Member member) {
		// 1. 접속 객체 취득 2. 문장 생성 3. 실행 후 처리
		Connection conn = DButil.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO member (ID, PW, name) VALUES (?, ?, ?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());

			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Member selectOne(String id) {
		Connection conn = DButil.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("Select * from member where id = ?");
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = Member.builder().num(rs.getLong("num")).id(rs.getString("id")).pw(rs.getString("pw"))
						.name(rs.getString("name")).regDate(rs.getDate("regdate")).build();

				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Member selectOne(Long num) {
		Connection conn = DButil.getConnection();
		try {
		PreparedStatement pstmt = conn.prepareStatement("Select * from member where num = ?");
		pstmt.setLong(1, num);
		
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Member member = Member.builder().num(rs.getLong("num")).id(rs.getString("id")).pw(rs.getString("pw"))
					.name(rs.getString("name")).regDate(rs.getDate("regdate")).build();
			
			return member;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
}
