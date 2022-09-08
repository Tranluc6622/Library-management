package com.elcom.model_dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Data
@ToString(callSuper = true)
public class CountBookByFirstLetter {
    @Column(name= "firstLetter")
    private String firstLetter;

    @Column(name = "bookQuantity")
    private int bookQuantity;

}
