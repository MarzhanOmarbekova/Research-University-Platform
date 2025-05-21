package com.rou.test2.model;

import com.rou.test2.comparator.CitationNumComparator;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Entity
@Data
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private School school;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResearchPaper> researchPapers = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "journal_id")
    )
    private List<Journal> subscriptions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Announcement> notifications = new ArrayList<>();

    public static int calculateHIndex(List<ResearchPaper> papers) {
        papers.sort(new CitationNumComparator());
        int hIndex = 0;
        for (int i = 0; i < papers.size(); i++) {
            if (papers.get(i).getCitationNum() >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }
        return hIndex;
    }
}
