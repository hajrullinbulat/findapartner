package com.findandplay.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.findandplay.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hajrullinbulat on 30.04.17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpaceSportsJson implements Serializable {
    @JsonProperty("json")
    private List<SportType> sports;
}
