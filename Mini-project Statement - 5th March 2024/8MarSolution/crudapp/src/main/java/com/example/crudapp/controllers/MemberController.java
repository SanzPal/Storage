package com.example.crudapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import com.example.crudapp.models.DemandRequest;
import com.example.crudapp.models.Member;
import com.example.crudapp.models.Demand;

import com.example.crudapp.helpers.CustomComparator;

import com.example.crudapp.repos.MemberRepository;
import com.example.crudapp.repos.DemandRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class MemberController {
    
    
    @Autowired
    MemberRepository memberRepository;
    
    
    @Autowired
    DemandRepository demandRepository;
    
    
    
    
    @GetMapping("/getAllMembers")
    public ResponseEntity<List<Member>> getAllMembers() {
        try {
            List<Member> MemberList = new ArrayList<>();
            memberRepository.findAll().forEach(MemberList::add);
            
            
            if (MemberList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            
            return new ResponseEntity<>(MemberList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/getMemberById/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable @NonNull Long id) {
        try {
            Optional<Member> MemberObj = memberRepository.findById(id);
            if (MemberObj.isPresent()) {
                return new ResponseEntity<>(MemberObj.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PostMapping("/addMember")
    public ResponseEntity<Member> addMember(@RequestBody @NonNull Member Member) {
        try {
            Member MemberObj = memberRepository.save(Member);
            return new ResponseEntity<>(MemberObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("getMembersByDemandId/{id}")
    public ResponseEntity<List<Member>> getMembers(@PathVariable @NonNull Long id) {
        try {
            List<Member> memberList = new ArrayList<Member>();
            Optional<Demand> optionalDemand = demandRepository.findById(id);
            if (!optionalDemand.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            DemandRequest demandRequest = new DemandRequest(optionalDemand.get());
            for (Member member: memberRepository.findAll()) {
                System.out.println(member);
                System.out.println("");
                System.out.println(demandRequest);
                if (demandRequest.matchesMember(member)) memberList.add(member);
            }
            
            
            if (memberList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Collections.sort(memberList, new CustomComparator(demandRequest));
            return new ResponseEntity<>(memberList, HttpStatus.OK);
            
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @DeleteMapping("/deleteMemberById/{id}")
    public ResponseEntity<HttpStatus> deleteMember(@PathVariable @NonNull Long id) {
        try {
            memberRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @DeleteMapping("/deleteAllMembers")
    public ResponseEntity<HttpStatus> deleteAllMembers() {
        try {
            memberRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
