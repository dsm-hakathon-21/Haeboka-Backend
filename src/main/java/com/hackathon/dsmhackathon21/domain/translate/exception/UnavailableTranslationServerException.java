package com.hackathon.dsmhackathon21.domain.translate.exception;

import com.hackathon.dsmhackathon21.global.error.CustomException;
import com.hackathon.dsmhackathon21.global.error.Error;

public class UnavailableTranslationServerException extends CustomException {
    public static final CustomException EXCEPTION = new UnavailableTranslationServerException();

    private UnavailableTranslationServerException() {
        super(Error.UNAVAILABLE_TRANSLATION_SERVER);
    }
}
