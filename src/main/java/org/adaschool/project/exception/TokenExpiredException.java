package org.adaschool.project.exception;

import static org.adaschool.project.utils.Constants.TOKEN_EXPIRED_MALFORMED_ERROR_MESSAGE;

public class TokenExpiredException extends ServerErrorException {

    public TokenExpiredException() {
        super(TOKEN_EXPIRED_MALFORMED_ERROR_MESSAGE);
    }

}
