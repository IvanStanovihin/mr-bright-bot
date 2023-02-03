package com.easy.bright.bot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Set;

@Entity
@Table(name = "bot_user")
@Data
@NoArgsConstructor
public class BotUser {

    @Id
    private Long chatId;
    private String firstName;
    private String lastName;
    private String userName;
    private Timestamp registeredAt;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TestResult> testResult;

}
