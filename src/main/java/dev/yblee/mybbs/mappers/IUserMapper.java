package dev.yblee.mybbs.mappers;

import dev.yblee.mybbs.entities.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserMapper {
    int insert(UserEntity userEntity);

    UserEntity selectUser(UserEntity userEntity);

    int selectCountByEmail(
            @Param(value = "email") String email);

    int selectCountByNickname(
            @Param(value = "nickname") String nickname);
}
