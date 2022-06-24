package com.devteam.marketing.domain.usr.service;

import com.devteam.marketing.domain.usr.dto.UsrAgreeDetailDto;
import com.devteam.marketing.domain.usr.repository.UsrAgreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsrAgreeService {

    private final UsrAgreeRepository usrAgreeRepository;

    @Transactional(readOnly = true)
    public List<UsrAgreeDetailDto> findByUsrIdToDetail(Long usrId) {
        return UsrAgreeDetailDto.of(usrAgreeRepository.findByUsrIdToDetail(usrId));
    }

}
