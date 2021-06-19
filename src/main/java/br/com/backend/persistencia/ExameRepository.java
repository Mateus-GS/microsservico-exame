package br.com.backend.persistencia;

import java.util.List;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.com.backend.entidades.Exame;

@EnableScan
public interface ExameRepository extends CrudRepository<Exame, String> {
	List<Exame> findByNome(String nome);
}
