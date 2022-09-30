package in.niraj.student_mongodb.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "stud_doc")

public class stud_mongo {

	@Id
	private String id;
	
	@NotNull(message = "Student Name can not be null.")
	private String name;
	
	@NotNull(message = "Student's Stream can not be null.")
	private String stream;
	
	private Integer rollno;
	
	@NotNull(message = "Student's Email can not be null.")
	private String email;
	
	private Date createdAt;
	
	private Date updatedAt;
}
