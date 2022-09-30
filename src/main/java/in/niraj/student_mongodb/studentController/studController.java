package in.niraj.student_mongodb.studentController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.niraj.student_mongodb.exception.StudentException;
import in.niraj.student_mongodb.model.stud_mongo;
import in.niraj.student_mongodb.repository.studentRepository;
import in.niraj.student_mongodb.service.studentService;

@RestController
public class studController {

	@Autowired
	private studentRepository sRepo;
	
	@Autowired
	private studentService sService;
	
	@GetMapping("/students")
	public ResponseEntity<?> getAllStudent()
	{
		List<stud_mongo> stud = sService.getAllStudent();
		if (!stud.isEmpty()) {
			return new ResponseEntity<List<stud_mongo>>(stud,HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<>("No student found", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/student")
	public ResponseEntity<?> saveStudent(@RequestBody stud_mongo stud)
	{
		try {
			sService.saveStudent(stud);
			return new ResponseEntity<stud_mongo>(stud,HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (StudentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<?> getStudent(@PathVariable("id") String id)
	{
		try {
			return new ResponseEntity<>(sService.getStudById(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND); 
		}
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<?> updateStudentById(@PathVariable("id") String id, @RequestBody stud_mongo stud)
	{
		Optional<stud_mongo> studFound = sRepo.findById(id);
		if (studFound.isPresent()) {
			stud_mongo studToEdit = studFound.get();
			studToEdit.setName(stud.getName() != null ? stud.getName(): studToEdit.getName());
			studToEdit.setStream(stud.getStream() != null ? stud.getStream(): studToEdit.getStream());
			studToEdit.setRollno(stud.getRollno() != null ? stud.getRollno(): studToEdit.getRollno());
			studToEdit.setEmail(stud.getEmail() != null ? stud.getEmail(): studToEdit.getEmail());
			studToEdit.setUpdatedAt(new Date(System.currentTimeMillis()));
			sRepo.save(studToEdit);
			return new ResponseEntity<>(studToEdit,HttpStatus.OK);			
		}
		else
		{
			return new ResponseEntity<>("Student not found with id "+id,HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<?> deleteStudentById(@PathVariable("id") String id)
	{
		String name = null;
		Optional<stud_mongo> studFound = sRepo.findById(id);
		if(studFound.isPresent())
		{
			stud_mongo stud = studFound.get();
			name = stud.getName();
		
		try {
		sService.deleteStudById(id);
		return new ResponseEntity<>("Student "+name+" successfully deleted",HttpStatus.OK);
		}
		catch(StudentException e)
		{
			return new ResponseEntity<>("Student not found with id "+id,HttpStatus.NOT_FOUND);
		}
		}
		else
		{
			return new ResponseEntity<>("Student not found with id "+id,HttpStatus.NOT_FOUND);
		}
	}
	
	
}
