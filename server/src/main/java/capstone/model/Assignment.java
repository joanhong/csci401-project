package capstone.model;

import java.time.*;

import org.springframework.data.annotation.Id;

public class Assignment {
	@Id
	long id;
	LocalDateTime dueDate;
	LocalDateTime submitDateTime;
}
