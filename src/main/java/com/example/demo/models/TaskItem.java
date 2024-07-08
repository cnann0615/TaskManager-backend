package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaskItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @jakarta.validation.constraints.NotBlank
    private String userId;

    @jakarta.validation.constraints.NotBlank
    private String title;

    private LocalDate deadLine;

    @ManyToOne
    private TaskCategory category;

    @ManyToOne
    private TaskSchedule schedule;

    private String memo;

    private boolean isCompleted = false;

    private Integer orderIndex;
    
}
