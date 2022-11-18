package com.db451B07.HW4.company.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEmployeeReq {
    private String ssn;
    private String column;
    private String content;
}
