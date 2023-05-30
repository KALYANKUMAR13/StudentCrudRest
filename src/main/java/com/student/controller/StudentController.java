package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Student;
import com.student.repo.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {

	private StudentRepository studentRepository = null;

	@Autowired
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@GetMapping
	public List<Student> getAllid() {
		return studentRepository.findAll();
	}
	
   
	/* @RequestMapping(value = "/student",method= RequestMethod.GET)
		public String getAllid(Model model) {
		 model.addAttribute("listStudent",studentRepository.findAll());
			return "student";
		}*/
	
	  /*   @GetMapping("/showNewEmployeeForm")
   public String showNewEmployeeForm(Model model, Student student) {
       // create model attribute to bind form data
       
       studentRepository.save(student);
       model.addAttribute("student", student);
       return "new_employee";
   }*/

	// Saving or creating
	
	@RequestMapping(value = "/", method= RequestMethod.POST)
	public Student createUser(@RequestBody Student student) {
		return studentRepository.save(student);
	}

  /* @RequestMapping(value = "/saveStudent",method= RequestMethod.POST)
   public String saveEmployee(@ModelAttribute("student") Student student) {
       // save employee to database
       studentRepository.save(student);
       return "redirect:/";
   }*/

	// Find by id and display that
	//@GetMapping("/{id}")
   @RequestMapping(value = "/{id}",method= RequestMethod.GET)
	public Student getUserById(@PathVariable Integer id) {
		return studentRepository.findById(id).orElseThrow();
	}

	//@DeleteMapping("/{id}")
   @RequestMapping(value = "/{id}",method= RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer id) {
		studentRepository.deleteById(id);
	}
	
	//@PutMapping("/{id}")
  @RequestMapping(value = "/{id}",method= RequestMethod.PUT)
    public Student updateUser(@PathVariable Integer id, @RequestBody Student student) {
        Student existingUser = studentRepository.findById(id)
                .orElseThrow();
        existingUser.setFirstName(student.getFirstName());
        existingUser.setLastName(student.getLastName());
        return studentRepository.save(existingUser);
    }
    /* @RequestMapping(value = "/showFormForUpdate/{id}",method= RequestMethod.PUT)
   public String updateUser(@PathVariable(value = "id") Integer id, Model model) {
       Student existingUser = studentRepository.findById(id)
               .orElseThrow();
       model.addAttribute("student", existingUser);
      
       return "updateStudent";

   }*/
}
