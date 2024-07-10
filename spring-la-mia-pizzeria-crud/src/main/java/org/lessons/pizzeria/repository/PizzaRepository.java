package org.lessons.pizzeria.repository;

import java.util.List;

import org.lessons.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
//	Query custom
	@Query(value = "SELECT * FROM pizza p "
			+ "WHERE p.name LIKE %:input%",
            nativeQuery = true)
    public List<Pizza> search( String input);

}
