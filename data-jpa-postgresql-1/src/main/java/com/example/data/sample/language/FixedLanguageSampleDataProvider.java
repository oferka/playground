package com.example.data.sample.language;

import com.example.model.language.Language;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.model.language.Language.Code.*;
import static java.util.Arrays.asList;

@Profile({"language_fixed_sample_data_provider", "default"})
@Service
public class FixedLanguageSampleDataProvider implements LanguageSampleDataProvider {

    @Override
    public List<Language> getSampleItems() {
        return asList(
                Language.builder()
                        .name("Amharic")
                        .code(amh)
                        .build(),
                Language.builder()
                        .name("Arabic")
                        .code(arb)
                        .build(),
                Language.builder()
                        .name("Basque")
                        .code(eus)
                        .build(),
                Language.builder()
                        .name("Bengali")
                        .code(ben)
                        .build(),
                Language.builder()
                        .name("English")
                        .code(eng)
                        .build(),
                Language.builder()
                        .name("Portuguese")
                        .code(por)
                        .build(),
                Language.builder()
                        .name("Bulgarian")
                        .code(bul)
                        .build(),
                Language.builder()
                        .name("Catalan")
                        .code(cat)
                        .build(),
                Language.builder()
                        .name("Cherokee")
                        .code(chr)
                        .build(),
                Language.builder()
                        .name("Croatian")
                        .code(hrv)
                        .build(),
                Language.builder()
                        .name("Czech")
                        .code(ces)
                        .build(),
                Language.builder()
                        .name("Danish")
                        .code(dan)
                        .build(),
                Language.builder()
                        .name("Dutch")
                        .code(nld)
                        .build(),
                Language.builder()
                        .name("Estonian")
                        .code(est)
                        .build(),
                Language.builder()
                        .name("Filipino")
                        .code(fil)
                        .build(),
                Language.builder()
                        .name("Finnish")
                        .code(fin)
                        .build(),
                Language.builder()
                        .name("French")
                        .code(fra)
                        .build(),
                Language.builder()
                        .name("German")
                        .code(deu)
                        .build(),
                Language.builder()
                        .name("Greek")
                        .code(ell)
                        .build(),
                Language.builder()
                        .name("Gujarati")
                        .code(guj)
                        .build(),
                Language.builder()
                        .name("Hebrew")
                        .code(heb)
                        .build(),
                Language.builder()
                        .name("Hindi")
                        .code(hin)
                        .build(),
                Language.builder()
                        .name("Hungarian")
                        .code(hun)
                        .build(),
                Language.builder()
                        .name("Icelandic")
                        .code(isl)
                        .build(),
                Language.builder()
                        .name("Indonesian")
                        .code(ind)
                        .build(),
                Language.builder()
                        .name("Italian")
                        .code(ita)
                        .build(),
                Language.builder()
                        .name("Japanese")
                        .code(jpn)
                        .build(),
                Language.builder()
                        .name("Kannada")
                        .code(kan)
                        .build(),
                Language.builder()
                        .name("Korean")
                        .code(kor)
                        .build(),
                Language.builder()
                        .name("Latvian")
                        .code(lav)
                        .build(),
                Language.builder()
                        .name("Lithuanian")
                        .code(lit)
                        .build(),
                Language.builder()
                        .name("Malay")
                        .code(msa)
                        .build(),
                Language.builder()
                        .name("Malayalam")
                        .code(mal)
                        .build(),
                Language.builder()
                        .name("Marathi")
                        .code(mar)
                        .build(),
                Language.builder()
                        .name("Norwegian")
                        .code(nor)
                        .build(),
                Language.builder()
                        .name("Polish")
                        .code(pol)
                        .build(),
                Language.builder()
                        .name("Romanian")
                        .code(ron)
                        .build(),
                Language.builder()
                        .name("Russian")
                        .code(rus)
                        .build(),
                Language.builder()
                        .name("Serbian")
                        .code(srp)
                        .build(),
                Language.builder()
                        .name("Chinese")
                        .code(zho)
                        .build(),
                Language.builder()
                        .name("Slovak")
                        .code(slk)
                        .build(),
                Language.builder()
                        .name("Slovenian")
                        .code(slv)
                        .build(),
                Language.builder()
                        .name("Spanish")
                        .code(spa)
                        .build(),
                Language.builder()
                        .name("Swahili")
                        .code(swa)
                        .build(),
                Language.builder()
                        .name("Swedish")
                        .code(swe)
                        .build(),
                Language.builder()
                        .name("Tamil")
                        .code(tam)
                        .build(),
                Language.builder()
                        .name("Telugu")
                        .code(tel)
                        .build(),
                Language.builder()
                        .name("Thai")
                        .code(tha)
                        .build(),
                Language.builder()
                        .name("Turkish")
                        .code(tur)
                        .build(),
                Language.builder()
                        .name("Urdu")
                        .code(urd)
                        .build(),
                Language.builder()
                        .name("Ukrainian")
                        .code(ukr)
                        .build(),
                Language.builder()
                        .name("Vietnamese")
                        .code(vie)
                        .build(),
                Language.builder()
                        .name("Welsh")
                        .code(cym)
                        .build()
        );
    }

    @Override
    public Language getSampleItem() {
        return Language.builder()
                .name("Amharic")
                .code(amh)
                .build();
    }
}
