package com.store.storeapp.service;

import java.util.Date;
import java.util.List;

import com.store.storeapp.exception.JournalNotFoundException;
import com.store.storeapp.model.Journal;
import com.store.storeapp.repository.JournalRepository;

import org.springframework.stereotype.Service;


@Service
public class JournalService {
    private JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository){
        this.journalRepository = journalRepository;
    }

    public List<Journal> findByEmail(String email){
        return journalRepository.findByEmailOrderByDateTimeCreatedDesc(email);
    }

    public List<Journal> findAll(){
        return journalRepository.findAll();
    }

    public Journal findById(Long id) throws JournalNotFoundException {
        return journalRepository.findById(id)
            .orElseThrow(() -> new JournalNotFoundException(id));
    }

    public Journal createJournal(String email, String description, String entry, String imagePath){
        Journal journal = new Journal();
        journal.setEmail(email);
        journal.setDescription(description);
        journal.setEntry(entry);
        journal.setImagePath(imagePath);
        journal.setDateTimeCreated(new Date());
        return journalRepository.save(journal);
    }

    public Journal putJournal(Journal upsertedJournal){
        return journalRepository.findById(upsertedJournal.getId())
            .map(journal -> {
                journal.setEmail(upsertedJournal.getEmail());
                journal.setDescription(upsertedJournal.getDescription());
                journal.setEntry(upsertedJournal.getEntry());
                journal.setImagePath(upsertedJournal.getImagePath());
                journal.setDateTimeCreated(upsertedJournal.getDateTimeCreated());
                return journalRepository.save(journal);
            })
            .orElseGet(() -> {
                upsertedJournal.setId(upsertedJournal.getId());
                return journalRepository.save(upsertedJournal);
            });
    }
}