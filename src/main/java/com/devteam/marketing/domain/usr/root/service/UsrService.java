package com.devteam.marketing.domain.usr.root.service;

import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.agree.repository.AgreeRepository;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.agree.repository.UsrAgreeRepository;
import com.devteam.marketing.domain.usr.root.dto.UsrDto;
import com.devteam.marketing.domain.usr.root.entity.Usr;
import com.devteam.marketing.domain.usr.root.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UsrService {

    private final UsrRepository usrRepository;

    private final UsrAgreeRepository usrAgreeRepository;

    private final AgreeRepository agreeRepository;

    public List<UsrDto.Simple> findAllToSimple() {
        return UsrDto.Simple.of(usrRepository.findAll());
    }

    public UsrDto.WithAgree findByIdWithAgree(Long id) {
        final List<UsrAgree> usrAgrees = usrAgreeRepository.findByUsrIdToDetail(id);
        return UsrDto.WithAgree.of(usrAgrees.get(0).getUsr(), usrAgrees);
    }

    public UsrDto.WithAgree saveWithAgree(UsrDto.Insert usrDto) {
        final List<UsrAgree> usrAgrees = new ArrayList<>();
        final Usr usr = usrRepository.save(Usr.create(usrDto));
        final List<Agree> agrees = agreeRepository.findAll();
        usrDto.getUsrAgrees().forEach(usrAgreeDto -> {
            usrAgreeDto.setUsr(usr);
            usrAgreeDto.setAgree(agrees.stream()
                    .filter(agree -> agree.getId().equals(usrAgreeDto.getAgreeId()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new));
            final UsrAgree usrAgree = usrAgreeRepository.save(UsrAgree.create(usrAgreeDto));
            usrAgrees.add(usrAgree);
        });
        return UsrDto.WithAgree.of(usrAgrees.get(0).getUsr(), usrAgrees);
    }
}
