package org.ac.cst8277.deLazzari.adriana.service;

import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.entity.SessionEntity;
import org.ac.cst8277.deLazzari.adriana.domain.repository.SessionRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionEntity save(SessionEntity sessionEntity){

        return this.sessionRepository.save(sessionEntity);
    }
    public SessionEntity findFirstByUuiIdOrderByIdDesc(String uuid){

        return this.sessionRepository.findFirstByUuiIdOrderByIdDesc(uuid);
    }
}
