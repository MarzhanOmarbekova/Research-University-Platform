package com.rou.test2.controller;

import com.rou.test2.comparator.ArticleLengthComparator;
import com.rou.test2.comparator.CitationNumComparator;
import com.rou.test2.comparator.PublishedDateComparator;
import com.rou.test2.model.Announcement;
import com.rou.test2.model.Journal;
import com.rou.test2.model.User;
import com.rou.test2.repository.AnnouncementsRepository;
import com.rou.test2.repository.JournalRepository;
import com.rou.test2.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnnouncementsRepository announcementsRepository;

    @GetMapping
    public String journals( Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        List<Journal> journals = journalRepository.findAll();
        model.addAttribute("journals", journals);

        List<Boolean> subscriptionsStatus = new ArrayList<>(Collections.nCopies(journals.size(), false));
        for (int i =0; i<journals.size();i++){
            boolean isSubscribed = false;
            for (int j = 0 ; j < user.getSubscriptions().size() ; j++) {
                    if(journals.get(i).getJournal_id().equals(user.getSubscriptions().get(j).getJournal_id())) {
                        isSubscribed = true;
                        break;
                    }
            }
            subscriptionsStatus.set(i,isSubscribed);
        }
        System.out.println(subscriptionsStatus);
        model.addAttribute("subscriptionsStatus", subscriptionsStatus);
        return "view_journals";
    }

    @PostMapping("/subscribe/{journalId}")
    public String subscribe(HttpSession session, @PathVariable Long journalId) {

        User user = (User) session.getAttribute("user");
        Optional<Journal> journal = journalRepository.findById(journalId);

        if (journal.isPresent()) {
            Journal journalObj = journal.get();
            boolean alreadySubscribed = user.getSubscriptions().stream()
                    .anyMatch(j -> j.getJournal_id().equals(journalObj.getJournal_id()));
            if (!alreadySubscribed) {
                user.getSubscriptions().add(journalObj);
                user = userRepository.save(user);
            }

        }
        session.setAttribute("user", user);
        return "redirect:/journals";
    }

    @PostMapping("/unsubscribe/{journalId}")
    public String unsubscribe(HttpSession session, @PathVariable Long journalId) {

        User user = (User) session.getAttribute("user");
        Optional<Journal> journal = journalRepository.findById(journalId);

        if (journal.isPresent()) {
            Journal journalObj = journal.get();
            user.getSubscriptions().removeIf(j -> j.getJournal_id().equals(journalObj.getJournal_id()));
            user = userRepository.save(user);
        }
        userRepository.save(user);
        session.setAttribute("user", userRepository.findById(user.getId()).orElse(null));

        return "redirect:/journals";
    }

    @GetMapping("/{journalId}/paper")
    public String papers(Model model, HttpSession session, @PathVariable Long journalId ,@RequestParam(required = false, defaultValue = "citation") String sortBy) {

        Optional<Journal> journal = journalRepository.findById(journalId);
        User user = (User) session.getAttribute("user");


        if (journal.isPresent()) {

            Journal journalObj = journal.get();

            switch (sortBy) {
                case "articleLength":
                    journalObj.getJournal_papers().sort(new ArticleLengthComparator());
                    break;
                case "publishedDate":
                    journalObj.getJournal_papers().sort(new PublishedDateComparator());
                    break;
                case "citationNum":
                default:
                    journalObj.getJournal_papers().sort(new CitationNumComparator());
                    break;
            }
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("journal", journalObj);
            model.addAttribute("user", user);
            return "journal_papers";
        }
        return "redirect:/journals";
    }

    @GetMapping("/notifications")
    public String notifications(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Announcement> announcements = new ArrayList<>();

        announcements.addAll(announcementsRepository.findByUser(user));

        model.addAttribute("announcements", announcements);
        return "notifications";
    }
}
