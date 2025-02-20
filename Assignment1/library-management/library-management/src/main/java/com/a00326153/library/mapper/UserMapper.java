package com.a00326153.library.mapper;

import com.a00326153.library.dto.LoanDto;
import com.a00326153.library.dto.UserDto;
import com.a00326153.library.entity.User;

import java.util.List;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        if (user.getLoans() != null) {
            List<LoanDto> loanDtos = user.getLoans().stream()
                    .map(loan -> LoanMapper.mapToLoanDto(loan))
                    .toList();
            userDto.setLoans(loanDtos);
        }

        userDto.addHateoasLinks(userDto.getUserId(), null);

        return userDto;
    }
}
