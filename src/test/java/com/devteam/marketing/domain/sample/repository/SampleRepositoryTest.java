package com.devteam.marketing.domain.sample.repository;

import com.devteam.marketing.domain.sample.entity.Sample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class SampleRepositoryTest {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void test() throws Exception {
        Sample sample1 = new Sample();
        sample1.setId(1L);
        Sample sample2 = new Sample();
        sample2.setId(2L);
        Sample sample3 = new Sample();
        sample3.setId(3L);

        em.persist(sample1);
        em.persist(sample2);
        em.persist(sample3);

        em.flush();
        em.clear();

        List<Sample> samples = sampleRepository.findAllTest();
        System.out.println(samples.size());
    }

}