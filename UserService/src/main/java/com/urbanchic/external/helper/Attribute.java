package com.urbanchic.external.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {

    @Id
    private String attributeId;
    private String attributeName;
    private Object attributeValue;
}
