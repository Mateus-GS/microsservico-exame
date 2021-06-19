package br.com.backend.negocio;

import java.lang.invoke.MethodHandles;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

import br.com.backend.entidades.Exame;
import br.com.backend.persistencia.ExameRepository;

@Service
public class ExameService {
	
	 private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	    
	    private final ExameRepository exameRepo;

	    public ExameService(ExameRepository exameRepository){
	        this.exameRepo=exameRepository;
	    }
	    
	    public List<Exame> getExame(){
	        if(logger.isInfoEnabled()){
	            logger.info("Buscando todos os exames");
	        }
	        Iterable<Exame> lista = this.exameRepo.findAll();
	        if (lista == null) {
	        	return new ArrayList<Exame>();
	        }
	        return IteratorUtils.toList(lista.iterator());
	    }
	    
	    public Exame getExameById(String id){
	        if(logger.isInfoEnabled()){
	            logger.info("Buscando exame com o codigo {}",id);
	        }
	        Optional<Exame> retorno = this.exameRepo.findById(id);
	        if(!retorno.isPresent()){
	            throw new RuntimeException("Exame com o id "+id+" nao encontrada");
	        }
	        return retorno.get();
	    }
	    
	    public Exame saveExame(Exame exame){
	        if(logger.isInfoEnabled()){
	            logger.info("Salvando o Exame com os detalhes {}",exame.toString());
	        }
	        return this.exameRepo.save(exame);
	    }
	    
	    public void deleteExame(String id){
	        if(logger.isInfoEnabled()){
	            logger.info("Excluindo o exame com id {}",id);
	        }
	        this.exameRepo.deleteById(id);
	    }
}
