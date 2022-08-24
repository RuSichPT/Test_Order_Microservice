package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.entities.Session;
import com.github.RuSichPT.TestOrderMicroservice.mappers.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    SessionMapper sessionMapper;

    @Override
    @Cacheable(cacheNames = "session")
    public Session select(String sessionId) {
        System.out.println("select sessionId in data base");
        return sessionMapper.selectSession(sessionId);
    }
}
