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
public class CountBookByCategory {
    @Column(name= "categoryid")
    private String CategoryID;

    @Column(name = "bookQuantity")
    private int bookQuantity;


}
