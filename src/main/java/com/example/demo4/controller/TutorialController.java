package com.example.demo4.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo4.model.MainModel;
import com.example.demo4.repository.MainRepository;


@RestController
@RequestMapping("/api")
public class TutorialController {
    
    @Autowired
    MainRepository mainRepository;

    @GetMapping("/show_all")
    public List<MainModel> getAllTutorials(){
        return (List<MainModel>) mainRepository.findAll();    
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<MainModel> geTutorialById(@PathVariable Long id){
        Optional<MainModel> model = mainRepository.findById(id);

        if (model.isPresent()) {
      return new ResponseEntity<>(model.get(), HttpStatus.OK);
    } 
    else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    @PostMapping("/create")
    public ResponseEntity<MainModel> createTutorial(@RequestBody MainModel model){
        MainModel _model = mainRepository
            .save(new MainModel(model.getFirstname(), model.getLastname(), model.getPassword()));
        return new ResponseEntity<>(_model, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus> deleteAllTutorial(){
        mainRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> delete_by_id(@PathVariable("id") long id){
        mainRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/insert/{id}")
    public ResponseEntity<MainModel> updateTutorial(@PathVariable("id") long id, @RequestBody MainModel model){
            Optional<MainModel> __model = mainRepository.findById(id);
        if(__model.isPresent()){
            MainModel _tutorial = __model.get();
            _tutorial.setFirstname(model.getFirstname());
            _tutorial.setLastname(model.getLastname());
            _tutorial.setPassword(model.getPassword());

            return new ResponseEntity<>(mainRepository.save(_tutorial), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    private Sort.Direction getSortDirection(String direction) {

        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
          } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
          }
      
          return Sort.Direction.ASC;

    }
    @GetMapping("/paginationandsorting")
    public ResponseEntity<Map<String, Object>> getAllTutorialsPage(
        @RequestParam(required = false) String title,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size,
        @RequestParam(defaultValue = "id,asc") String[] sort) {
  
        List<Order> orders = new ArrayList<Order>();
  
        if (sort[0].contains(",")) {
          for (String sortOrder : sort) {
            String[] _sort = sortOrder.split(",");
            orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
          }
        } else {
          // sort=[field, direction]
          orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }
  
        List<MainModel> tutorials = new ArrayList<MainModel>();
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
  
        Page<MainModel> pageTuts;
        pageTuts = mainRepository.findAll(pagingSort);
      
        tutorials = pageTuts.getContent();
  
        Map<String, Object> response = new HashMap<>();
        response.put("tutorials", tutorials);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());
  
        return new ResponseEntity<>(response, HttpStatus.OK);
      }

}
  
 



