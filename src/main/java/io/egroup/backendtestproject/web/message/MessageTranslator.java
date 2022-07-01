package io.egroup.backendtestproject.web.message;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@AllArgsConstructor
public class MessageTranslator {

    private static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("en");

    private final MessageSource messageSource;

    public String getMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, DEFAULT_LOCALE);
    }

    public String getMessage(String messageKey, Object[] arguments) {
        return messageSource.getMessage(messageKey, arguments, DEFAULT_LOCALE);
    }
}
