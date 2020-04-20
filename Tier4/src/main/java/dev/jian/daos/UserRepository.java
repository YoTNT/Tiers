package dev.jian.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.jian.entities.Role;
import dev.jian.entities.User;
import dev.jian.utils.ConnectionUtil;

public class UserRepository implements CurdRepository<User>{

	public User save(User t) {
		
		try(Connection conn = ConnectionUtil.createConnection()){
			
			String sql0 = "SELECT * FROM tier3.USER_ROLL WHERE NAME = ?";
			PreparedStatement ps0 = conn.prepareStatement(sql0);
			ps0.setString(1, t.getRole().getRoleName());
			
			ResultSet rs0 = ps0.executeQuery();
			rs0.next();
			int roleId = rs0.getInt("ROLE_ID");
			
			String sql = "INSERT INTO tier3.APP_USER VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, t.getUsername());
			ps.setString(3, t.getPassword());
			ps.setString(4, t.getFirstName());
			ps.setString(5, t.getLastName());
			ps.setInt(6, roleId);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("USER_ID");
			
			t.setId(key);
			
			return t;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Set<User> findAll() {
		
		try(Connection conn = ConnectionUtil.createConnection()){
			
			String sql = "SELECT * FROM tier3.APP_USER";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Set<User> users = new HashSet<User>();
			
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("USER_ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setFirstName(rs.getString("FIRST_NAME"));
				u.setFirstName(rs.getString("LAST_NAME"));
				
				String sql0 = "SELECT * FROM USER_ROLE WHERE ROLE_ID = ?";
				PreparedStatement ps0 = conn.prepareStatement(sql0);
				ps0.setInt(1, rs.getInt("ROLE_ID"));
				
				ResultSet rs0 = ps0.executeQuery();
				rs0.next();
				String roleName = rs0.getString("NAME");
				
				Role r = new Role();
				r.setRoleName(roleName);
				
				u.setRole(r);
				
				users.add(u);
			}
			
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
