package com.github.RuSichPT.TestOrderMicroservice.mappers;

import com.github.RuSichPT.TestOrderMicroservice.entities.Session;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SessionMapper {
    Session selectSession(String id);

}
