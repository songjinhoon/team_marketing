package com.devteam.marketing.domain.usr.service;

import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.agree.repository.AgreeRepository;
import com.devteam.marketing.domain.usr.repository.UsrAgreeRepository;
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

    @Transactional
    public void save(UsrInsertDto usrInsertDto) {
        final List<Agree> agrees = agreeRepository.findAll();
        final Usr usr = usrRepository.save(usrInsertDto.toEntity());
        usrInsertDto.getUsrAgrees().forEach(usrAgreeLinkDto -> {
            final Agree agree = agrees.stream()
                    .filter(data -> data.getId().equals(usrAgreeLinkDto.getAgreeId()))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
            usrAgreeRepository.save(UsrAgreeInsertDto.builder()
                    .usr(usr)
                    .agree(agree)
                    .agreeYn(usrAgreeLinkDto.getAgreeYn())
                    .build().toEntity());
        });
    }

    @Transactional(readOnly = true)
    public UsrSimpleDto findByIdToSimple(Long id) {
        return UsrSimpleDto.of(usrRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Transactional(readOnly = true)
    public List<UsrSimpleDto> findAllToSimple() {
        return UsrSimpleDto.of(usrRepository.findAll());
    }

    @Transactional
    public void update(Long id, UsrUpdateDto usrUpdateDto) {
        final Usr usr = usrRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (!usr.getSocial().equals(Social.NONE)) {
            throw new RuntimeException();
        }
        if (!usrUpdateDto.getPrevPwd().equals(usr.getPwd())) {
            throw new RuntimeException();
        }
        usr.update(usrUpdateDto);
    }

    @Transactional
    public void delete(Long id) {
        usrRepository.delete(usrRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Transactional(readOnly = true)
    public void resetPwdLink(UsrMailDto usrMailDto) {
        final Usr usr = usrRepository.findByEmail(usrMailDto.getEmail()).orElseThrow(IllegalArgumentException::new);
        if (!usr.getSocial().equals(Social.NONE)) {
            throw new RuntimeException();
        }
        mailService.send(MailDto.builder()
                .to(usrMailDto.getEmail())
                .from(GOOGLE_ID)
                .text(usrMailDto.getLink())
                .subject(SUBJECT)
                .build());
    }

}
