package com.fernando.workshopmongo.application.assembler;

import com.fernando.workshopmongo.domain.User;
import com.fernando.workshopmongo.dto.UserDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import static java.util.Objects.isNull;

@NoArgsConstructor
public class UserAssembler {
  public static UserDto toDto(final User user) {
    if (isNull(user)) return null;

    UserDto userDto = UserDto.builder().build();
    BeanUtils.copyProperties(user, user);
    return userDto;
  }

  public static User toDomain(final UserDto userDto) {
    if (isNull(userDto)) return null;

    User user = User.builder().build();
    BeanUtils.copyProperties(userDto, user);
    return user;
  }
}
