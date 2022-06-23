package com.devteam.marketing.domain.agree.service;

import com.devteam.marketing.domain.agree.dto.AgreeSimpleTimeDto;
import com.devteam.marketing.domain.agree.repository.AgreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AgreeService {

    private final AgreeRepository agreeRepository;

    public List<AgreeSimpleTimeDto> findAllToSimple() {
        return AgreeSimpleTimeDto.of(agreeRepository.findAll());
    }

}
