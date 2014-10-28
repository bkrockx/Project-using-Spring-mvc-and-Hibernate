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
@RequestMapping("/client")

public class ClientController {
	
	@Resource(name="clientService")
	private ClientService clientService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRecords(Model model) {
    	
    	List<Client> clients = clientService.getAll();
    	
    	List<ClientDTO> clientDTO = new ArrayList<ClientDTO>();
    	
    	for (Client client: clients) {
    		ClientDTO dto = new ClientDTO();
    		
			dto.setClientId(client.getClientId());
			dto.setClientName(client.getClientName());
			dto.setClientAddress(client.getClientAddress());
			
			clientDTO.add(dto);
			
    	}
   
    	model.addAttribute("clients", clientDTO);
		
    	return "ClientRecord";
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String getAdd(@RequestParam("id")Integer projectId,Model model){
		
		Client client = new Client();
		
		List<Client> clients = clientService.getAll();
    	
    	List<ClientDTO> clientDTO = new ArrayList<ClientDTO>();
    	
    	for (Client client1: clients) {
    		ClientDTO dto = new ClientDTO();
    		
			dto.setClientId(client1.getClientId());
			dto.setClientName(client1.getClientName());
			dto.setClientAddress(client1.getClientAddress());
			
			clientDTO.add(dto);
			
    	}
   
    	model.addAttribute("clients", clientDTO);
		model.addAttribute("projectId",projectId);
		model.addAttribute("clientAttribute",client);
		
		return "addClient";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String postAdd(@RequestParam("id")Integer projectId,@ModelAttribute("clientAttribute")Client client) throws IOException{
		
		Client client1 = clientService.getClientByName(client.getClientName());
		if(client1==null){
			return "Error";
		}
		else{
			clientService.add(projectId,client1);
			return "redirect:/project3/list";
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer clientId) {
		
		clientService.delete(clientId);
		return "redirect:/project3/list";
	}
   
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("bid") Integer projectId,@RequestParam("cid") Integer clientId, Model model) {
    	
    	Client client = clientService.get(clientId);

    	model.addAttribute("projectId",projectId);
    	model.addAttribute("clientAttribute",client);

    	return "editClient";
	}
 
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer clientId,
    		@ModelAttribute("employeeAttribute") Client client) {
		
		client.setClientId(clientId);
		clientService.edit(client);

		return "redirect:/project3/list";
	}
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value="/addCnt",method = RequestMethod.GET)
	public String getAddCnt(Model model){
		
		Client client = new Client();
		model.addAttribute("clientAttribute",client);
		
		return "addCnt";
	}
	
	@RequestMapping(value="/addCnt", method = RequestMethod.POST)
	public String postAddCnt(@ModelAttribute("clientAttribute")Client client) throws IOException{
		
		clientService.addClient(client);
		return "redirect:/client/list";
		
	}
	
	@RequestMapping(value = "/deleteCnt", method = RequestMethod.GET)
    public String getDeleteClient(@RequestParam("id") Integer clientId) {
		
		clientService.delete(clientId);
		return "redirect:/client/list";
	}
   
    
    @RequestMapping(value = "/editCnt", method = RequestMethod.GET)
    public String getEditClient(@RequestParam("id") Integer clientId, Model model) {
    	
    	Client client2 = clientService.get(clientId);
    	model.addAttribute("clientAttribute",client2);

    	return "editCnt";
	}
 
    @RequestMapping(value = "/editCnt", method = RequestMethod.POST)
    public String postEditEmp(@RequestParam("id") Integer clientId,
    		@ModelAttribute("clientAttribute") Client client) {
		
		client.setClientId(clientId);
		clientService.editClient(client);

		return "redirect:/client/list";
	}
    
}



