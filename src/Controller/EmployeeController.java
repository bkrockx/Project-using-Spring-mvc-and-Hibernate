package Controller;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import Dto.*;
import Model.*;
import Service.*;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Resource(name="employeeService")
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRecords(Model model) {
    	
    	List<Employee> employees = employeeService.getAll();
    	
    	List<EmployeeDTO> employeeDTO = new ArrayList<EmployeeDTO>();
    	
    	for (Employee employee: employees) {
    		EmployeeDTO dto = new EmployeeDTO();
    		
			dto.setEmployeeId(employee.getEmployeeId());
			dto.setEmployeeName(employee.getEmployeeName());
			dto.setDepartment(employee.getDepartment());
			dto.setDesignation(employee.getDesignation());
			
			employeeDTO.add(dto);
			
    	}
   
    	model.addAttribute("employees", employeeDTO);
		
    	return "EmployeeRecord";
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String getAdd(@RequestParam("id")Integer projectId,Model model){
		
		Employee emp = new Employee();
		
		
		
		List<Employee> employees = employeeService.getAll();
    	
    	List<EmployeeDTO> employeeDTO = new ArrayList<EmployeeDTO>();
    	
    	for (Employee employee: employees) {
    		EmployeeDTO dto = new EmployeeDTO();
    		
			dto.setEmployeeId(employee.getEmployeeId());
			dto.setEmployeeName(employee.getEmployeeName());
			dto.setDepartment(employee.getDepartment());
			dto.setDesignation(employee.getDesignation());
			
			employeeDTO.add(dto);
			
    	}
   
    	model.addAttribute("employees", employeeDTO);
    	model.addAttribute("projectId",projectId);
		model.addAttribute("employeeAttribute",emp);
		
		return "addEmployee";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String postAdd(@RequestParam("id")Integer projectId,@ModelAttribute("employeeAttribute")Employee employee) throws IOException{
		
		Employee emp = employeeService.getEmployeeByName(employee.getEmployeeName());
		if(emp==null){
			return "Error";
		}
		else{
			employeeService.add(projectId,emp);
			return "redirect:/project3/list";
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer employeeId) {
		
		employeeService.delete(employeeId);
		return "redirect:/project3/list";
	}
   
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("bid") Integer projectId,@RequestParam("cid") Integer employeeId, Model model) {
    	
    	Employee emp2 = employeeService.get(employeeId);

    	model.addAttribute("projectId",projectId);
    	model.addAttribute("employeeAttribute",emp2);

    	return "editEmployee";
	}
 
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer employeeId,
    		@ModelAttribute("employeeAttribute") Employee employee) {
		
		employee.setEmployeeId(employeeId);
		employeeService.edit(employee);

		return "redirect:/project3/list";
	}
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @RequestMapping(value="/addEmp",method = RequestMethod.GET)
	public String getAddEmp(Model model){
		
		Employee emp = new Employee();
		model.addAttribute("employeeAttribute",emp);
		
		return "addEmp";
	}
	
	@RequestMapping(value="/addEmp", method = RequestMethod.POST)
	public String postAddEemp(@ModelAttribute("employeeAttribute")Employee employee) throws IOException{
		
		employeeService.addEmp(employee);
		return "redirect:/employee/list";
		
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam("id") Integer employeeId) {
		
		employeeService.delete(employeeId);
		return "redirect:/employee/list";
	}
   
    
    @RequestMapping(value = "/editEmp", method = RequestMethod.GET)
    public String getEditEmp(@RequestParam("id") Integer employeeId, Model model) {
    	
    	Employee emp2 = employeeService.get(employeeId);
    	model.addAttribute("employeeAttribute",emp2);

    	return "editEmp";
	}
 
    @RequestMapping(value = "/editEmp", method = RequestMethod.POST)
    public String postEditEmp(@RequestParam("id") Integer employeeId,
    		@ModelAttribute("employeeAttribute") Employee employee) {
		
		employee.setEmployeeId(employeeId);
		employeeService.editEmp(employee);

		return "redirect:/employee/list";
	}
    
    @RequestMapping(value = "/deleteEmp", method = RequestMethod.GET)
    public String deleteEmp(@RequestParam("id") Integer employeeId) {
		
		employeeService.delete(employeeId);
		return "redirect:/project3/list";
	}
}


