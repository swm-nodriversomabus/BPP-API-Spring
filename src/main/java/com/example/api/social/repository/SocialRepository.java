package com.example.api.social.repository;

import com.example.api.social.adapter.out.persistence.SocialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<SocialEntity, Long> {

}
