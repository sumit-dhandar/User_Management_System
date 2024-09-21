package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;


//if we have limited number of entities then it is good to have mapper classes else always use third party libraries for the same
public class UserMapper {
//convert user JPA entity into userDto
    public static UserDto mapToUserDto (User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    //convert userDto into user JPA Entity
    public static User mapToUser(UserDto userDto){
       User user = new User(
        userDto.getId(),
        userDto.getFirstName(),
        userDto.getLastName(),
        userDto.getEmail()
       );
       return user;
    }
}
