package in.niraj.student_mongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import in.niraj.student_mongodb.model.stud_mongo;

@Repository
public interface studentRepository extends MongoRepository<stud_mongo, Long> {
	
	
	public Optional<stud_mongo> findById(String id);

	public Optional<stud_mongo> findByStream(String stream);
	
	public Optional<stud_mongo> findByRollno(Integer rollno);
	
	public void deleteById(String id);
	
	@Query("{'email':?0}")
	public Optional<stud_mongo> findByEmail(String email);
	
}
