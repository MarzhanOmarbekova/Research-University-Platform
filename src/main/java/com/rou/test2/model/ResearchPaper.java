package com.rou.test2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "research_papers")
public class ResearchPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "research_paper_id")
    private Long researchPaperId;

    @Column(nullable = false)
    private String title;

    @ElementCollection
    @CollectionTable(name = "research_paper_citations",joinColumns = @JoinColumn(name = "research_paper_id"))
    @Column(name = "citation")
    private List<String> citations;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String article;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "journal_id")
    private Journal journal;

    public int getArticleLength() {
        return article.length();
    }

    public int getCitationNum() {
        return citations!=null ? citations.size():0;
    }
}
