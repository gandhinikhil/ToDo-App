package com.example.ToDoApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.ToDoApp.Service.ToDoService;
import com.example.ToDoApp.model.ToDo;



@RestController
public class ToDoController {

	@Autowired
	private ToDoService service;
	 
	@GetMapping({"/", "viewToDoList"})
	public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
		
		model.addAttribute("list", service.getAllToDoItems());
		model.addAttribute("message", message);
		
		return "ViewToDoList";
	}
	
	
	@GetMapping("/UpdateToDoStatus/{id}")
	public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		
		if (service.updatestatus(id)) {
		
			redirectAttributes.addFlashAttribute("message", "Update Success");
			return "redirect:/viewToDoList";
	
		}
		else {
		redirectAttributes.addFlashAttribute("message", "Update Failure");
		return "redirect:/viewToDoList";
		}
		
	}
	
	@GetMapping("/AddToDoItem")
	public String addToDoItem(Model model) {
		
		model.addAttribute("todo", new ToDo());
		
		return "AddToDoItem";
	}
	
	@PostMapping("/saveToDoItem")
	public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
		
		if(service.saveOrUpdateToDoItem(todo)) {
			
			redirectAttributes.addFlashAttribute("message", "Save Success");
			
			
			return "redirect:/viewToDoList";
		}
		else {
		
			redirectAttributes.addFlashAttribute("message", "Save Failure");
		
			return "redirect:/addToDoItem";
		
		}
		}
	
	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
		
		if(service.saveOrUpdateToDoItem(todo)) {
		
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewToDoList";
	
		}
		else {
		
			redirectAttributes.addFlashAttribute("message", "Edit Failure");
		
			return "redirect:/editToDoItem/" + todo.getId();
		}
		
		}
	
	@GetMapping("/deleteToDoItem/{id}")
	public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		
		if (service.deleteToDoItem(id)) {
		
			redirectAttributes.addFlashAttribute("message", "Delete Success");
			return "redirect:/viewToDoList";
		
		}
		else {
			
		
		redirectAttributes.addFlashAttribute("message", "Delete Failure");
		
		return "redirect:/viewToDoList";
		
		}
		}
}
