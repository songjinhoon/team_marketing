package com.devteam.marketing.domain.agree.service;

import com.devteam.marketing.domain.agree.dto.AgreeDto;
import com.devteam.marketing.domain.agree.repository.AgreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AgreeService {

    private final AgreeRepository agreeRepository;

    public List<AgreeDto.Simple> findAllToSimple() {
        return AgreeDto.Simple.of(agreeRepository.findAll());
    }
}
