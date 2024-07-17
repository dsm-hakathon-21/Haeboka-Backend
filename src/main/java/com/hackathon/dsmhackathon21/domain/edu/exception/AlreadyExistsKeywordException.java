package com.hackathon.dsmhackathon21.domain.edu.exception;

import com.hackathon.dsmhackathon21.global.error.CustomException;
import com.hackathon.dsmhackathon21.global.error.Error;

public class AlreadyExistsKeywordException extends CustomException {
    public static final CustomException EXCEPTION = new AlreadyExistsKeywordException();

    private AlreadyExistsKeywordException() {
        super(Error.ALREADY_EXISTS_KEYWORD);
    }
}
