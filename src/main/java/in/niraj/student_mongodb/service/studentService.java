package in.niraj.student_mongodb.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import in.niraj.student_mongodb.exception.StudentException;
import in.niraj.student_mongodb.model.stud_mongo;

public interface studentService {

	public void saveStudent(stud_mongo stud)throws ConstraintViolationException, StudentException;
	
	public List<stud_mongo> getAllStudent();
	
	public stud_mongo getStudById(String id) throws StudentException;
	
	public void deleteStudById(String id) throws StudentException;
}
