package com.rou.test2.comparator;

import com.rou.test2.model.ResearchPaper;

import java.util.Comparator;

public class ArticleLengthComparator implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper p1, ResearchPaper p2) {
        return Integer.compare(p1.getArticleLength(), p2.getArticleLength());
    }
}
