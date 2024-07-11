package projeto.curso.com.br.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
     @GetMapping("/")
	public ModelAndView home() {
    	 
    	 ModelAndView md = new ModelAndView();
    	 md.setViewName("home.html");
    	 return md;
     }
}
