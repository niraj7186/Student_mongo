package in.niraj.student_mongodb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.niraj.student_mongodb.exception.StudentException;
import in.niraj.student_mongodb.model.stud_mongo;
import in.niraj.student_mongodb.repository.studentRepository;

@Service
public class StudentServiceImpl implements studentService {

	@Autowired
	private studentRepository sRepo;
	
	@Override
	public void saveStudent(stud_mongo stud) throws ConstraintViolationException, StudentException {
			Optional<stud_mongo> foundEmail = sRepo.findByEmail(stud.getEmail());
			Optional<stud_mongo> foundStream = sRepo.findByStream(stud.getStream());
			Optional<stud_mongo> foundRollno = sRepo.findByRollno(stud.getRollno());
			if(foundStream.isPresent() && foundRollno.isPresent())
			{
				throw new StudentException(StudentException.RollNoExists(stud.getRollno(), stud.getStream()));
			}
			if (foundEmail.isPresent()) {
				throw new StudentException(StudentException.EmailExists(stud.getEmail()));
			}
			else {
				stud.setCreatedAt(new Date(System.currentTimeMillis()));
				sRepo.save(stud);
			}
		}

	@Override
	public List<stud_mongo> getAllStudent() {
		List<stud_mongo> stud = sRepo.findAll();
		if (!stud.isEmpty()) {
			return stud;
		}
		else 
		{
			return new ArrayList<stud_mongo>();
		}
	}

	@Override
	public stud_mongo getStudById(String id) throws StudentException {
		Optional<stud_mongo> found = sRepo.findById(id);
		if(!found.isPresent())
		{
			throw new StudentException(StudentException.NotFoundException(id));
		}
		else
		{
			return found.get();
		}
	}

	@Override
	public void deleteStudById(String id) throws StudentException {
		Optional<stud_mongo> found = sRepo.findById(id);
		if (!found.isPresent()) {
			throw new StudentException(StudentException.NotFoundException(id));
		}
		else
		{
			sRepo.deleteById(id);
		}
	}

	
}
