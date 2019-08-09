package model.dao;

import db.DbJava;
import model.dao.impl.StudentDaoJDBC;

public class DaoFactory {
	
	public static StudentDao createStudentDao() {
		return new StudentDaoJDBC(DbJava.getConnection());
	}
}
