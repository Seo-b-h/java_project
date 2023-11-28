package com.example.board.mapper;

import com.example.board.model.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    Test test() throws Exception;
}
