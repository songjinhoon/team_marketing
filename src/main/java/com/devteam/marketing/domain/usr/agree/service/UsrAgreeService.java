package com.devteam.marketing.domain.usr.agree.service;

import com.devteam.marketing.domain.usr.agree.dto.UsrAgreeDetailTimeDto;
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

    public List<UsrAgreeDetailTimeDto> findByUsrIdToDetail(Long usrId) {
        return UsrAgreeDetailTimeDto.of(usrAgreeRepository.findByUsrIdToDetail(usrId));
    }

}
