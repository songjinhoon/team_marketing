package com.devteam.marketing.domain.usr.service;

import com.devteam.marketing.domain.usr.dto.UsrDto;
import com.devteam.marketing.domain.usr.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsrService {

    private final UsrRepository usrRepository;

    public List<UsrDto.Simple> findAllToSimple() {
        return UsrDto.Simple.of(usrRepository.findAll());
    }

}
