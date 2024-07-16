package com.hackathon.dsmhackathon21.global.error.exception;

import com.hackathon.dsmhackathon21.global.error.CustomException;
import com.hackathon.dsmhackathon21.global.error.Error;

public class UnauthenticatedEmailException extends CustomException {
    public static final CustomException EXCEPTION = new UnauthenticatedEmailException();

    private UnauthenticatedEmailException() {
        super(Error.UNAUTHENTICATED_EMAIL);
    }
}
