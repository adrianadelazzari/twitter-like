package org.ac.cst8277.deLazzari.adriana.service;

import lombok.RequiredArgsConstructor;
import org.ac.cst8277.deLazzari.adriana.domain.repository.MessageLikeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageLikeService {

    private final MessageLikeRepository messageLikeRepository;

    public void like(Integer messageId){

    }
    public Boolean deleteById(Integer messageLikeId){

        return true;
    }
}
