package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Student {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String studentId;

    private String firstname;
    private String lastname;
    private String Address;
    private String contact;


}
