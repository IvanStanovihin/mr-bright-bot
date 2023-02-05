package com.easy.bright.bot.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    private String answerOption1;
    private String answerOption2;
    private String answerOption3;
    private String answerOption4;
    private String answerOption5;
    private Boolean freeAnswerEntry;
}
