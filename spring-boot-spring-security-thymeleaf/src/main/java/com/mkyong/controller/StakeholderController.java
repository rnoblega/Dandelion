package com.mkyong.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mkyong.entity.Proyecto;
import com.mkyong.entity.RoleStakeholder;
import com.mkyong.entity.RoleStakeholderPK;
import com.mkyong.entity.Stakeholder;
import com.mkyong.entity.TipoRoleStakeholder;
import com.mkyong.entity.TipoStakeholder;
import com.mkyong.entity.Usuario;
import com.mkyong.service.ProyectoService;
import com.mkyong.service.RolStakeholderPKService;
import com.mkyong.service.RolStakeholderService;
import com.mkyong.service.StakeholderService;
import com.mkyong.service.TipoStakeholderService;
import com.mkyong.service.UsuarioService;

@Controller
public class StakeholderController {
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private StakeholderService stakeholderService;
	
	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private RolStakeholderService rolStakeholderService;
	
	@Autowired
	private TipoStakeholderService tipoStakeholderService;
	
	@Autowired
	private RolStakeholderPKService rolStakeholderPKService;
	
	
	
	@RequestMapping(method=RequestMethod.POST, value="/crearstakeholder/{idProyecto}")
	public String registrarStakeholder(@PathVariable("idProyecto") int idProyecto, @Valid @ModelAttribute Stakeholder stakeholder, BindingResult bindingResult, Model model,  RedirectAttributes redir){
		
//		Lee la lista de tipo y rol stakeholder que llega desde javascript. Busca el rol y tipo por separados y los relaciona entre si
		List<TipoRoleStakeholder> listaRolesStakeholder = new ArrayList<TipoRoleStakeholder>();
		System.out.println("stakeholder id: " + stakeholder.getId());
		System.out.println("stakeholder id: " + stakeholder.getDescripcion());
		
		System.out.println(stakeholder.getListaSHTipoRoles().toString());
		
		for (int i=0; i < stakeholder.getListaSHTipoRoles().size(); i=i+2){
			
			if (!stakeholder.getListaSHTipoRoles().get(i).contains("N")){
				
				TipoStakeholder tipoStakeholder = tipoStakeholderService.findByid(Integer.parseInt(stakeholder.getListaSHTipoRoles().get(i)));
				TipoRoleStakeholder rolStakeholder = rolStakeholderService.findByid(Integer.parseInt(stakeholder.getListaSHTipoRoles().get(i+1)));
				
				System.out.println("Tipo: " + tipoStakeholder.getNombre());
				System.out.println("Rol: " + rolStakeholder.getNombre());
				
				rolStakeholder.setIdTipoStakeholder(tipoStakeholder);
				
				listaRolesStakeholder.add(rolStakeholder);
			}
			
			
		}
		
				
//		 Busca usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = userService.findUserByName(authentication.getName());
		
//		Busca el proyecto en que se esta trabajando y se lo asigna al stakeholder
		Proyecto proyecto = proyectoService.findByid(idProyecto);
		stakeholder.setIdProyecto(proyecto);
		
//	 	Asigna la lista de roles al stakeholder
		System.out.println("Prueba cuando asigna la lista de roles a un usuario");
		for (int i = 0; i < listaRolesStakeholder.size();i++){
			System.out.println("ID TIPO: " + listaRolesStakeholder.get(i).getIdTipoStakeholder().getId());
			System.out.println("ID ROL : " + listaRolesStakeholder.get(i).getId());
		}
		stakeholder.setTipoRoleStakeholderList(listaRolesStakeholder);
		
//		Graba el stakeholder con todos los datos
		stakeholderService.saveStakeholder(stakeholder);
		
		
//		Por cada rol crea una clase RoleStakeholder que contiene el rol y el id del usuario
		for (int i = 0; i < listaRolesStakeholder.size(); i++){
//			Crea clase RoleStakeholder
		
			RoleStakeholder rolStakeholderPK = new RoleStakeholder();
			RoleStakeholderPK claveRolStakeholderPK =  new RoleStakeholderPK();
		
			rolStakeholderPK.setStakeholder(stakeholder);
		
			rolStakeholderPK.setTipoRoleStakeholder(listaRolesStakeholder.get(i));
	
			claveRolStakeholderPK.setIdStakeholder(stakeholder.getId());
		
			claveRolStakeholderPK.setIdTipoRoleStakeholder(listaRolesStakeholder.get(i).getId());
			
			rolStakeholderPK.setRoleStakeholderPK(claveRolStakeholderPK);
		
			rolStakeholderPKService.saveRoleStakeholder(rolStakeholderPK);
			
			
		}
		 
		redir.addFlashAttribute("user", user);
		redir.addFlashAttribute("proyectoentrabajo", proyecto);
		redir.addFlashAttribute("paginaActual", "stakeholder");
		
		return "redirect:/";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/eliminarstakeholder/{id}")
	public String eliminarStakeholder( @PathVariable("id") int id, Model model,  RedirectAttributes redir){
		
		Stakeholder stakeholder = stakeholderService.findByid(id);	
		
		Proyecto proyecto = stakeholder.getIdProyecto();
		
		List<RoleStakeholder> listRoleStakeholder = rolStakeholderPKService.findBystakeholder(stakeholder);
		
		for (int i=0; i < listRoleStakeholder.size(); i++){
			RoleStakeholder roleStakeholder = new RoleStakeholder();
			roleStakeholder = listRoleStakeholder.get(i);
			rolStakeholderPKService.delete(roleStakeholder);
		}
		
		stakeholderService.delete(stakeholder);
				
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = userService.findUserByName(authentication.getName());
		
		redir.addFlashAttribute("user", user);
		redir.addFlashAttribute("proyectoentrabajo", proyecto);
		redir.addFlashAttribute("paginaActual", "stakeholder");
		return "redirect:/";
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/modificarstakeholder/{idProyecto}")
	public String modificarStakeholder(@PathVariable("idProyecto") int idProyecto, @Valid @ModelAttribute Stakeholder stakeholder, BindingResult bindingResult, Model model,  RedirectAttributes redir){
		
//		Lee la lista de tipo y rol stakeholder que llega desde javascript. Busca el rol y tipo por separados y los relaciona entre si
		List<TipoRoleStakeholder> listaRolesStakeholder = new ArrayList<TipoRoleStakeholder>();
		System.out.println("stakeholder id: " + stakeholder.getId());
		System.out.println("stakeholder id: " + stakeholder.getDescripcion());
		
		System.out.println(stakeholder.getListaSHTipoRoles().toString());
		
		for (int i=0; i < stakeholder.getListaSHTipoRoles().size(); i=i+2){
			
			if (!stakeholder.getListaSHTipoRoles().get(i).contains("N")){
			
				TipoStakeholder tipoStakeholder = tipoStakeholderService.findByid(Integer.parseInt(stakeholder.getListaSHTipoRoles().get(i)));
			
				TipoRoleStakeholder rolStakeholder = rolStakeholderService.findByid(Integer.parseInt(stakeholder.getListaSHTipoRoles().get(i+1)));
			
				System.out.println("Tipo: " + tipoStakeholder.getNombre());
				System.out.println("Rol: " + rolStakeholder.getNombre());
			
				rolStakeholder.setIdTipoStakeholder(tipoStakeholder);
			
				listaRolesStakeholder.add(rolStakeholder);
			
			}
			
			
		}
		System.out.println("SALE");
				
//		 Busca usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = userService.findUserByName(authentication.getName());
		
//		Busca el proyecto en que se esta trabajando y se lo asigna al stakeholder
		Proyecto proyecto = proyectoService.findByid(idProyecto);
		stakeholder.setIdProyecto(proyecto);
		
//	 	Asigna la lista de roles al stakeholder
		System.out.println("Prueba cuando asigna la lista de roles a un usuario");
		for (int i = 0; i < listaRolesStakeholder.size();i++){
			System.out.println("ID TIPO: " + listaRolesStakeholder.get(i).getIdTipoStakeholder().getId());
			System.out.println("ID ROL : " + listaRolesStakeholder.get(i).getId());
		}
		stakeholder.setTipoRoleStakeholderList(listaRolesStakeholder);
		
//		Graba el stakeholder con todos los datos
		stakeholderService.saveStakeholder(stakeholder);
		
//		Borra todos los RoleStakholder para agregarlos nuevamente
		rolStakeholderPKService.deleteBystakeholder(stakeholder);
		
//		Por cada rol crea una clase RoleStakeholder que contiene el rol y el id del usuario
		for (int i = 0; i < listaRolesStakeholder.size(); i++){
//			Crea clase RoleStakeholder
		
			RoleStakeholder rolStakeholderPK = new RoleStakeholder();
			RoleStakeholderPK claveRolStakeholderPK =  new RoleStakeholderPK();
		
			rolStakeholderPK.setStakeholder(stakeholder);
		
			rolStakeholderPK.setTipoRoleStakeholder(listaRolesStakeholder.get(i));
	
			claveRolStakeholderPK.setIdStakeholder(stakeholder.getId());
		
			claveRolStakeholderPK.setIdTipoRoleStakeholder(listaRolesStakeholder.get(i).getId());
			
			rolStakeholderPK.setRoleStakeholderPK(claveRolStakeholderPK);
		
			rolStakeholderPKService.saveRoleStakeholder(rolStakeholderPK);
			
			
		}
		 
		redir.addFlashAttribute("user", user);
		redir.addFlashAttribute("proyectoentrabajo", proyecto);
		redir.addFlashAttribute("paginaActual", "stakeholder");
		
		return "redirect:/";
	}
	
	
	
	
}
