package com.mkyong.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mkyong.entity.Proyecto;
import com.mkyong.entity.RoleStakeholder;
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
public class UsuarioController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private StakeholderService stakeholderService;
	
	@Autowired
	private TipoStakeholderService tipoStakeholderService;
	
	@Autowired
	private RolStakeholderService rolStakeholderService;
	
	@Autowired
	private RolStakeholderPKService rolStakeholderPKService;

	@GetMapping("/")
	public String index(Model mav) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Usuario user = userService.findUserByName(authentication.getName());
		
		Proyecto proyecto = new Proyecto();
		
		Stakeholder stakeholder = new Stakeholder();
		
		Proyecto proyectoentrabajo = (Proyecto) mav.asMap().get("proyectoentrabajo");
		
		if (mav.containsAttribute("paginaActual")){
			String paginaActual = (String) mav.asMap().get("paginaActual");		
			System.out.println("Pagina Actual: " + paginaActual);
		}
		
		List<TipoStakeholder> listaTipoStakeholders = tipoStakeholderService.findAll();
		
		System.out.println("Lista de tipo de stakeholders: " + listaTipoStakeholders);
	
		mav.addAttribute("listaTipoStakeholders", listaTipoStakeholders);
		
		mav.addAttribute("listaProyectos", proyectoService.findProyectoByusuarioCreacionId(user));
		mav.addAttribute("nuevoProyecto", proyecto);
		mav.addAttribute("usuarioRegistrado", user);
		mav.addAttribute("nuevoStakeholder", stakeholder);
		mav.addAttribute("proyectoentrabajo", proyectoentrabajo);
		
		if (proyectoentrabajo != null){
			
			
			List<Stakeholder> listaStakeholder = stakeholderService.findByidProyecto(proyectoentrabajo);
			
			System.out.println("Antes de entrar" + listaStakeholder.size());
			
			for (int i = 0 ; i < listaStakeholder.size();i++){
				
				ArrayList<String> listaTipoRolPasaje = new ArrayList<String>();
				List<RoleStakeholder> listRoleStakeholderPK = rolStakeholderPKService.findBystakeholder(listaStakeholder.get(i));
								
				for (int j = 0; j < listRoleStakeholderPK.size();j++){
					
					String idTipoStakeholder = Integer.toString(listRoleStakeholderPK.get(j).getTipoRoleStakeholder().getIdTipoStakeholder().getId());
					String idRolStakeholder = Integer.toString(listRoleStakeholderPK.get(j).getTipoRoleStakeholder().getId());
					String nombreTipoStakeholder = listRoleStakeholderPK.get(j).getTipoRoleStakeholder().getIdTipoStakeholder().getNombre();
					String nombreRolStakeholder = listRoleStakeholderPK.get(j).getTipoRoleStakeholder().getNombre();
					
					listaTipoRolPasaje.add(idTipoStakeholder);
					listaTipoRolPasaje.add(idRolStakeholder);
					listaTipoRolPasaje.add(nombreTipoStakeholder);
					listaTipoRolPasaje.add(nombreRolStakeholder);
				
				}
				
				listaStakeholder.get(i).setListaSHTipoRoles(listaTipoRolPasaje);
				
				
			}
			
			
			
			mav.addAttribute("listaStakeholder", listaStakeholder);
			
		}
		else
		{
			List<Stakeholder> stakeHolders = new ArrayList<Stakeholder>();
			mav.addAttribute("listaStakeholder", stakeHolders);
			
		}
		
		
		return "/index";
	}


	
	@RequestMapping(method=RequestMethod.GET, value="/login")
	public String login(Model mav) {
		Usuario user = new Usuario();
		mav.addAttribute("usuario", user);
		return "login";
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/registration")
	public String registration(Model mav) {
		Usuario user = new Usuario();
		mav.addAttribute("usuario", user);
		return "registration";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/registracioncorrecta")
	public String registrationCorrecta(Model mav) {
		
		Usuario user = new Usuario();
		mav.addAttribute("usuario", user);
		return "/login?error";
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/registration")
	public String registrarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult bindingResult, Model model,  RedirectAttributes redir){

		Usuario userExists = userService.findUserByName(usuario.getName());
		
		if (userExists != null) {
			System.out.println("Ya existe el usuario");
			bindingResult
					.rejectValue("name", "error.user",
							"Ya hay un usuario registrado con ese nombre");
		}
		
		if (!usuario.getPassword().equalsIgnoreCase(usuario.getRepeatPassword())){
			bindingResult.rejectValue("repeatPassword", "error.user", "Las contraseñas deben coincidir");
		}
		
		if (bindingResult.hasErrors()) {
			System.out.println("Tiene errores");
			return "registration";
			
		} else {
			userService.saveUser(usuario);
			
		}	
		
		
		System.out.println("Grabo usuario");
		redir.addFlashAttribute("success", "registrado");
		return "redirect:login";
	}
	
	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}


	
	
}
