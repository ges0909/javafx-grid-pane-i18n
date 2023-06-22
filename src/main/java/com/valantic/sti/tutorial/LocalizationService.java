package com.valantic.sti.tutorial;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocalizationService {

    @Getter
    private final ObjectProperty<Locale> selectedLocale;
    
    private final Map<Locale, Translation> localeTranslationMap = new HashMap<>();

    public LocalizationService(final Locale locale) {
        selectedLocale = new SimpleObjectProperty<>(locale);
    }

    public void addTranslations(final Locale locale, final Map<String, String> translations) {
        localeTranslationMap.put(locale, new MapTranslation(translations));
    }

    public StringBinding localizedStringBinding(final String key) {
        return Bindings.createStringBinding(() -> getLocalizedString(selectedLocale.get(), key), selectedLocale);
    }

    public String getLocalizedString(final Locale locale, final String key) {
        return localeTranslationMap.get(locale).translate(key);
    }
}
