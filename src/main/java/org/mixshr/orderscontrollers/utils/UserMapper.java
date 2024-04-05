package org.mixshr.orderscontrollers.utils;

import lombok.AllArgsConstructor;
import org.mixshr.orderscontrollers.dto.SignUpDTO;
import org.mixshr.orderscontrollers.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserEntity toUserEntity(SignUpDTO signUpDTO) {
        return Objects.isNull(signUpDTO) ? null : modelMapper.map(signUpDTO, UserEntity.class);
    }
}
