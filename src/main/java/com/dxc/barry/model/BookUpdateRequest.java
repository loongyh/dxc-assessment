package com.dxc.barry.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateRequest extends BookCreationRequest {

    private String title;

}
