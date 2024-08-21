package com.dxc.barry.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookCreationRequest extends BookUpdateRequest {

    @NotNull(message = "Title is required")
    private String title;

}
