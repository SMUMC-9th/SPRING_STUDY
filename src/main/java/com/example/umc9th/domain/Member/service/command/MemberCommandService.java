package com.example.umc9th.domain.Member.service.command;

import com.example.umc9th.domain.Member.dto.req.MemberRequestDTO;
import com.example.umc9th.domain.Member.dto.res.MemberResponseDTO;

public interface MemberCommandService {

    MemberResponseDTO.SignUpResponseDTO signUp(MemberRequestDTO.SignUpRequestDTO dto);
}
