package br.bionexo.api.ubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.bionexo.api.ubs.domain.Ubs;

@Repository
public interface UbsRepository extends JpaRepository<Ubs, Long>, JpaSpecificationExecutor<Ubs> {

}

