package com.example.demo;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.group.Group;
import com.example.demo.student.Student;
import com.example.demo.student.StudentController;
import java.time.LocalDateTime;

@Controller
public class StudyGroupsController{
	
	private List<Group> groups = new ArrayList<Group>();
	private static long index =1;
	public static Student user = StudentController.user;
	
	// Show a form to create/update a study group
	@GetMapping("/studygroupsForm")
	public ModelAndView showStudyGroupsForm(@RequestParam(required = false) Long id) {
		
		String viewName = "studygroupsForm";
		Map<String, Object> model = new HashMap<String, Object>();
		
		// Form view to create a new group or update an existing group if the id of the group is found
		Group group = findGroupById(id);
		if (group == null) {
			model.put("group", new Group());
		}else {
			model.put("group", group);
		}
		
		return new ModelAndView(viewName, model);
	}
	
	
	private Group findGroupById(Long id) {
		
		for(Group group : groups) {
			if (group.getId().equals(id)) {
				return group;
			}
		}
		return null;
	}

	// Method to create or update a study group
	@PostMapping("/studygroupsForm")
	public ModelAndView submitStudyGroup(Group group) {
		
		// Create a new group or update it if the group already exist
		Group existingGroup = findGroupById(group.getId());
		if (existingGroup == null) {
			group.setId(index++);
			group.setCreator(user);
			groups.add(group);
		}else {
			existingGroup.setTopic(group.getTopic());
			existingGroup.setDescription(group.getDescription());
			existingGroup.setStartTime(group.getStartTime());
			existingGroup.setEndTime(group.getEndTime());
		}
				
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/studygroups");
		
		return new ModelAndView(redirectView);
		
	}
	
	// Show the list of study groups. The non required parameter is used to delete a group.
	@GetMapping("/studygroups")
	public ModelAndView getStudyGroups(@RequestParam(required = false) Long id) {
	
		String viewName = "studygroups";	
		Map<String, Object> model = new HashMap<String, Object>();
		
		// If the user delete a group, remove it from the list
		if (id != null) {
			Group group = findGroupById(id);
	        groups.remove(group);
		}
		model.put("groups", groups);
		model.put("user", user);
		model.put("numberOfGroups", groups.size());
		
		return new ModelAndView(viewName , model);
	}

	// Join a study group by adding the user to the list members of that group
	@GetMapping("/joinStudyGroup")
	public ModelAndView joinStudyGroup(@RequestParam(required = false) String idUser, @RequestParam(required = false) Long idGroup) {
		
		Group group = findGroupById(idGroup);
		Student student = StudentController.findStudentById(idUser);
		
		// Check if the student is not already a member of the group
	    if (!group.getMembers().contains(student)) {
	        group.addMember(student);
	        group.showMembers();
	    } else {
	        System.out.println("Student is already a member of the group");
	    }
	    	
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/studygroups");
		
		return new ModelAndView(redirectView);
	}
	
	// Load study groups
	@GetMapping("/")
	public ModelAndView loadStudyGroups() {
		
		groups.clear();
		groups.add(new Group(index++,"CS401","HW 1",new Student("Michael","Dupont","exemple@exemple.com","0100000000"),LocalDateTime.of(2024,12, 22, 8, 00),LocalDateTime.of(2021,12, 22, 10, 0)));
		groups.add(new Group(index++,"CS401","Lab 3",new Student("Lionel","Tyson","exemple@exemple.com","0100000000"),LocalDateTime.of(2024,01, 22, 20, 00),LocalDateTime.of(2024,01, 22, 21, 0)));
		groups.add(new Group(index++,"CS401","Discussion 8",new Student("Stephane","Gauthiez","exemple@exemple.com","0100000000"),LocalDateTime.of(2024,12, 02, 15, 00),LocalDateTime.of(2023,12, 02, 16, 30)));
				
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/welcome");
		
		return new ModelAndView(redirectView);
	}
}