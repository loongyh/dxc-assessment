package com.dxc.barry.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookUpdateRequest {

    private String isbn;

    private String title;

    private Set<AuthorCreationRequest> authors;

    private int year;

    private BigDecimal price;

    private String genre;

    @JsonIgnore
    @AssertTrue(message = "Invalid author details")
    public boolean isValid() {
        if (authors != null) {
            for (AuthorCreationRequest author : authors) {
                if (author != null) {
                    if (StringUtils.isBlank(author.name())) {
                        return false;
                    }
                    if (author.birthday() != null) {
                        try {
                            LocalDate.parse(author.birthday());
                        } catch (DateTimeParseException e) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
