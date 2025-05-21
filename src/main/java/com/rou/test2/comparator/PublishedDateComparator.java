package com.rou.test2.comparator;

import com.rou.test2.model.ResearchPaper;

import java.util.Comparator;

public class PublishedDateComparator implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper p1, ResearchPaper p2) {
        return p1.getPublishedDate().compareTo(p2.getPublishedDate());
    }
}
