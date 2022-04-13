package br.com.alura.springData.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.springData.orm.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long>{

}
