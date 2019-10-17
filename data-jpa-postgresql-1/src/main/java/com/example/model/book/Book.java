package com.example.model.book;

import com.example.model.Identifiable;
import com.example.model.keyword.Keyword;
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
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book", schema = "demo")
public class Book implements Identifiable {

    public static final int TITLE_MIN_LENGTH = 1;
    public static final int TITLE_MAX_LENGTH = 100;
    public static final int NUMBER_OF_PAGES_MIN = 1;
    public static final int NUMBER_OF_PAGES_MAX = 10000;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "title must not be null")
    @NotBlank(message = "title must not be blank")
    @Size(min = TITLE_MIN_LENGTH, max = TITLE_MAX_LENGTH, message = "title length must be at least 1 character and no more than 100 characters")
    @Column(length = TITLE_MAX_LENGTH, nullable = false)
    private String title;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "date published must not be null")
    @PastOrPresent(message = "date published must be in the past or present date")
    private LocalDate datePublished;

    @NotNull(message = "number of pages must not be null")
    @Min(value = NUMBER_OF_PAGES_MIN, message = "minimum number of pages violated")
    @Max(value = NUMBER_OF_PAGES_MAX, message = "maximum number of pages violated")
    private Integer numberOfPages;

    @NotNull(message = "family friendly must not be null")
    private Boolean familyFriendly;

    @NotNull(message = "isbn must not be null")
    @NotBlank(message = "isbn must not be blank")
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", message = "isbn format violated")
    @Column(length = 30, nullable = false, unique = true)
    private String isbn;

    @NotNull(message = "format must not be null")
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Book.Format format;

    @NotNull(message = "language must not be null")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @NotNull
    @ManyToMany
    @JoinTable(schema = "demo")
    private Set<Keyword> keywords;

    public enum Format {
        AUDIO_BOOK,
        ELECTRONIC_BOOK,
        GRAPHIC_NOVEL,
        HARD_COVER,
        PAPER_BACK;
    }
}
