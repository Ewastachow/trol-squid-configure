package trol.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Could not update list")
public class DomainsListHeaderUpdateException extends RuntimeException {
}
