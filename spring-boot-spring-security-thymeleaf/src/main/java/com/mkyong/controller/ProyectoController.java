package com.mkyong.controller;


import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mkyong.entity.Proyecto;
import com.mkyong.entity.Usuario;
import com.mkyong.service.ProyectoService;
import com.mkyong.service.RolStakeholderPKService;
import com.mkyong.service.UsuarioService;

@Controller
public class ProyectoController {

	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private RolStakeholderPKService rolStakeholderPKService;
		
	@RequestMapping(method=RequestMethod.POST, value="/crearproyecto")
	public String registrarProyecto(@Valid @ModelAttribute Proyecto proyecto, BindingResult bindingResult, Model model,  RedirectAttributes redir){
				
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = userService.findUserByName(authentication.getName());
		proyecto.setUsuariocreacionId(user);
		
		Date fecha = new Date();
		proyecto.setFechaCreacion(fecha);

		proyectoService.saveProyecto(proyecto);					
		
		redir.addFlashAttribute("user", user);
		return "redirect:/";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/eliminarproyecto/{id}")
	public String eliminarProyecto( @PathVariable("id") int id, Model model,  RedirectAttributes redir){
		
		Proyecto proy = proyectoService.findByid(id);		
		
//		Borra los stakehodler de RolStakeholderPK
		for (int i = 0; i < proy.getStakeholderList().size();i++){
			rolStakeholderPKService.deleteBystakeholder(proy.getStakeholderList().get(i));
		}
				
		proyectoService.delete(proy);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = userService.findUserByName(authentication.getName());
		
		redir.addFlashAttribute("user", user);
		return "redirect:/";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/updateproyecto")
	public String updateProyecto(@Valid @ModelAttribute Proyecto proyecto,BindingResult bindingResult, Model model,  RedirectAttributes redir){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = userService.findUserByName(authentication.getName());
		proyecto.setUsuariocreacionId(user);
		
		Date fecha = new Date();
		proyecto.setFechaCreacion(fecha);
		
		proyectoService.saveProyecto(proyecto);					
		
		redir.addFlashAttribute("user", user);
		return "redirect:/";
	}

	@RequestMapping(method=RequestMethod.POST, value="/trabajarproyecto/{id}")
	public String trabajarProyecto( @PathVariable("id") int id, Model model,  RedirectAttributes redir){
		
		Proyecto proy = proyectoService.findByid(id);		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = userService.findUserByName(authentication.getName());		
		
		System.out.println("Proyecto proyecto controller id: " + proy.getId());
		System.out.println("Descripcion: " + proy.getDescripcion());
		redir.addFlashAttribute("user", user);
		redir.addFlashAttribute("proyectoentrabajo", proy);
		redir.addFlashAttribute("paginaActual", "stakeholder");
		return "redirect:/";
	}
	
}
