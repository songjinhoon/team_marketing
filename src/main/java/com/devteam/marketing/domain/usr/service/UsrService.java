package com.devteam.marketing.domain.usr.service;

import com.devteam.marketing.domain.agree.entity.Agree;
import com.devteam.marketing.domain.agree.repository.AgreeRepository;
import com.devteam.marketing.domain.usr.agree.dto.UsrAgreeDto;
import com.devteam.marketing.domain.usr.agree.entity.UsrAgree;
import com.devteam.marketing.domain.usr.agree.repository.UsrAgreeRepository;
import com.devteam.marketing.domain.usr.dto.UsrInsertDto;
import com.devteam.marketing.domain.usr.dto.UsrSimpleDto;
import com.devteam.marketing.domain.usr.repository.UsrRepository;
import com.devteam.marketing.domain.usr.dto.UsrDto;
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
import java.util.Optional;

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
        final Usr usr = usrRepository.save(Usr.create(usrInsertDto));
        final List<Agree> agrees = agreeRepository.findAll();
        usrInsertDto.getUsrAgrees().forEach(data -> {
            final UsrAgree usrAgree = usrAgreeRepository.save(UsrAgree.create(UsrAgreeDto.Save.builder()
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

    public String resetPwdLink(UsrDto.Mail usrDto) {
        final Optional<Usr> optional = usrRepository.findByEmail(usrDto.getEmail());
        if (optional.isEmpty()) {
            return "계정을 확인해주세요.";
        }
        final Usr usr = optional.get();
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

    public UsrSimpleDto update(Long id, UsrDto.Update usrDto) {
        final Usr usr = usrRepository.findById(id).orElseThrow(() -> new NoSuchElementException("data not found"));
        /*if (optional.isEmpty()) {
            return UsrDto.Error.builder()
                    .message("data not found error")
                    .build();
        }
        final Usr usr = optional.get();
        if (!usr.getSocial().equals(Social.NONE)) {
            return UsrDto.Error.builder()
                    .message("this account social login error")
                    .build();
        }
        if (!usrDto.getPrevPwd().equals(usr.getPwd())) {
            return UsrDto.Error.builder()
                    .message("password mismatch error")
                    .build();
        }*/
        usr.updatePwd(usrDto.getNextPwd());
        usr.updateNm(usrDto.getNm());
        return UsrSimpleDto.of(usr);
    }

}
