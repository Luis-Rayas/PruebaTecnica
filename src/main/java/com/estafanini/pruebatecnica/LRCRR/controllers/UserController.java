package com.estafanini.pruebatecnica.LRCRR.controllers;

import com.estafanini.pruebatecnica.LRCRR.DTO.BaseResponse;
import com.estafanini.pruebatecnica.LRCRR.DTO.NewUserDto;
import com.estafanini.pruebatecnica.LRCRR.DTO.UserDto;
import com.estafanini.pruebatecnica.LRCRR.Exeptions.EmailAlreadyExistException;
import com.estafanini.pruebatecnica.LRCRR.Exeptions.UserNotFoundException;
import com.estafanini.pruebatecnica.LRCRR.entities.User;
import com.estafanini.pruebatecnica.LRCRR.services.interfaces.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final IUserService userService;
    public UserController(
        IUserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<User>>> getUsers(@RequestParam(required = false, defaultValue = "false") boolean withDeleted) {
        BaseResponse<List<User>> response = new BaseResponse<>();

        List<User> users;
        if(withDeleted) {
            users  = userService.getAllDeleted();
        } else {
            users = userService.getAll();
        }

        if(users.isEmpty()) {
            response.getMessages().add("No se encontraron usuarios.");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(new BaseResponse<>(false, null, users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<User>> getUser(@PathVariable Long id) {
        BaseResponse<User> response = new BaseResponse<>();
        Optional<User> user = userService.getUserById(id);

        if(user.isEmpty() || user.get().getFh_eliminacion() != null) {
            return ResponseEntity.notFound().build();
        }

        response.setData(user.get());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<User>> saveUser(@Valid @RequestBody NewUserDto user) {
        BaseResponse<User> response = new BaseResponse<>();

        try {
            User userToSave = User.builder().nombre(user.getNombre()).email(user.getEmail()).password(user.getPassword()).build();
            User userSaved = userService.save(userToSave);

            response.setData(userSaved);
            response.getMessages().add("Usuario creado exitosamente.");

        }
        catch (EmailAlreadyExistException e) {
            response.setError(true);
            response.getMessages().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        catch (Exception e) {
            response.setError(true);
            response.getMessages().add(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<User>> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        BaseResponse<User> response = new BaseResponse<>();

        try {

            User userToUpdate = User.builder()
                    .id(id)
                    .nombre(user.getNombre())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();

            response.setData(userService.update(userToUpdate));
            response.getMessages().add("Usuario con id: " + id + " actualizado exitosamente.");
        }
        catch (UserNotFoundException e) {
            response.setError(true);
            response.getMessages().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        catch (Exception e) {
            response.setError(true);
            response.getMessages().add(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Boolean>> deleteUser(@PathVariable Long id) {
        BaseResponse<Boolean> response = new BaseResponse<>();
        try
        {
            boolean deleted = userService.delete(id);
            response.getMessages().add("Usuario con id: " + id + " eliminado exitosamente.");
            response.setData(deleted);
        } catch (UserNotFoundException e) {
            response.setError(true);
            response.getMessages().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        catch (Exception e) {
            response.setError(true);
            response.getMessages().add(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
        return ResponseEntity.ok(response);
    }

}
