package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(
        description = "UserDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @Schema(
            description = "user first name"
    )
//first name should not be empty
    //@NotEmpty
    //for custimization
    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;


    @Schema(
            description = "user last name"
    )
    //last name should not be empty
    @NotEmpty(message = "User last name should not be empty")
    private String lastName;

    @Schema(
            description = "user email address"
    )
    //email should not be empty
    //email should be valid
    @NotEmpty(message = "User email should not be empty")
    @Email(message = "Email should be valid")
    private String Email;
}
