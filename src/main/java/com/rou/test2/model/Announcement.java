package com.rou.test2.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name= "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "journal_id", nullable = false)
    private Journal journal;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}