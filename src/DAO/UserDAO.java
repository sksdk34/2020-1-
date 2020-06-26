package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import Util.ConnectionPool;
import VO.UserVO;

public class UserDAO {

	public String login(UserVO vo) throws SQLException, NamingException {
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
		try {
			conn = ConnectionPool.getInstance().getConn();
			
			String sql = "SELECT * FROM USER WHERE id = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, vo.getId());
			rs = st.executeQuery();
			while(rs.next()) {
				if(rs.getString(2).equals(vo.getId()) && rs.getString(3).equals(vo.getPw())) {
					return "OK";
				}
			}
			
			return "FAIL";
			
		}finally {
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
}
