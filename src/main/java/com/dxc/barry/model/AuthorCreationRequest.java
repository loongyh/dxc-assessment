package com.dxc.barry.model;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public record AuthorCreationRequest(String name, String birthday) {

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof AuthorCreationRequest &&
            StringUtils.equalsIgnoreCase(((AuthorCreationRequest) obj).name, name) &&
            StringUtils.equalsIgnoreCase(((AuthorCreationRequest) obj).birthday, birthday));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }

}
