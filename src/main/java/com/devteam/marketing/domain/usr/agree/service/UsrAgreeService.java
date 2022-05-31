package com.devteam.marketing.domain.usr.agree.service;

import com.devteam.marketing.domain.usr.agree.dto.UsrAgreeDetailDto;
import com.devteam.marketing.domain.usr.agree.repository.UsrAgreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class UsrAgreeService {

    private final UsrAgreeRepository usrAgreeRepository;

    public List<UsrAgreeDetailDto> findByUsrIdToDetail(Long usrId) {
        return UsrAgreeDetailDto.of(usrAgreeRepository.findByUsrIdToDetail(usrId));
    }

}
