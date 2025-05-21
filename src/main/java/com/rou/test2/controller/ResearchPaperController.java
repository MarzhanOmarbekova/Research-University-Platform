package com.rou.test2.controller;

import com.rou.test2.comparator.ArticleLengthComparator;
import com.rou.test2.comparator.CitationNumComparator;
import com.rou.test2.comparator.PublishedDateComparator;
import com.rou.test2.model.Announcement;
import com.rou.test2.model.Journal;
import com.rou.test2.model.ResearchPaper;
import com.rou.test2.model.User;
import com.rou.test2.repository.AnnouncementsRepository;
import com.rou.test2.repository.JournalRepository;
import com.rou.test2.repository.ResearchPaperRepository;
import com.rou.test2.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rps")
public class ResearchPaperController {

    @Autowired
    private ResearchPaperRepository researchPaperRepository;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnnouncementsRepository announcementsRepository;

    @GetMapping
    public String viewResearchPapers(@RequestParam(required = false, defaultValue = "citation") String sortBy, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<ResearchPaper> papers = researchPaperRepository.findByUser(user);

        switch (sortBy) {
            case "articleLength":
                papers.sort(new ArticleLengthComparator());
                break;
            case "publishedDate":
                papers.sort(new PublishedDateComparator());
                break;
            case "citationNum":
            default:
                papers.sort(new CitationNumComparator());
                break;
        }
        model.addAttribute("sortBy", sortBy);

        int hIndex = User.calculateHIndex(papers);
        model.addAttribute("hIndex", hIndex);


        model.addAttribute("papers", papers);
        model.addAttribute("user", user);
        return "research_papers";
    }

    @GetMapping("/new")
    public String newResearchPaper(Model model) {
        ResearchPaper paper = new ResearchPaper();
        model.addAttribute("paper", paper);
        return "new_research_paper";
    }

    @PostMapping("/new")
    public String addResearchPaper(@ModelAttribute ResearchPaper paper, HttpSession session) {
        User user = (User) session.getAttribute("user");
        paper.setUser(user);
        researchPaperRepository.save(paper);
        return "redirect:/rps";
    }

    @GetMapping("/article/{id}")
    public String viewResearchPaper(Model model, @PathVariable Long id) {
        Optional<ResearchPaper> paper = researchPaperRepository.findById(id);
        if (paper.isPresent()) {
            model.addAttribute("title", paper.get().getTitle());
            model.addAttribute("article", paper.get().getArticle());
            return "research_paper_article";
        }
        else {
            model.addAttribute("article", "There is no research article");
            return "research_paper_article";
        }
    }

    @GetMapping("/edit/{id}")
    public String editResearchPaper(Model model, @PathVariable Long id) {
        Optional<ResearchPaper> paper = researchPaperRepository.findById(id);
        if (paper.isPresent()) {
            model.addAttribute("researchPaper", paper.get());
            return "research_paper_edit";
        }
        else {
            return "redirect:/rps";
        }
    }

    @PostMapping("/update/{id}")
    public String updateResearchPaper(@PathVariable Long id, @ModelAttribute ResearchPaper model_paper) {
        Optional<ResearchPaper> paper = researchPaperRepository.findById(id);
        if (paper.isPresent()) {
            paper.get().setTitle(model_paper.getTitle());
            paper.get().setArticle(model_paper.getArticle());
            researchPaperRepository.save(paper.get());
            return "redirect:/rps";
        }
        else {
            return "redirect:/rps";
        }
    }

    @GetMapping("/delete/{researchPaperId}")
    public String deleteResearchPaper(@PathVariable Long researchPaperId) {
        Optional<ResearchPaper> paper = researchPaperRepository.findById(researchPaperId);
        if (paper.isPresent()) {
            researchPaperRepository.delete(paper.get());
            return "redirect:/rps";
        }
        else {
            return "redirect:/rps";
        }
    }

    @GetMapping("/citations/add/{researchPaperId}")
    public String addResearchPaperCitation(Model model, @PathVariable Long researchPaperId) {
        Optional<ResearchPaper> paper = researchPaperRepository.findById(researchPaperId);
        if (paper.isPresent()) {
            model.addAttribute("paper", paper.get());
            return "add_citation";
        }
        else {
            return "redirect:/journal_papers";
        }
    }

    @PostMapping("/citations/add/{researchPaperId}")
    public String addCitation(Model model, @PathVariable Long researchPaperId, @RequestParam String citation) {
        Optional<ResearchPaper> paper = researchPaperRepository.findById(researchPaperId);
        if(paper.isPresent()) {
            paper.get().getCitations().add(citation);
            researchPaperRepository.save(paper.get());
        }
        Long journalId = paper.get().getJournal().getJournal_id();
        return "redirect:/journals/" + journalId + "/paper";
    }

    @PostMapping("/publish/{paperId}")
    public String publishPaper(@PathVariable Long paperId, HttpSession session) {

        Optional<ResearchPaper> paperOpt = researchPaperRepository.findById(paperId);

        if (paperOpt.isPresent()) {
            ResearchPaper paper = paperOpt.get();

            if (paper.getJournal() == null) {
                User user = (User) session.getAttribute("user");

                Journal journal = journalRepository.findBySchool(user.getSchool());

                if (journal != null) {
                    paper.setJournal(journal);
                    paper.setPublishedDate(LocalDate.now());
                    journal.getJournal_papers().add(paper);

                    List<User> subscribers = userRepository.findBySubscriptions(journal);
                    for (User subscriber : subscribers) {
                        Announcement announcement = new Announcement();
                        announcement.setMessage("A new paper titled '" + paper.getTitle() + "' has been published in the " +
                                journal.getJournal_title() + " journal.");
                        announcement.setUser(subscriber);
                        announcement.setJournal(journal);
                        announcementsRepository.save(announcement);
                    }

                    researchPaperRepository.save(paper);
                    journalRepository.save(journal);
                }
            }
        }

        return "redirect:/journals";
    }


}
