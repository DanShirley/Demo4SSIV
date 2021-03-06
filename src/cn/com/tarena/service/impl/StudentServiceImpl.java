package cn.com.tarena.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.tarena.pojo.Student;
import cn.com.tarena.service.StudentService;

public class StudentServiceImpl implements StudentService {

	@Override
	public List getStudentList() {
		
		List studentList = new ArrayList();
		
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from student");
			
			while(rs.next()){
				Student student = new Student();
				
				student.setUserName(rs.getString("USER_NAME"));
				student.setPassword(rs.getString("PASSWORD"));
				student.setGender(rs.getString("PROVINCE"));
				student.setProvince(rs.getString("GENDER"));
				student.setHobbies(rs.getString("HOBBIES"));	
				
				studentList.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error when querying students!",e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("error when querying students!",e);
			}
		}
		
		return studentList;
	}

}
