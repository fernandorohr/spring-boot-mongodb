package com.fernando.workshopmongo.dto;

import com.fernando.workshopmongo.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
@Data
@Builder
@EqualsAndHashCode
@ToString
public class AuthorDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
}
