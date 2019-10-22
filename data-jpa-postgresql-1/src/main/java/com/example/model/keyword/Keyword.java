package com.example.model.keyword;

import com.example.model.BaseEntity;
import com.example.model.language.Language;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "keyword", schema = "demo")
public class Keyword implements BaseEntity {

    public static final int TEXT_MIN_LENGTH = 2;
    public static final int TEXT_MAX_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @NotNull(message = "text must not be null")
    @NotBlank(message = "text must not be blank")
    @Size(min = TEXT_MIN_LENGTH, max = TEXT_MAX_LENGTH, message = "text length must be at least 2 character and no more than 30 characters")
    @Column(length = TEXT_MAX_LENGTH, nullable = false)
    private String text;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "date defined must not be null")
    @PastOrPresent(message = "date defined must be in the past or present date")
    private LocalDate dateDefined;

    @NotNull(message = "language must not be null")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;
}
