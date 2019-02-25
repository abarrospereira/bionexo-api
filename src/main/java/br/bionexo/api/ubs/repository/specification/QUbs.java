package br.bionexo.api.ubs.repository.specification;

import static org.springframework.util.StringUtils.isEmpty;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.bionexo.api.ubs.domain.Ubs;

public class QUbs {

	public static Specification<Ubs> idEquals(Long id) {
		return (Root<Ubs> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			
			if (id == null) {
				return null;
			}
			
			return cb.equal(root.get("id"), id);
		};
	}
	
	
	public static Specification<Ubs> nameLike(String name) {
		return (root, query, cb) -> {
			if (isEmpty(name))
				return null;

			return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
		};
	}

}
