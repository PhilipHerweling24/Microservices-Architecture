package com.a00326153.library.dto;

import com.a00326153.library.controller.LoanController;
import com.a00326153.library.controller.UserController;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDto extends RepresentationModel<UserDto>{

    private Long userId;
    @NotBlank(message = "Name cannot be left blank")
    @Size(min = 3, max = 50, message = "Name has to be between 3 and 50 characters long")
    private String name;

    @NotBlank(message = "Email can't be left blank.")
    @Email(message = "Invalid format for an email")
    private String email;


    public void addHateoasLinks(Long userId, Pageable pageable){
        this.add(linkTo(methodOn(UserController.class).getUserById(userId)).withSelfRel());
        this.add(linkTo(methodOn(UserController.class).getAllUsers(pageable)).withRel("all-users"));
        this.add(linkTo(methodOn(LoanController.class).getLoansByUser(userId, pageable)).withRel("user-loans"));
        this.add(linkTo(methodOn(UserController.class).updateUser(userId, null)).withRel("update-user"));
        this.add(linkTo(methodOn(UserController.class).deleteUser(userId)).withRel("delete-user"));
    }
}
