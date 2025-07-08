package net.javaguides.springboot.service.impl;


import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        //convert UserDto into userJPA Entity
//        User user =new User(
//                userDto.getId(),
//                userDto.getFirstName(),
//                userDto.getLastName(),
//                userDto.getEmail()
//        );

        //using mapper class

        //User user = UserMapper.mapToUser(userDto);
        //User user = modelMapper.map(userDto,User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already exists");
        }
        User user = AutoUserMapper.Mapper.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        //convert User JPA entity to userDto
//        UserDto savedUserDto = new UserDto(
//                savedUser.getId(),
//                savedUser.getFirstName(),
//                savedUser.getLastName(),
//                savedUser.getEmail()
//        );


//        using Mapper class

        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

//        UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);
        UserDto savedUserDto = AutoUserMapper.Mapper.mapToUserDto(savedUser);

        // AutoUserMapper is nothing but the mapstruct Mapper Class
        return savedUserDto;
    }

    @Override
    // Cache the result of this method with the key 'itemList'
    @Cacheable(value = "users", key = "#userId")
    public UserDto getUserById(Long userId) {
//        Optional<User> optionalUser = userRepository.findById(userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId)
        );

        //return UserMapper.mapToUserDto(user);
       //return modelMapper.map(user,UserDto.class);
        return AutoUserMapper.Mapper.mapToUserDto(user);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        //return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

        return users.stream().map((user) -> AutoUserMapper.Mapper.mapToUserDto(user)).collect(Collectors.toList());
    }



    @Override
    public UserDto updateUser(UserDto user) {
//        User existingUser = userRepository.findById(user.getId()).get();
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);
        //using modelMapper lib
//        return modelMapper.map(updatedUser,UserDto.class);
        return AutoUserMapper.Mapper.mapToUserDto(updatedUser);
    }


    //delete method have no conversion logic so we do not need to refactor this
    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }



}
