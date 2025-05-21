package com.rou.test2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journal_id;

    @Column(nullable = false)
    private String journal_title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private School school;

    @OneToMany(mappedBy = "journal",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ResearchPaper> journal_papers = new ArrayList<>();


}
