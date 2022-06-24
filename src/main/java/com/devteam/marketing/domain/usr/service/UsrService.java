package com.devteam.marketing.domain.usr.service;

import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.agree.repository.AgreeRepository;
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

    private final AgreeRepository agreeRepository;

    private final UsrAgreeRepository usrAgreeRepository;

    private final MailService mailService;

    public UsrSimpleDto saveWithAgree(UsrInsertDto usrInsertDto) {
        final List<Agree> agrees = agreeRepository.findAll();
        final Usr usr = usrRepository.save(usrInsertDto.toEntity());
        usrInsertDto.getUsrAgrees().forEach(usrAgreeLinkDto -> {
            final Agree agree = agrees.stream()
                    .filter(data -> data.getId().equals(usrAgreeLinkDto.getAgreeId()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);
            final UsrAgree usrAgree = usrAgreeRepository.save(UsrAgreeInsertDto.builder()
                    .usr(usr)
                    .agree(agree)
                    .agreeYn(usrAgreeLinkDto.getAgreeYn())
                    .build().toEntity());
            usr.addUsrAgree(usrAgree);
            agree.addUsrAgree(usrAgree);
        });
        return UsrSimpleDto.of(usr);
    }

    public List<UsrSimpleDto> findAllToSimple() {
        return UsrSimpleDto.of(usrRepository.findAll());
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
