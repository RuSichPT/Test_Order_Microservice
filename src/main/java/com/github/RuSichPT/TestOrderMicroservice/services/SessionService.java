package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.entities.Session;


public interface SessionService {
    Session select(String sessionId);
}
