package com.store.storeapp.assembler;

import com.store.storeapp.controller.JournalController;
import com.store.storeapp.model.Journal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class JournalModelAssembler implements RepresentationModelAssembler<Journal, EntityModel<Journal>>{
    @Override
    public EntityModel<Journal> toModel(Journal journal) {

        return EntityModel.of(journal, //
            linkTo(methodOn(JournalController.class).getJournal(journal.getId())).withSelfRel(),
            linkTo(methodOn(JournalController.class).allJournals()).withRel("journals"));
    }
}
