package com.example.model.language;

import com.example.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "language", schema = "demo")
public class Language implements Identifiable {

    public static final int NAME_MIN_LENGTH = 1;
    public static final int NAME_MAX_LENGTH = 64;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH, message = "name length must be at least 1 character and no more than 64 characters")
    @Column(length = NAME_MAX_LENGTH, nullable = false)
    private String name;

    @NotNull(message = "code must not be null")
    @Enumerated(EnumType.STRING)
    @Column(length = 3, nullable = false)
    private Code code;

    public enum Code {
        amh,
        arb,
        eus,
        ben,
        eng,
        por,
        bul,
        cat,
        chr,
        hrv,
        ces,
        dan,
        nld,
        est,
        fil,
        fin,
        fra,
        deu,
        ell,
        guj,
        heb,
        hin,
        hun,
        isl,
        ind,
        ita,
        jpn,
        kan,
        kor,
        lav,
        lit,
        msa,
        mal,
        mar,
        nor,
        pol,
        ron,
        rus,
        srp,
        zho,
        slk,
        slv,
        spa,
        swa,
        swe,
        tam,
        tel,
        tha,
        tur,
        urd,
        ukr,
        vie,
        cym;
    }
}
