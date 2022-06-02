package com.devteam.marketing.domain.usr.service;

import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.agree.repository.AgreeRepository;
import com.devteam.marketing.domain.usr.agree.dto.UsrAgreeInsertDto;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.agree.repository.UsrAgreeRepository;
import com.devteam.marketing.domain.usr.dto.*;
import com.devteam.marketing.domain.usr.repository.UsrRepository;
import com.devteam.marketing.domain.usr.entity.Social;
import com.devteam.marketing.domain.usr.entity.Usr;
import com.devteam.marketing.external.dto.MailDto;
import com.devteam.marketing.external.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
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

    public List<UsrSimpleDto> findAllToSimple() {
        return UsrSimpleDto.of(usrRepository.findAll());
    }

    public UsrSimpleDto saveWithAgree(UsrInsertDto usrInsertDto) {
        final Usr check = usrRepository.findByEmail(usrInsertDto.getEmail()).orElseGet(Usr::isEmpty);
        if (check.getId() != null) {
            throw new RuntimeException("id duplicate error");
        }
        final Usr usr = usrRepository.save(Usr.create(usrInsertDto));
        final List<Agree> agrees = agreeRepository.findAll();
        usrInsertDto.getUsrAgrees().forEach(data -> {
            final UsrAgree usrAgree = usrAgreeRepository.save(UsrAgree.create(UsrAgreeInsertDto.builder()
                    .usr(usr)
                    .agree(agrees.stream()
                            .filter(agree -> agree.getId().equals(data.getAgreeId()))
                            .findFirst()
                            .orElseThrow(() -> new NoSuchElementException("data not found")))
                    .agreeYn(data.getAgreeYn())
                    .build()));
        });
        return UsrSimpleDto.of(usr);
    }

    public void resetPwdLink(UsrMailDto usrMailDto) {
        final Usr usr = usrRepository.findByEmail(usrMailDto.getEmail()).orElseThrow(() -> new NoSuchElementException("data not found"));
        if (!usr.getSocial().equals(Social.NONE)) {
            throw new RuntimeException(usr.getSocial().getValue() + "비밀번호 찾기를 이용해주세요");
        }
        mailService.send(MailDto.builder()
                .to(usrMailDto.getEmail())
                .from(GOOGLE_ID)
                .text(usrMailDto.getLink())
                .subject(SUBJECT)
                .build());
    }

    public UsrSimpleDto update(Long id, UsrUpdateDto usrUpdateDto) {
        final Usr usr = usrRepository.findById(id).orElseThrow(() -> new NoSuchElementException("data not found"));
        if (!usr.getSocial().equals(Social.NONE)) {
            throw new RuntimeException("this account social login error");
        }
        if (!usrUpdateDto.getPrevPwd().equals(usr.getPwd())) {
            throw new RuntimeException("password mismatch error");
        }
        usr.updatePwd(usrUpdateDto.getNextPwd());
        usr.updateNm(usrUpdateDto.getNm());
        return UsrSimpleDto.of(usr);
    }

}
