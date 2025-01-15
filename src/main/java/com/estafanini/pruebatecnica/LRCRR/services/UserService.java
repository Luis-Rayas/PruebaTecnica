package com.estafanini.pruebatecnica.LRCRR.services;

import com.estafanini.pruebatecnica.LRCRR.DTO.UserDto;
import com.estafanini.pruebatecnica.LRCRR.Exeptions.EmailAlreadyExistException;
import com.estafanini.pruebatecnica.LRCRR.Exeptions.UserNotFoundException;
import com.estafanini.pruebatecnica.LRCRR.entities.User;
import com.estafanini.pruebatecnica.LRCRR.repositories.UserRepository;
import com.estafanini.pruebatecnica.LRCRR.services.interfaces.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> getAllDeleted() {
        return userRepository.findAllDeleted();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User save(User user) throws EmailAlreadyExistException  {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException("El email ya esta registrado");
        }

        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) throws UserNotFoundException {

        User userToUpdate = userRepository.findById(user.getId()).orElseThrow(
                () -> new UserNotFoundException("No se encontro el usuario con el id: " + user.getId())
        );

        if (user.getNombre() != null && !user.getNombre().isEmpty()) {
            userToUpdate.setNombre(user.getNombre());
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            userToUpdate.setPassword(user.getPassword());
        }

        return userRepository.save(userToUpdate);
    }

    @Transactional
    public boolean delete(Long id) throws UserNotFoundException {
        User userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("No se encontro el usuario con el id: " + id)
        );

        try {
            userRepository.delete(userToUpdate);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


}
