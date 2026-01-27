package model;

import constant.StakeholderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Stakeholder {
    private final String id;
    private final StakeholderType type;
}
