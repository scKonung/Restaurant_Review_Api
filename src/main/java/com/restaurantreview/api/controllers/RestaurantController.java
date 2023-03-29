package com.restaurantreview.api.controllers;

import com.restaurantreview.api.dto.RestaurantDto;
import com.restaurantreview.api.dto.RestaurantResponse;
import com.restaurantreview.api.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class RestaurantController {

    private RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("" +
            "")
    public ResponseEntity<RestaurantResponse>getPokemon(
            @RequestParam(value = "pageNo" , defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize) {

        return new ResponseEntity<>( restaurantService.getAllRestaurant(pageNo,pageSize),HttpStatus.OK);
    }

    @GetMapping("restaurant/{id}")
    public ResponseEntity<RestaurantDto> pokemonDetail(@PathVariable int id){
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @PostMapping("restaurant/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestaurantDto> createPokemon(@RequestBody RestaurantDto restaurantDto){
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDto), HttpStatus.CREATED);
    }

    @PutMapping("restaurant/{id}/update")
    public ResponseEntity<RestaurantDto> updatePokemon(@RequestBody RestaurantDto restaurantDto, @PathVariable("id") int pokemonid){
        RestaurantDto response = restaurantService.updateRestaurant(restaurantDto, pokemonid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("restaurant/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id")int pokemonId){
        restaurantService.deleteRestaurant(pokemonId);
        return new ResponseEntity<>("Pokemon delete", HttpStatus.OK);
    }
}
