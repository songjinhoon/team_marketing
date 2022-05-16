package com.devteam.marketing.external.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class MailDto {

    private String to;

    private String from;

    private String subject;

    private String text;

}
