package br.com.backend.rest;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.entidades.Exame;
import br.com.backend.negocio.ExameService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "exame")
public class ExameController {
	
	private final ExameService exameService;

    public ExameController(ExameService exameService){
        this.exameService=exameService;
    }

    @GetMapping(value = "")
    public List<Exame> getExame(){
        return exameService.getExame();
    }
    
    @GetMapping(value="{id}")
    public Exame getExameById(@PathVariable String id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return exameService.getExameById(id);
        }
        throw new Exception("Exame com codigo "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Exame createExame(@RequestBody @NotNull Exame exame) throws Exception {
         return exameService.saveExame(exame);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Exame updateExame(@PathVariable String id, 
    		@RequestBody @NotNull Exame exame) throws Exception {
         return exameService.saveExame(exame);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean deleteExame(@PathVariable String id) throws Exception {
         exameService.deleteExame(id);
         return true;
    }
}
