package model.dao;

import java.util.List;

import model.entities.Student;

public interface StudentDao {
	
	void insert (Student obj);
	void update (Student obj);
	void deleteByRa (Integer ra);
	Student findByRa (Integer ra);
	List<Student> findAll();
}
