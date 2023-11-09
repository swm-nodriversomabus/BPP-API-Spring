package com.example.api.user.validator;

import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.user.dto.CreateUserDto;
import com.example.api.user.dto.UpdateUserDto;
import com.example.api.user.type.UserGenderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class CreateGenderValidator implements Validator {
    @Override
    public boolean supports(Class<?> userClass) {
        return CreateUserDto.class.equals(userClass) || UpdateUserDto.class.equals(userClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target.getClass().equals(CreateUserDto.class)) {
            CreateUserDto createUserDto = (CreateUserDto) target;

            if (createUserDto.getGender() == null) {
                errors.rejectValue("gender", "enum.required","성별을 넣어주세요");
            } else {
                boolean isValid = false;
                for (UserGenderEnum genderEnum: UserGenderEnum.values()) {
                    if (genderEnum.equals(createUserDto.getGender())) {
                        isValid = true;
                        break;
                    }
                }
                if (!isValid) {
                    errors.rejectValue("gender","enum.invalid","유효하지 않는 성별 값입니다");
                }
            }
        } else if (target.getClass().equals(UpdateUserDto.class)) {
            UpdateUserDto updateUserDto = (UpdateUserDto) target;

            boolean isValid = false;
            for (UserGenderEnum genderEnum: UserGenderEnum.values()) {
                if (genderEnum.equals(updateUserDto.getGender())) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                errors.rejectValue("gender","enum.invalid","유효하지 않는 성별 값입니다");
            }
        } else {
            log.error("CreateGenderValidator::validate: Invalid datatype");
            throw new CustomException(ErrorCodeEnum.INVALID_DATATYPE);
        }
    }
}