package com.example.crudapp.models;


import java.util.Objects;
import java.util.Set;


import jakarta.persistence.ElementCollection;


// import com.example.crudapp.enums.Demand_Status;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DemandRequest {
    private String requiredLevel;


    private String requiredCity;


    private String requiredMgrName;


    private String requiredProjectName;


    private String requiredDemandStatus;


    // Skills consist of key-value pairs
    @ElementCollection
    private Set<String> requiredSkills;


    public DemandRequest(Demand demand) {
        requiredLevel = demand.getLevel();
        requiredCity = demand.getCity();
        requiredSkills = demand.getSkills();
    }


    public Boolean matchesDemand(Demand demand) {
        System.out.println("demand matching in progress...");
        // System.out.println(this.requiredLevel);
        if (this.requiredLevel != null && !this.requiredLevel.equalsIgnoreCase(demand.getLevel())) {
            // System.out.println(this.requiredLevel + "!=" + demand.getLevel());
            return false;
        }


        if (this.requiredCity != null && !this.requiredCity.equalsIgnoreCase(demand.getCity())) {
            // System.out.println(2);
            return false;
        }
        
        if (this.requiredMgrName != null && !this.requiredMgrName.equalsIgnoreCase(demand.getMgrName())) {
            // System.out.println(3);
            return false;
        }
        if (this.requiredProjectName != null && !this.requiredProjectName.equalsIgnoreCase(demand.getProjectName())) {
            // System.out.println(4);
            return false;
        }
        if (this.requiredDemandStatus != null && !(this.requiredDemandStatus.toString()).equalsIgnoreCase(demand.getDemandStatus().toString())) {
            // System.out.println(5);
            return false;
        }


        Set<String> availableSkills = demand.getSkills();
        // System.out.println(availableSkills);
        // System.out.println(skills);


        if (!Objects.isNull(requiredSkills)) {
            for (String requiredSkill : requiredSkills){
                System.out.println(requiredSkill);
    
                if (!(availableSkills.contains(requiredSkill))) {
                        // System.out.println(requiredSkillName + ":" + requiredSkillExp + ":" + availableSkills.get(requiredSkillName));
                    return false;
                }
            }
        }
        System.out.println("Matched...");
        return true;
    }


    public Boolean matchesMember(Member member) {
        System.out.println("member matching in progress...");
        // System.out.println(this.requiredLevel);
        if (this.requiredLevel != null && !this.requiredLevel.equalsIgnoreCase(member.getLevel())) {
            // System.out.println(this.requiredLevel + "!=" + demand.getLevel());
            // System.out.println(1);
            return false;
        }


        if (this.requiredCity != null && !this.requiredCity.equalsIgnoreCase(member.getLocation())) {
            // System.out.println(2);
            return false;
        }


        Set<String> availableSkills = member.getSkills().keySet();
        // System.out.println(availableSkills);
        // System.out.println(skills);

        if (!Objects.isNull(requiredSkills)) {
            for (String requiredSkill : requiredSkills){
                // System.out.println(requiredSkill);
    
                if (availableSkills.contains(requiredSkill)) {
                    System.out.println("Matched...");
                    return true;
                }
            }
            System.out.println("Didn't match..");
            return false;
        }
        System.out.println("Matched...");
        return true;
    }


    public Integer matchingSkills(Member member) {
        Integer count = 0;
        Set<String> availableSkills = member.getSkills().keySet();
        for (String requiredSkill: requiredSkills) {
            if (availableSkills.contains(requiredSkill)) count++;
        }
        return count;
    }
}