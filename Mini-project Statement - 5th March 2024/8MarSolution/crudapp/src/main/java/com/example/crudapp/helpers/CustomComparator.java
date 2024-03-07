package com.example.crudapp.helpers;


import java.util.Comparator;

import com.example.crudapp.models.DemandRequest;
import com.example.crudapp.models.Member;


public class CustomComparator implements Comparator<Member> {


    DemandRequest demandRequest;


    public CustomComparator(DemandRequest demandRequest) {
        this.demandRequest = demandRequest;
    }


    public int compare(Member m1, Member m2) {
        return demandRequest.matchingSkills(m2).compareTo(demandRequest.matchingSkills(m1));
    }
}