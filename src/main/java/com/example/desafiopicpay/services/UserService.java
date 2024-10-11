package com.example.desafiopicpay.services;

import com.example.desafiopicpay.domain.entities.User;
import com.example.desafiopicpay.domain.entities.enums.UserCategory;
import com.example.desafiopicpay.domain.repositories.UserRepository;
import com.example.desafiopicpay.rest.dtos.UserDTO;
import com.example.desafiopicpay.services.exceptions.EmailAlreadyExistsException;
import com.example.desafiopicpay.services.exceptions.UnauthorizedTransactionException;
import com.example.desafiopicpay.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public List<User> getAll(){
        return this.userRepository.findAll();
    }

    public User findUserById(Long id) throws UserNotFoundException {
        return this.userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
    }

    public User insertUser(UserDTO userDTO) throws EmailAlreadyExistsException {

        if (userRepository.existsByEmail(userDTO.email())) {
            throw new EmailAlreadyExistsException("Já existe um usuário cadastrado com esse e-mail.");
        }

        User user = new User(userDTO);
        this.saveUser(user);
        return user;
    }

    public void checkTransaction(User sender, BigDecimal amount) throws UnauthorizedTransactionException {
        if (sender.getUserCategory() == UserCategory.MERCHANT){
            throw new UnauthorizedTransactionException("Não é permitida a realização de transação por Usuário do Tipo Lojista.");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new UnauthorizedTransactionException("Saldo insuficiente.");
        }
    }
}
