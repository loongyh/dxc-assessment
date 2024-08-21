package com.dxc.barry.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @SequenceGenerator(name = "books_seq", allocationSize = 1, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_seq")
    private long id;

    private String isbn;

    private String title;

    @ManyToMany
    private Set<Author> authors;

    private Integer year;

    private BigDecimal price;

    private String genre;

    @Builder.Default
    private long timeCreated = Instant.now().getEpochSecond();

    @Builder.Default
    private long timeUpdated = Instant.now().getEpochSecond();

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Book &&
            StringUtils.equalsIgnoreCase(((Book) obj).title, this.title) &&
            authors.equals(((Book) obj).authors));
    }

}
