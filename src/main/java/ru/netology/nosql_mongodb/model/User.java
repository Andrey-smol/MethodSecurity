package ru.netology.nosql_mongodb.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    @NotBlank(message = "Имя не может быть пустым или содержать только пробелы")
    private String name;

    @Email(message = "не соответствует формату электронной почты")
    @NotBlank(message = "email не может быть пустым или содержать только пробелы")
    private String email;

    @Min(value = 10, message = "возраст не может быть меньше 10")
    @Max(value = 130, message = "возраст не может быть больше 130")
    private Integer age;
}
