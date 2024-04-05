package com.example.demo.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.group.Group;

@RestController
public class StudentController {
	
	private static List<Student> students = new ArrayList<Student>();
	private static long index =1;
	public static Student user = new Student();
	
	// Page to sing in or log in
	@GetMapping("/welcome")
	public ModelAndView welcome() {
		String viewName = "welcome";
		Map<String, Object> model = new HashMap<String, Object>();
		
		return new ModelAndView(viewName, model);
	}
	
	@GetMapping("/signin")
	public ModelAndView signinForm(@RequestParam(required = false) String id) {
		
		String viewName = "signin";
		Map<String, Object> model = new HashMap<String, Object>();
		
		// Form view to login
		Student student = findStudentById(id);
		if (student == null) {
			model.put("student", new Student());
		}else {
			model.put("student", student);
		}

		return new ModelAndView(viewName, model);
	}
	

	@PostMapping("/signin")
	public ModelAndView submitSignin(Student student) {
		
		// The user becomes the student that signed in
		user.copy(student);
		
		// Create a new student or update it if the student already exist
		Student existingStudent = findStudentById(student.getId());
		if (existingStudent == null) {
			students.add(student);
		}else {
			existingStudent.setFname(student.getFname());
			existingStudent.setLname(student.getLname());
			existingStudent.setEmail(student.getEmail());
			existingStudent.setPhone(student.getPhone());
		}
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/studygroups");
		
		return new ModelAndView(redirectView);
	}
	
	@GetMapping("/login")
	public ModelAndView loginForm(@RequestParam(required = false) String id) {
		
		String viewName = "login";
		Map<String, Object> model = new HashMap<String, Object>();
		
		// Form view to login
		Student student = findStudentById(id);
		if (student == null) {
			model.put("student", new Student());
		}else {
			model.put("student", student);
		}
		
		return new ModelAndView(viewName, model);
	}
	

	@PostMapping("/login")
	public ModelAndView submitLogin(Student student) {
		
		RedirectView redirectView = new RedirectView();
		
		Student existingStudent = findStudentById(student.getId());
		if (existingStudent == null) {
			redirectView.setUrl("/login");
		}else if (existingStudent.getPassword().equals(student.getPassword())) {
			redirectView.setUrl("/studygroups");
		}else {
			redirectView.setUrl("/login");
		}
		
		return new ModelAndView(redirectView);
	}
	
	public static Student findStudentById(String id) {
		
		for(Student student : students) {
			if (student.getId().equals(id)) {
				return student;
			}
		}
		return null;
	}
}
