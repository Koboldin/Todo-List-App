package online.neveling.webapp.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITodoRepository extends JpaRepository<Todo, Integer>{
	public List<Todo> findByUsername(String username);

}
