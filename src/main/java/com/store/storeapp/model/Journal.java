package com.store.storeapp.model;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Entity(name = "Journal")
@Table(name = "journal")
@NoArgsConstructor
@AllArgsConstructor
public class Journal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Setter
    private Long id;

    private @Getter @Setter String email;
    private @Getter @Setter Date dateTimeCreated;
    private @Getter @Setter String description;
    private @Getter @Setter String entry;
    private @Getter @Setter String imagePath;
}