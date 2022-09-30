package in.niraj.student_mongodb.exception;

public class StudentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentException(String message) {
		super(message);
	}
	
	public static String EmailExists(String email)
	{
		return email+" already exists for a student.";
	}
	
	public static String RollNoExists(Integer rollno ,String stream)
	{
		return rollno+" already exists for a student in "+stream+".";
	}
	
	public static String NotFoundException(String id)
	{
		return "Student with "+id+" not found";
	}
}
