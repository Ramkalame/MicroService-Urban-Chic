package com.urbanchic.entity;

import com.urbanchic.entity.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_collection")
public class User {

    @Id
    private String userId;

    private String fullName;

    private String email;

    private String mobileNo;

    private String profileImageUrl;

    @Field(targetType = FieldType.STRING )
    private Role role;

}
