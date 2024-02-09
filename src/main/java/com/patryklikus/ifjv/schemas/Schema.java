/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.ifjv.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patryklikus.ifjv.JsonDataType;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.Map;

@Getter
public class Schema implements IntegerSchema, DoubleSchema, StringSchema, BooleanSchema, ArraySchema, ObjectSchema {
    @JsonProperty(required = true)
    private JsonDataType type;
    @JsonProperty
    private boolean required;
    @JsonProperty
    private Double min;
    @JsonProperty
    private Double max;
    @JsonProperty
    private Integer minLength;
    @JsonProperty
    private Integer maxLength;
    @JsonProperty
    private Integer minSize;
    @JsonProperty
    private Integer maxSize;
    @JsonProperty
    private Schema items;
    @JsonProperty
    @Getter(AccessLevel.NONE)
    private Map<String, Schema> properties;
    @Getter
    private int requiredCount;

    public Schema() {
        requiredCount = -1;
    }

    public void setupRequiredCount() {
        if (requiredCount == -1) {
            requiredCount = (int) properties.values().stream()
                    .filter(Schema::isRequired)
                    .count();
        }
    }

    @Override
    public int getPropertiesCount() {
        return properties.size();
    }

    public Schema getPropertySchema(String key) {
        if (properties == null) {
            return null;
        }
        return properties.get(key);
    }

}
