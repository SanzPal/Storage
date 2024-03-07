package com.example.crudapp.repos;


import com.example.crudapp.models.Demand;


// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DemandRepository extends JpaRepository<Demand, Long> {
    // Slice<Demand> findAllBy(Pageable page);
    
    
    // @Query("SELECT * FROM DEMAND WHERE ID = ?1")
    // Demand getDemandById(Long Id);
}