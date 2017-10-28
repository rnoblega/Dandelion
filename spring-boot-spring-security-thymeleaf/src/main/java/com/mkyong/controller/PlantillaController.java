package com.mkyong.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mkyong.entity.Encuesta;
import com.mkyong.entity.Plantilla;
import com.mkyong.entity.PreguntaEncuesta;
import com.mkyong.entity.Proyecto;
import com.mkyong.entity.Stakeholder;
import com.mkyong.entity.Tipornf;
import com.mkyong.entity.Usuario;
import com.mkyong.service.PlantillaService;
import com.mkyong.service.ProyectoService;
import com.mkyong.service.StakeholderService;
import com.mkyong.service.TipornfService;
import com.mkyong.service.UsuarioService;

@Controller
public class PlantillaController {
	
	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private TipornfService tipornfService;
	
	@Autowired 
	private StakeholderService stakeholderService;
	
	@Autowired 
	private PlantillaService plantillaService;
	
	@RequestMapping(method=RequestMethod.POST, value="/crearplantillacalidad/{idProyecto}")
	public String crearPlantillaCalidad(@PathVariable("idProyecto") int idProyecto, @Valid @ModelAttribute Plantilla plantillaFE, BindingResult bindingResult, Model model,  RedirectAttributes redir){
		
//		Busca usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = userService.findUserByName(authentication.getName());
		
//		Busca el proyecto en que se esta trabajando y se lo asigna al stakeholder
		Proyecto proyecto = proyectoService.findByid(idProyecto);
		
		Tipornf tipornf = tipornfService.findBynombre("Calidad");
		
		
		System.out.println("LLega a destino");
		
		//Lista de stakeholder preguntas que llega desde javascript
		ArrayList<String> aux = plantillaFE.getEncuestasPorStakeholder();
		
		
		//Inicializa la lista de encuesta
		List<Encuesta> encuestaList = new ArrayList<Encuesta>();
		
		Encuesta encuesta = new Encuesta();
		
		Plantilla plantilla = new Plantilla();
		
		List<PreguntaEncuesta> preguntaEncuestaList = new ArrayList<PreguntaEncuesta> ();
		
		
		// Para entender mejor este for hay que tener en cuesnta lo siguiente:
		//
		// Desde JAVASCRIPT llega de la siguiente manera:
		//
		// <List> [IDSTAKEHOLDER1, PREG1, PREG2, PREG3, ..., PREGN, ..., IDSTAKEHOLDERN, PREG1, PREG2, ..., PREGN] 
		//		
		//
		// EN JAVA tiene el sigueinet formato:
		//
		// Plantilla --
		//            |
		//	          ---> <List> Encuesta -> Posee el stakeholder
		//                            |
		//                             --> <List> PreguntaEncuesta ->Posee el texto de la pregunta
		for (int i = 0; i < aux.size(); i++){
			
			//Desde javascript el campo ID_STAKE llega con el formato "ID_STAKE: " + nro idstakeholder
			if (aux.get(i).toString().substring(0, 9).equalsIgnoreCase("ID_STAKE:")){
				
				Stakeholder stakeholderActual = stakeholderService.findByid(Integer.parseInt(aux.get(i).substring(9)));
				System.out.println("ID del stakeholder: " + aux.get(i).toString());
				System.out.println("ID corto del stakeholder: " + aux.get(i).substring(9));
							
				encuesta = new Encuesta();
				
				//Arma Encuesta
				encuesta.setFechaCreacion(new Date());
				encuesta.setIdProyecto(proyecto);
				encuesta.setIdStakeholder(stakeholderActual);
				encuesta.setIdPlantilla(plantilla);
				//aca pone el nombre al archivo VER COMO GENERARLO
				encuesta.setNombreArchivo("asd");
				
				//Inicializa pregunta list
				preguntaEncuestaList = new ArrayList<PreguntaEncuesta> ();
	
					
			}else{
				
				System.out.println("Pregunta del stakeholder: " + aux.get(i).toString());
				PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();

				preguntaEncuesta.setFechaUpdate(new Date());
	
				//Cambiamos la ^ que viene de javascript por ,
				String preguntaconcoma = aux.get(i).toString().replace("^", ",");
				preguntaEncuesta.setTexto(preguntaconcoma);
				preguntaEncuesta.setIdEncuesta(encuesta);
				preguntaEncuestaList.add(preguntaEncuesta);

				if (( (i+1) >= aux.size() ) ){
					System.out.println("Entra 1");
					encuesta.setPreguntaEncuestaList(preguntaEncuestaList);
					
					encuestaList.add(encuesta);
				}else{
					System.out.println("Entra 2");
					if ( aux.get(i+1).toString().substring(0, 9).equalsIgnoreCase("ID_STAKE:") ){
						System.out.println("Entra 3");						
						encuesta.setPreguntaEncuestaList(preguntaEncuestaList);

						encuestaList.add(encuesta);
					}
					
				}
				
				
			}
				
				
		}
			
		
		//Completo los datos de la plantilla con las encuestas generadas
		
		
		plantilla.setEncuestaList(encuestaList);
		plantilla.setFechacreacion(new Date());
		plantilla.setIdTipoRnf(tipornf);
		plantilla.setIdProyecto(proyecto);
		
//		//Plantilla de prueba
//		
//		Plantilla pl = new Plantilla();
//		Encuesta en = new Encuesta();
//		
//		PreguntaEncuesta pe1 = new PreguntaEncuesta();
//		pe1.setFechaUpdate(new Date());
//		pe1.setTexto("Hola como te va?");
//		pe1.setIdEncuesta(en);
//		
//		PreguntaEncuesta pe2 = new PreguntaEncuesta();
//		pe2.setFechaUpdate(new Date());		
//		pe2.setTexto("Todo bien vos?");
//		pe2.setIdEncuesta(en);
//		
//		List<PreguntaEncuesta> pelist = new ArrayList<PreguntaEncuesta>();
//		pelist.add(pe1);
//		pelist.add(pe2);
//		
//		
//		en.setFechaCreacion(new Date());
//		en.setIdProyecto(proyecto);
//		Stakeholder stakeholderActualasd = stakeholderService.findByid(21);
//		en.setIdStakeholder(stakeholderActualasd);
//		en.setNombreArchivo("asdprueba");
//		en.setIdPlantilla(pl);
//		en.setPreguntaEncuestaList(pelist);
//		
//		List<Encuesta> enlist = new ArrayList<Encuesta>();
//		enlist.add(en);
//		
//
//		pl.setFechacreacion(new Date());
//		pl.setEncuestaList(enlist);
//		pl.setIdTipoRnf(tipornf);
//		pl.setIdProyecto(proyecto);
//		
//		
//		
//		
//		//BORRAR, ES SOLO PARA PROBAR
//		System.out.println("Antes del for");
//		for (int j = 0; j < plantilla.getEncuestaList().size(); j++){
//			
//			System.out.println("NOMBRE STAKEHOLDER: " + plantilla.getEncuestaList().get(j).getIdStakeholder().getNombre());
//			
//			for (int k = 0 ; k < plantilla.getEncuestaList().get(j).getPreguntaEncuestaList().size();k++){
//				
//				System.out.println("PREGUNTA: " + plantilla.getEncuestaList().get(j).getPreguntaEncuestaList().get(k).getTexto());
//				
//			}
//			
//		}
//		
		System.out.println("Lista: " + plantilla.getEncuestasPorStakeholder());
		
			
		plantillaService.savePlantilla(plantilla);

		
		
		redir.addFlashAttribute("user", user);
		redir.addFlashAttribute("proyectoentrabajo", proyecto);
		redir.addFlashAttribute("paginaActual", "stakeholder");
		
		return "redirect:/";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/crearplantillarestricciones/{idProyecto}")
	public String crearPlantillaRestricciones(@PathVariable("idProyecto") int idProyecto, @Valid @ModelAttribute Plantilla plantillaFE, BindingResult bindingResult, Model model,  RedirectAttributes redir){
		
//		Busca usuario
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = userService.findUserByName(authentication.getName());
		
//		Busca el proyecto en que se esta trabajando y se lo asigna al stakeholder
		Proyecto proyecto = proyectoService.findByid(idProyecto);
		
		Tipornf tipornf = tipornfService.findBynombre("Restricciones");
		
		
		System.out.println("LLega a destino");
		
		//Lista de stakeholder preguntas que llega desde javascript
		ArrayList<String> aux = plantillaFE.getEncuestasPorStakeholder();
		System.out.println("aux: " + aux.toString());
		
		//Inicializa la lista de encuesta
		List<Encuesta> encuestaList = new ArrayList<Encuesta>();
		
		Encuesta encuesta = new Encuesta();
		
		Plantilla plantilla = new Plantilla();
		
		List<PreguntaEncuesta> preguntaEncuestaList = new ArrayList<PreguntaEncuesta> ();
		
		
		// Para entender mejor este for hay que tener en cuesnta lo siguiente:
		//
		// Desde JAVASCRIPT llega de la siguiente manera:
		//
		// <List> [IDSTAKEHOLDER1, PREG1, PREG2, PREG3, ..., PREGN, ..., IDSTAKEHOLDERN, PREG1, PREG2, ..., PREGN] 
		//		
		//
		// EN JAVA tiene el sigueinet formato:
		//
		// Plantilla --
		//            |
		//	          ---> <List> Encuesta -> Posee el stakeholder
		//                            |
		//                             --> <List> PreguntaEncuesta ->Posee el texto de la pregunta
		for (int i = 0; i < aux.size(); i++){
			
			//Desde javascript el campo ID_STAKE llega con el formato "ID_STAKE: " + nro idstakeholder
			if (aux.get(i).toString().substring(0, 9).equalsIgnoreCase("ID_STAKE:")){
				
				Stakeholder stakeholderActual = stakeholderService.findByid(Integer.parseInt(aux.get(i).substring(9)));
				System.out.println("ID del stakeholder: " + aux.get(i).toString());
				System.out.println("ID corto del stakeholder: " + aux.get(i).substring(9));
							
				encuesta = new Encuesta();
				
				//Arma Encuesta
				encuesta.setFechaCreacion(new Date());
				encuesta.setIdProyecto(proyecto);
				encuesta.setIdStakeholder(stakeholderActual);
				encuesta.setIdPlantilla(plantilla);
				//aca pone el nombre al archivo VER COMO GENERARLO
				encuesta.setNombreArchivo("asd");
				
				//Inicializa pregunta list
				preguntaEncuestaList = new ArrayList<PreguntaEncuesta> ();
	
					
			}else{
				
				System.out.println("Pregunta del stakeholder: " + aux.get(i).toString());
				PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();

				preguntaEncuesta.setFechaUpdate(new Date());
	
				//Cambiamos la ^ que viene de javascript por ,
				String preguntaconcoma = aux.get(i).toString().replace("^", ",");
				preguntaEncuesta.setTexto(preguntaconcoma);
				preguntaEncuesta.setIdEncuesta(encuesta);
				preguntaEncuestaList.add(preguntaEncuesta);

				if (( (i+1) >= aux.size() ) ){
					System.out.println("Entra 1");
					encuesta.setPreguntaEncuestaList(preguntaEncuestaList);
					
					encuestaList.add(encuesta);
				}else{
					System.out.println("Entra 2");
					if ( aux.get(i+1).toString().substring(0, 9).equalsIgnoreCase("ID_STAKE:") ){
						System.out.println("Entra 3");						
						encuesta.setPreguntaEncuestaList(preguntaEncuestaList);

						encuestaList.add(encuesta);
					}
					
				}
				
				
			}
				
				
		}
			
		
		//Completo los datos de la plantilla con las encuestas generadas
		
		
		plantilla.setEncuestaList(encuestaList);
		plantilla.setFechacreacion(new Date());
		plantilla.setIdTipoRnf(tipornf);
		plantilla.setIdProyecto(proyecto);
		
//		//Plantilla de prueba
//		
//		Plantilla pl = new Plantilla();
//		Encuesta en = new Encuesta();
//		
//		PreguntaEncuesta pe1 = new PreguntaEncuesta();
//		pe1.setFechaUpdate(new Date());
//		pe1.setTexto("Hola como te va?");
//		pe1.setIdEncuesta(en);
//		
//		PreguntaEncuesta pe2 = new PreguntaEncuesta();
//		pe2.setFechaUpdate(new Date());		
//		pe2.setTexto("Todo bien vos?");
//		pe2.setIdEncuesta(en);
//		
//		List<PreguntaEncuesta> pelist = new ArrayList<PreguntaEncuesta>();
//		pelist.add(pe1);
//		pelist.add(pe2);
//		
//		
//		en.setFechaCreacion(new Date());
//		en.setIdProyecto(proyecto);
//		Stakeholder stakeholderActualasd = stakeholderService.findByid(21);
//		en.setIdStakeholder(stakeholderActualasd);
//		en.setNombreArchivo("asdprueba");
//		en.setIdPlantilla(pl);
//		en.setPreguntaEncuestaList(pelist);
//		
//		List<Encuesta> enlist = new ArrayList<Encuesta>();
//		enlist.add(en);
//		
//
//		pl.setFechacreacion(new Date());
//		pl.setEncuestaList(enlist);
//		pl.setIdTipoRnf(tipornf);
//		pl.setIdProyecto(proyecto);
//		
//		
//		
//		
		//BORRAR, ES SOLO PARA PROBAR
		System.out.println("Antes del for");
		for (int j = 0; j < plantilla.getEncuestaList().size(); j++){
			
			System.out.println("NOMBRE STAKEHOLDER: " + plantilla.getEncuestaList().get(j).getIdStakeholder().getNombre());
			
			for (int k = 0 ; k < plantilla.getEncuestaList().get(j).getPreguntaEncuestaList().size();k++){
				
				System.out.println("PREGUNTA: " + plantilla.getEncuestaList().get(j).getPreguntaEncuestaList().get(k).getTexto());
				
			}
			
		}
		
		System.out.println("Lista: " + plantilla.getEncuestasPorStakeholder());
		
			
		plantillaService.savePlantilla(plantilla);
	
		
		redir.addFlashAttribute("user", user);
		redir.addFlashAttribute("proyectoentrabajo", proyecto);
		redir.addFlashAttribute("paginaActual", "stakeholder");
		
		return "redirect:/";
	}
}
