package com.hackathon.dsmhackathon21.domain.edu.exception;

import com.hackathon.dsmhackathon21.global.error.CustomException;
import com.hackathon.dsmhackathon21.global.error.Error;

public class WordbookNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new WordbookNotFoundException();

    private WordbookNotFoundException() {
        super(Error.WORDBOOK_NOT_FOUND);
    }
}
