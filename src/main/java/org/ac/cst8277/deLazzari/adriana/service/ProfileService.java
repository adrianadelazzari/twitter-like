package org.ac.cst8277.deLazzari.adriana.service;

import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.ProfileEntity;
import org.ac.cst8277.deLazzari.adriana.domain.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileEntity save(ProfileEntity profileEntity){

        return null;
    }
    public ProfileEntity findByUserId(Integer userId){

        return null;
    }
}
