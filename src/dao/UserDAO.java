package dao;

import static dao.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.User;

public class UserDAO extends DAO{
    public UserDAO() {
        super();
    }
    
    //Kiểm tra đăng nhập
    public boolean checkLogin(User user) {
        boolean result = false;
        String sql = "SELECT name, position, id FROM tblUser WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setName(rs.getString("name"));
                user.setPosition(rs.getString("position"));
                user.setId(rs.getInt("id"));
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    //Kiểm tra xêm đã tồn tại username trong hệ thống hay chưa
    public boolean checkExistUsername(String username){
        boolean result = false;
        String sql = "SELECT * FROM tblUser WHERE username = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean save(User user){
        String sql = "INSERT INTO tblUser (username, password, name, email, position, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean rs=false;
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPosition());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getPhone());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
            rs=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    public ArrayList<User> findByKey(String key) {
        ArrayList<User> list = new ArrayList();
        String sql = "SELECT * FROM tblUser WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setPhone(rs.getString("phone"));
                u.setPosition(rs.getString("position"));
                u.setUsername(rs.getString("username"));
                u.setName(rs.getString("name"));
                
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean update(User user) {
        String sql = "UPDATE tblUser SET name=?, email=?, position=?, address=?, phone=? WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPosition());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getPhone());
            ps.setInt(6, user.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
