package com.dxc.barry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;

public record BookSearchRequest(@Schema(example = "Real") String title,
                                @Schema(example = "Katy Evans") String author) {

    @JsonIgnore
    @AssertTrue(message = "Either title or author is required")
    public boolean isValid() {
        if (title == null) {
            return author != null;
        }
        if (author == null) {
            return title != null;
        }
        return true;
    }

}
