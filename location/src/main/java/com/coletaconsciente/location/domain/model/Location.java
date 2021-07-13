package com.coletaconsciente.location.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_location")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String name;

	@Valid
	@NotNull
	@Embedded
	private Address address;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Material material;
	
	@NotNull
	@Column(name = "is_Valid", nullable = false)
	private Boolean isValid;

	@CreationTimestamp
	@Column(name = "registration_date", nullable = false, columnDefinition = "datetime")
	private LocalDateTime registrationDate;
	
	@UpdateTimestamp
	@Column(name = "update_date", nullable = false, columnDefinition = "datetime")
	private LocalDateTime updateDate;
	
	@NotNull
	@Column(name = "user_id", nullable = false)
	private Long userId;
}