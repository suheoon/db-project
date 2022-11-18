package com.db451B07.HW4.company.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class DeleteEmployeeReq {
    private ArrayList<String> ssn;
}
