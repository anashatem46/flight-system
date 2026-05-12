package org.learnjava.flightsystem.user.mapper;

import org.learnjava.flightsystem.user.dto.UserDto;
import org.learnjava.flightsystem.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperSpringConfig.class)
public interface UserMapper {

    UserDto convertToUserDto(User user);

    User convertToUserEntity(UserDto userDto);
}