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
    private String questionText;
    private int userClass;
    private int score;
    private String correctHint;
    private String firstHint;
    private String secondHint;
    private String thirdHint;
    private int questionType;
}
