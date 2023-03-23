package online.neveling.webapp.todo;

import java.time.LocalDate;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {

	private TodoService todoService;

	public TodoController(TodoService todoService, ITodoRepository todoRepository) {
		super();
		this.todoService = todoService;
	}

	@RequestMapping("list-todos")
	public String gotoTodoPage(ModelMap model) {
		String username = getLoggedInUsername(model);
		List<Todo> todos = todoService.findByUsername(username);
		model.addAttribute("todos", todos);
		return "list-todos";
	}

	//GET, POST Todos
	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedInUsername(model);
		LocalDate targetDate = (LocalDate)model.get("targetDate");
		Todo todo = new Todo(0, username , "", targetDate, false);
		model.put("todo", todo);
		return "manage-todo";
	}

	
	// Add Todo logic
	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		// In case of validation error (description field needs at least 5 characters) return to manage-todo instead of fallback page
		if (result.hasErrors()) {
			return "manage-todo";
		}
		
		String username = getLoggedInUsername(model);
		todoService.addTodo(username, todo.getDescription(),todo.getTargetDate(), false);
		return "redirect:list-todos";
	}
	
	//Delete Todo logic
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		//Delete selected Todo
		todoService.deleteTodoById(id);
		return "redirect:list-todos";
	}
	
	// Update Todo logic
	
	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		String username = getLoggedInUsername(model);
		LocalDate targetDate = (LocalDate)model.get("targetDate");
		String description = (String)model.get("description");
		Todo todo = new Todo(0, username , description, targetDate, false);
		model.put("todo", todo);
		return "manage-todo";
	}
	
	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		// In case of validation error (description field needs at least 5 characters) return to manage-todo instead of fallback page
		if (result.hasErrors()) {
			return "manage-todo";
		}
		
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
