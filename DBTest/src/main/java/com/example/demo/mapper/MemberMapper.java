package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.domain.OfficeWorker;

@Component
public interface MemberMapper {
	List<OfficeWorker> selectNamePositionSalaryFromMember();
}
