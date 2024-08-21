package com.dxc.barry.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookExistsException extends Exception {

    private final long id;

    @Override
    public String getMessage() {
        return "Book exists with ID: " + id;
     }

}
