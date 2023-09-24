package com.example.api.user.validator;

import com.example.api.user.dto.CreateUserDto;
import com.example.api.user.type.UserGenderEnum;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreateGenderValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CreateUserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateUserDto creaeUserDto = (CreateUserDto) target;

        if (creaeUserDto.getGender() == null) {
            errors.rejectValue("gender", "enum.required","성별을 넣어주세요");
        }else{
            boolean isValid = false;
            for(UserGenderEnum genderEnum: UserGenderEnum.values()){
                if (genderEnum.equals(creaeUserDto.getGender())){
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                errors.rejectValue("gender","enum.invalid","유효하지 않는 성별 값입니다");
            }
        }
    }
}
