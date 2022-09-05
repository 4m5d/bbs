package dev.yblee.mybbs.enums;

public enum RegisterResult {
    DUPLICATE_EMAIL,     // 이메일 중복
    DUPLICATE_NICKNAME,  // 닉네임 중복
    FAILURE,             // 회원가입 실패
    SUCCESS              // 회원가입 성공
}
