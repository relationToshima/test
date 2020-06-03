package com.example.demo.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 会社員Bean
 */
@Component
@Data
public class OfficeWorker extends SalaryInfo {

	/** 名前 **/
	String name;
	/** 基本給＋役職手当 **/
	int basicSalaryAddPositionAllowance;

}
