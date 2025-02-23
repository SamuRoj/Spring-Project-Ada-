package org.adaschool.project.controller.auth;

import java.util.Date;

public record TokenDTO(
        String token,
        Date expirationDate) {

}
