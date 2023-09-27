package com.example.api.social.application.port.out;

import com.example.api.social.dto.AddSocialDto;

public interface SaveSocialPort {
    void saveSocial(AddSocialDto addSocialDto);
}
