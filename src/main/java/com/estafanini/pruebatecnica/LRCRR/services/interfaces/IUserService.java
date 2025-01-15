package com.estafanini.pruebatecnica.LRCRR.services.interfaces;

import com.estafanini.pruebatecnica.LRCRR.DTO.UserDto;
import com.estafanini.pruebatecnica.LRCRR.Exeptions.EmailAlreadyExistException;
import com.estafanini.pruebatecnica.LRCRR.Exeptions.UserNotFoundException;
import com.estafanini.pruebatecnica.LRCRR.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<User> getAll();
    public List<User> getAllDeleted();

    public Optional<User> getUserById(Long id);

    public User save(User user) throws EmailAlreadyExistException;

    public User update(User user) throws UserNotFoundException;

    public boolean delete(Long id) throws UserNotFoundException;
}
