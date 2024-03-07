package com.example.crudapp.controllers;

import com.example.crudapp.models.Demand;
import com.example.crudapp.models.DemandRequest;
import com.example.crudapp.repos.DemandRepository;


// import com.example.crudapp.model.Member;
// import com.example.crudapp.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
// import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DemandController {

    @Autowired
    DemandRepository demandRepository;

    @GetMapping("/getAllDemands")
    public ResponseEntity<List<Demand>> getAllDemands() {
        try {
            List<Demand> demandList = new ArrayList<>();
            demandRepository.findAll().forEach(demandList::add);

            if (demandList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(demandList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*
    @GetMapping("/getDemands")
    public ResponseEntity<List<Demand>> getDemands( @RequestParam Map<String, String> params ) {
        try {
            // System.out.println(params.keySet());
            // System.out.println(params.values());

            List<Demand> demandList = new ArrayList<>();
            
            // System.out.println("Received a getDemands request: " + city.get() + manager.get() + project.get() + status.get());

            // // Limits to first 50 entries only
            // Slice<Demand> slice = demandRepository.findAllBy(PageRequest.of(0, 50));
            // List<Demand> demands = slice.getContent();

            List<Demand> demands = demandRepository.findAll();
            for (Demand demand: demands) {

                if (params.containsKey("city") && !(demand.getCity().equalsIgnoreCase(params.get("city")))) {
                    // System.out.println(demand.getCity().length() + " " + params.get("city").length());
                    // System.out.println("City doesn't match");
                    continue;
                }
                if (params.containsKey("project") && !(demand.getProjectName().equalsIgnoreCase(params.get("project")))) {
                    continue;
                }
                // if (params.containsKey("status") && Boolean.toString(demand.getMember_assigned()).equalsIgnoreCase(params.get("status"))) {
                //     continue;
                // }
                demandList.add(demand);
            }

            if (demandList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(demandList, HttpStatus.OK);

            // List<Demand> demandList = new ArrayList<>();
            // demandRepository.findAll().forEach(demandList::add);

            // if (demandList.isEmpty()) {
            //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // }

            // return new ResponseEntity<>(demandList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

    @GetMapping("/getDemandById/{id}")
    public ResponseEntity<Demand> getDemandById(@PathVariable @NonNull Long id) {
        Optional<Demand> DemandObj = demandRepository.findById(id);
        if (DemandObj.isPresent()) {
            return new ResponseEntity<>(DemandObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("getDemands")
    public ResponseEntity<List<Demand>> getDemands(@RequestBody DemandRequest demandRequest) {
        try {
            System.out.println("Received JSON is: " + demandRequest);
            
            List<Demand> demandList = new ArrayList<>();
            for (Demand demand: demandRepository.findAll()) {
                // System.out.println(demand);
                // System.out.println("");
                // System.out.println(demandRequest);
                if (demandRequest.matchesDemand(demand)) demandList.add(demand);
            }

            if (demandList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(demandList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addDemand")
    public ResponseEntity<Demand> addDemand(@RequestBody @NonNull Demand demand) {
        try {
            System.out.println("Received JSON is: " + demand);
            Demand DemandObj = demandRepository.save(demand);
            return new ResponseEntity<>(DemandObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteDemandById/{id}")
    public ResponseEntity<HttpStatus> deleteDemand(@PathVariable @NonNull Long id) {
        try {
            demandRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllDemands")
    public ResponseEntity<HttpStatus> deleteAllDemands() {
        try {
            demandRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}