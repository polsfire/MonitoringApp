package com.bfi.ecm.Mappers;

import com.bfi.ecm.DTO.SignupDto;
import com.bfi.ecm.DTO.UserDto;
import com.bfi.ecm.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel= "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    @Mapping(target = "password",ignore=true)
    User  signupToUser (SignupDto sigundto);

}

/*
first import the dependency :
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>1.5.3.Final</version>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>1.5.3.Final</version>
            <scope>provided</scope>
        </dependency>
//---------------------------
second : look top
//---------
public PatientDTO get(Long id) {
    Optional<Patient> patient = this.patientRepo.findById(id);
    return PatientDTO.builder()
        .id(patient.get().getId())
        .patientName(patient.get().getPatientName())
        .patientAge(patient.get().getPatientAge())
        .build();
}
//-------------
public PatientDTO get(Long id) {
    Optional<Patient> patient = this.patientRepo.findById(id);
    return this.patientMapper.toDTO(patient.get());
}

destination namefuntion(source)


*/