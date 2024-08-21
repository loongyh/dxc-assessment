package com.dxc.barry.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookNotFoundException extends Exception {

    private final long id;

    @Override
    public String getMessage() {
        return "Book with ID " + id + " not found";
     }

}
