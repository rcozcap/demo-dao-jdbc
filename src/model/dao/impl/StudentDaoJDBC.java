package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import db.DbJava;
import model.dao.StudentDao;
import model.entities.Student;

public class StudentDaoJDBC implements StudentDao{

	private Connection conn;
	
	public StudentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Student obj) {
			PreparedStatement st = null; 
			try {
				st = conn.prepareStatement(
						"INSERT INTO student VALUES (null, ?)",Statement.RETURN_GENERATED_KEYS);
				st.setString(1, obj.getName());
				
				int rowsAffected = st.executeUpdate();
				
				if(rowsAffected > 0) {
					ResultSet rs = st.getGeneratedKeys();
				} else {
					throw new DbException("Unexpected Error! No rows affected");
				}
			}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DbJava.closeStatement(st);
		}
	}

	@Override
	public void update(Student obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByRa(Integer RA) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Student findByRa(Integer ra) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM ti2p22 WHERE RA = ?");
			st.setInt(1, ra);	
			rs = st.executeQuery();	
			if (rs.next()) {
				Student obj = instantiateStudent(rs);
				return obj;
			}
			return null;
			}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DbJava.closeStatement(st);
			DbJava.closeResultSet(rs);
		}
	}

	private Student instantiateStudent(ResultSet rs) throws SQLException {
		Student obj = new Student();
		obj.setRa(rs.getInt("RA"));
		obj.setName(rs.getString("NAME"));
		return obj;
	}

	@Override
	public List<Student> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT RA, NAME FROM student");
			
			rs = st.executeQuery();	
			
			List<Student> list = new ArrayList<>();
			
			while (rs.next()) {
				
				Student stud = instantiateStudent(rs);
				list.add(stud);
				}
				return list;
			}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DbJava.closeStatement(st);
			DbJava.closeResultSet(rs);
		}
	}
}
