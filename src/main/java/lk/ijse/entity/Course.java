package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Course {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Id
    private String programId;

    private String programName;

    private String duration;

    private double fee;


}