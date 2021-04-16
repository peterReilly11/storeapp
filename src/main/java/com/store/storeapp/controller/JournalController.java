package com.store.storeapp.controller;

import com.store.storeapp.assembler.JournalModelAssembler;
import com.store.storeapp.model.Journal;
import com.store.storeapp.service.JournalService;

import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JournalController {
    private JournalService journalService;
    private final JournalModelAssembler assembler;

    public JournalController(JournalService journalService, JournalModelAssembler assembler){
        this.journalService = journalService;
        this.assembler = assembler;
    }

    @GetMapping("/all-journals")
    public CollectionModel<EntityModel<Journal>> allJournals() {
        List<EntityModel<Journal>> journals = journalService.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        System.out.println("journlas " + journals);

        return CollectionModel.of(journals, linkTo(methodOn(JournalController.class).allJournals()).withSelfRel());
    }

    @GetMapping("/journal/{id}")
    public EntityModel<Journal> getJournal(@PathVariable Long id) {
        Journal journal = null;
        try{
            journal = journalService.findById(id);
        } catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        return assembler.toModel(journal);
    }

    @GetMapping("/journals-by-email")
    public CollectionModel<EntityModel<Journal>> journalsByEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<EntityModel<Journal>> journals = journalService.findByEmail(email).stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        
        return CollectionModel.of(journals, linkTo(methodOn(JournalController.class).allJournals()).withSelfRel());
    }

    @PutMapping("/journal")
    public ResponseEntity<?> putJournal(@RequestBody Journal upsertedJournal) {
        Journal updatedJournal = journalService.putJournal(upsertedJournal);
        EntityModel<Journal> entityModel = assembler.toModel(updatedJournal);

        return ResponseEntity 
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @PostMapping("/journal")
    public ResponseEntity<?> postJournal(@RequestBody Journal newJournal) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(newJournal.toString());
        Journal createdJournal = journalService.createJournal(
            email,
            newJournal.getDescription(),
            newJournal.getEntry(),
            ""
        );
        EntityModel<Journal> entityModel = assembler.toModel(createdJournal);

        return ResponseEntity 
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }
}