package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.entities.Session;
import com.github.RuSichPT.TestOrderMicroservice.mappers.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    SessionMapper sessionMapper;

    @Override
    public Session select(String sessionId) {
        return sessionMapper.selectSession(sessionId);
    }
}
