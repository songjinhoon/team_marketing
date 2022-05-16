package com.devteam.marketing.domain.usr.root.service;

import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.agree.repository.AgreeRepository;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.agree.repository.UsrAgreeRepository;
import com.devteam.marketing.domain.usr.root.dto.UsrDto;
import com.devteam.marketing.domain.usr.root.entity.Social;
import com.devteam.marketing.domain.usr.root.entity.Usr;
import com.devteam.marketing.domain.usr.root.repository.UsrRepository;
import com.devteam.marketing.external.dto.MailDto;
import com.devteam.marketing.external.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UsrService {

    private static final String SUBJECT = "[marketing] 비밀번호 재설정 링크";

    @Value("${env.google.id}")
    private String GOOGLE_ID;

    private final UsrRepository usrRepository;

    private final UsrAgreeRepository usrAgreeRepository;

    private final AgreeRepository agreeRepository;

    private final MailService mailService;

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

    public String findPwd(UsrDto.Mail usrDto) {
        final Usr usr = usrRepository.findByEmail(usrDto.getEmail()).orElseGet(Usr::empty);
        if (ObjectUtils.isArray(usr)) {
            return "계정을 확인해주세요.";
        }
        if (!usr.getSocial().equals(Social.NONE)) {
            return usr.getSocial().getValue() + " 비밀번호 찾기를 이용해주세요.";
        }

        return mailService.send(MailDto.builder()
                .to(usrDto.getEmail())
                .from(GOOGLE_ID)
                .text(usrDto.getLink())
                .subject(SUBJECT)
                .build());
    }
}
