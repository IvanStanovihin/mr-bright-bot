package com.easy.bright.bot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "bot_user")
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class BotUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String firstName;
    private String lastName;
    private String telegramName;
    private Timestamp registeredAt;
    private String phoneNumber;
    private String inputName;
    private String schoolClass;
    private String schoolName;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TestResult> testResult;
}