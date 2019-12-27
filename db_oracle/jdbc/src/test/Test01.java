package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test01 {
	public static void main(String[] args) {
		System.out.println("JDBC TEST");
		String driver = "oracle.jdbc.OracleDriver"; // 오라클인지 MYSQL인지
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; // 위치
		String user = "SCOTT";
		String pw = "TIGER";
		
		String sql = "select * from dept";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		// select문 실행시 나오는 테이블셋은 resultset으로 온다
		// insert update delete 결과의 리턴값은 int타입이다
		
		
		try {
			// 1. Driver클래스를 로딩
			Class.forName(driver);
			
			// 2. 로딩된 Driver클래스를 이용해서 Connection요청(url,user,pw)
			con = DriverManager.getConnection(url, user, pw);
			
			// 3. 생성된 Connection으로부터 Statement생성
			ps = con.prepareStatement(sql);
			
			// 4. 생성된 Statement를 이용해서 sql수행(execute, executeUpdate, executeQuery)
			rs = ps.executeQuery();
			
			// 5. 결과 처리(ResultSet, int)
			while(rs.next()) {
				System.out.print(rs.getString("deptno")+" ");
				System.out.print(rs.getString("dname")+" ");
				System.out.print(rs.getString("loc")+" ");
				System.out.println();
			}
					
			//System.out.println(con);
			
		} catch (Exception e) {
			// 6. SQLException 처리(try, catch, finally)
			System.out.println(e);
		}finally {
			// 7. 자원정리(connection, statement, resultset)
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		
		
		System.out.println("JDBC TEST END");
	}
}
