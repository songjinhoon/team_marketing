package com.devteam.marketing.domain.usr.root.service;

import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.agree.repository.UsrAgreeRepository;
import com.devteam.marketing.domain.usr.root.dto.UsrDto;
import com.devteam.marketing.domain.usr.root.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsrService {

    private final UsrRepository usrRepository;

    private final UsrAgreeRepository usrAgreeRepository;

    public List<UsrDto.Simple> findAllToSimple() {
        return UsrDto.Simple.of(usrRepository.findAll());
    }

    public UsrDto.WithAgree findByIdWithAgree(Long id) {
        final List<UsrAgree> usrAgrees = usrAgreeRepository.findByUsrIdToDetail(id);
        return UsrDto.WithAgree.of(usrAgrees.get(0).getUsr(), usrAgrees);
    }
}
