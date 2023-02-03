package com.easy.bright.bot.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "test_result")
@Data
@NoArgsConstructor
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private BotUser user;
    private String question1Result;
    private String question2Result;
    private String question3Result;
    private String question4Result;
    private String question5Result;
}
