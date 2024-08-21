package com.dxc.barry.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "authors")
public class Author {

    @Id
    @SequenceGenerator(name = "authors_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_seq")
    @JsonIgnore
    private long id;
    
    private String name;

    private String birthday;

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Author &&
            StringUtils.equalsIgnoreCase(((Author) obj).name, name) &&
            StringUtils.equalsIgnoreCase(((Author) obj).birthday, birthday));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }

}
