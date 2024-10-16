package dev.solyanka.solyankabot.data;

import dev.solyanka.solyankabot.telegram.enumeration.ContextKey;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class ChatContextValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "context_id", nullable = false)
    private ChatContext chatContext;

    @Column(nullable = false)
    private ContextKey key;

    @Column
    private String value;

}
