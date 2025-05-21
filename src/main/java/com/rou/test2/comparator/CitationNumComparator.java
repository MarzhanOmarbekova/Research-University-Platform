package com.rou.test2.comparator;

import com.rou.test2.model.ResearchPaper;

import java.util.Comparator;

public class CitationNumComparator implements Comparator<ResearchPaper> {

    @Override
    public int compare(ResearchPaper p1, ResearchPaper p2) {
        return Integer.compare(p1.getCitationNum(), p2.getCitationNum());
    }
}
