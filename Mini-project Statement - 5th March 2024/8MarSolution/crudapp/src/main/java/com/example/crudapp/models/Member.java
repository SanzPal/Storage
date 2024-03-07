package com.example.crudapp.models;


import lombok.*;


import java.sql.Date;
import java.util.Map;


import jakarta.persistence.*;




// import com.example.crudapp.enums.Member_Status;




@Entity
@Table(name="Members")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Member {


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

@Column(unique = true)
private Long eid;


private String firstName;
private String lastName;


private Date doj;


private String level;


private String location;


private Integer overallExp;


@Column(columnDefinition = "varchar(32) default 'AVAILABLE'")
private String memberStatus = "AVAILABLE";


// Skills consist of key-value pairs
@ElementCollection
private Map<String, Integer> skills; 
}