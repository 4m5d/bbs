package dev.yblee.mybbs.services;

import dev.yblee.mybbs.enums.RegisterResult;
import dev.yblee.mybbs.mappers.IUserMapper;
import dev.yblee.mybbs.utils.CryptoUtil;
import dev.yblee.mybbs.vos.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "dev.yblee.mybbs.services.UserService")
public class UserService {
    private final IUserMapper userMapper;

    @Autowired
    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void register(RegisterVo registerVo) {
        registerVo.setPassword(CryptoUtil.hashSha512(registerVo.getPassword()));
        // 주어진 이메일이 있으면 DUPLICATE_EMAIL
        if (this.userMapper.selectCountByEmail(registerVo.getEmail()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_EMAIL);
            return;
        }
        // 주어진 닉네임이 있으면 DUPLICATE_NICKNAME
        if (this.userMapper.selectCountByNickname(registerVo.getNickname()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_NICKNAME);
            return;
        }
        // 인서트 결과 영향을 받은 레코드 수가 0이면 FAILURE, 아니면 SUCCESS
        if (this.userMapper.insert(registerVo) == 0) {
            registerVo.setResult(RegisterResult.FAILURE);
        } else {
            registerVo.setResult(RegisterResult.SUCCESS);
        }
    }
}
