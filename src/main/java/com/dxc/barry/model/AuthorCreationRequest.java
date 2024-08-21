package com.dxc.barry.model;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.StringUtils;

public record AuthorCreationRequest(@Schema(example = "Katy Evans") String name,
                                    @Schema(example = "1976-03-03") String birthday) {

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
