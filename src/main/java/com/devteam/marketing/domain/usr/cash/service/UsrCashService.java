package com.devteam.marketing.domain.usr.cash.service;

import com.devteam.marketing.domain.usr.cash.dto.UsrCashDto;
import com.devteam.marketing.domain.usr.cash.entity.UsrCash;
import com.devteam.marketing.domain.usr.cash.repository.UsrCashRepository;
import com.devteam.marketing.domain.usr.root.entity.Usr;
import com.devteam.marketing.domain.usr.root.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class UsrCashService {

    private final UsrCashRepository usrCashRepository;

    private final UsrRepository usrRepository;

    private final EntityManager em;

    public UsrCashDto save(UsrCashDto.Insert usrCashDto) {
        final Optional<Usr> optional = usrRepository.findById(usrCashDto.getUsrId());
        if (optional.isEmpty()) {
            return UsrCashDto.Error.builder()
                    .message("data not found error")
                    .build();
        }
        final Usr usr = optional.get();
        final UsrCash usrCash = UsrCash.create(usrCashDto);
        boolean check = usr.addUsrCash(usrCash);
        if (!check) {
            return UsrCashDto.Error.builder()
                    .message("limit excess error")
                    .build();
        }
        em.flush();
        return UsrCashDto.Detail.of(usrCash);
    }

}
