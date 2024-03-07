package com.example.crudapp.repos;


import com.example.crudapp.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    
}