package com.dxc.barry.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookCreationRequest extends BookUpdateRequest {

    @Schema(example = "Real")
    @NotNull(message = "Title is required")
    private String title;

}
