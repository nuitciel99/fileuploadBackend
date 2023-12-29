package com.study.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "FileData")
public class FileData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fileData_seq")
	@SequenceGenerator(name = "fileData_seq", sequenceName = "fileData_SEQ", allocationSize = 1)
	private Long id;
	
	private String name;
	private String type;
	private String filePath;
	
	@Builder
	public FileData(String name, String type, String filePath) {
		this.name = name;
		this.type = type;
		this.filePath = filePath;
	}
	
	
}
