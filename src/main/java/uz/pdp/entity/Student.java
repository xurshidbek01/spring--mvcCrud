package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.AliasFor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student {
    private Integer id;
    private String name;
    private String email;
    private String course;
    private Integer class_id;
    private Integer group_id;

}

