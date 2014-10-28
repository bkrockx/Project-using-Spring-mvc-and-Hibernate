package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Model.*;
import Dto.*;
import Service.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/project3")
public class ProjectController {
	
	@Resource(name="projectService")
	private ProjectService projectService;
	
	@Resource(name="employeeService")
	private EmployeeService employeeService;
	
	@Resource(name="clientService")
	private ClientService clientService;
	
	@RequestMapping(value="/login")
	public String GetFront(Model model){
		return "Login";
	}
	
	@RequestMapping(value="/authenticate",method = RequestMethod.POST)
    public String authenticate(@RequestParam("uname")String employeeName,@RequestParam("pass")String password,
    		HttpSession session,HttpServletRequest request,Model model){
		
		Employee emp = employeeService.getEmployeeByName(employeeName);
		if(emp==null){
    		return "Error";
    	}
    	else{
    		boolean success = employeeService.validate(employeeName,password);
    	
	    	String designation = null;
	    	if(emp.getDesignation().equalsIgnoreCase("manager")){
	    		designation = emp.getDesignation();
	    	}
	    	
	    	if(success){
	    		session = request.getSession();
	    		session.setAttribute("employees",employeeName);
	    		session.setAttribute("designation",designation);
	    		if(emp.getDesignation().equalsIgnoreCase("manager")){
	    			return "index";
	    		}
	    		else{
	    			return "redirect:/project3/userDisplay";
	    		}
	    	}
	    	else{
	    		return "Error";
	    	}
    	}
    
    }
    
	@RequestMapping(value="/Logout")
	public String Logout(HttpSession session,HttpServletRequest request,Model model){
		HttpSession session2 = request.getSession(false);
		session2.removeAttribute("employees");
		session2.removeAttribute("designation");
		if(session2!=null){
			session2.invalidate();
		}
		return "Logout";
	}
	
	
    
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRecords(Model model) {
    	
    	List<Project> projects = projectService.getAll();
    	
    	List<ProjectDTO> projectDTO = new ArrayList<ProjectDTO>();
    	
    	//List<Client> clients =
    	
    	for (Project project: projects) {
    		ProjectDTO dto = new ProjectDTO();
    		
			dto.setProjectId(project.getProjectId());
			dto.setProjectName(project.getProjectName());
			dto.setDescription(project.getDescription());
			dto.setManager(project.getManager());
			dto.setFte(project.getFte());
			
			dto.setEmployee(employeeService.getAll(project.getProjectId()));
			dto.setClient(clientService.getAll(project.getProjectId()));
			
			projectDTO.add(dto);
			
    	}
   
    	model.addAttribute("projects", projectDTO);
		
    	return "ProjectRecord";
	}
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
    	
    	Project project = new Project();
    	model.addAttribute("projectAttribute",project);
    	
    	return "addProject";
	}
 
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@ModelAttribute("projectAttribute") Project project) {
		
    	projectService.add(project);
		return "redirect:/project3/list";
	}
    
 
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer projectId) {
    	
		projectService.delete(projectId);
		return "redirect:/project3/list";
	}
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("id") Integer projectId, Model model) {
    	
    	Project project1 = projectService.get(projectId);
    	model.addAttribute("projectAttribute",project1);
    	
    	return "editProject";
	}
 
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer projectId, 
    						    @ModelAttribute("projectAttribute") Project project) {
		
		//project.setProjectId(projectId);
		projectService.edit(project,projectId);
		return "redirect:/project3/list";
	}
    
    @RequestMapping(value="/userDisplay")
    public String userShow(Model model){
    	List<Project> projects = projectService.getAll();
    	
    	List<ProjectDTO> projectDTO = new ArrayList<ProjectDTO>();
    	
    	
    	for (Project project: projects) {
    		ProjectDTO dto = new ProjectDTO();
    		
			dto.setProjectId(project.getProjectId());
			dto.setProjectName(project.getProjectName());
			dto.setDescription(project.getDescription());
			dto.setManager(project.getManager());
			dto.setFte(project.getFte());
			
			dto.setEmployee(employeeService.getAll(project.getProjectId()));
			dto.setClient(clientService.getAll(project.getProjectId()));
			
			projectDTO.add(dto);
    	}
    	
    	model.addAttribute("projects", projectDTO);
    	
    	return "userDisplay";
    }
    
}


