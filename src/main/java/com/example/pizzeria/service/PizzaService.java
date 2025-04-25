package com.example.pizzeria.service;


import com.example.pizzeria.persistence.entity.PizzaEntity;
import com.example.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.example.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }


    //getall version 1
//    public List<PizzaEntity> getAll(){
//        return this.pizzaRepository.findAll();
//    }

    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public boolean exist(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

    public List<PizzaEntity> getAvailable(){
        System.out.println(this.pizzaRepository.countByVeganTrue());
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public PizzaEntity GetFirstByname(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("la Pizza no existe"));
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheastpest(double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    //version 1
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PizzaService(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<PizzaEntity> getAll(){
//        return this.jdbcTemplate.query("SELECT * FROM pizza WHERE available = 0", new BeanPropertyRowMapper<>(PizzaEntity.class));
//    }
}
