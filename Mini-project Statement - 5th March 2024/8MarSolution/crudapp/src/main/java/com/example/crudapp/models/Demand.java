package com.example.crudapp.models;


import lombok.*;


import java.sql.Date;
import java.util.Set;


import jakarta.persistence.*;


// import com.example.crudapp.enums.Demand_Status;


@Entity
@Table(name="Demands")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Demand {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private String projectName;
    
    
    private String mgrName;
    private String level;
    
    
    private String city;
    
    
    @Column(columnDefinition = "varchar(32) default 'OPEN'")
    private String demandStatus = "OPEN";
    
    
    // Say this is in months
    private Integer duration;
    
    
    private Date startDate;
    
    
    @ElementCollection
    private Set<String> skills;
}