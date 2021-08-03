/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.swing.bff.mapper;

import com.dostojic.climbers.common.dto.UserDto;
import com.dostojic.climbers.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author planina
 */
@Mapper
public interface UserMapper {

    public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
}
