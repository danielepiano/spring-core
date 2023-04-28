package com.dp.spring.springcore.model.error;

import com.dp.spring.springcore.model._links._LinksModel;
import com.dp.spring.springcore.model._links._LinksRel;
import com.dp.spring.springcore.utils.HttpUtils;
import org.springframework.http.HttpStatus;

import java.util.Set;

/**
 * Body model of an HTTP response, in case of any kind of error.
 *
 * @param _links useful links about the errors
 * @param status the synthetic string representation of the response status: fail|error|generic
 * @param errors the errors found
 */
public record ErrorModel(
        _LinksModel _links,
        ErrorModelStatus status,
        Set<Error> errors
) {
    // Without _LinksModel
    // - ErrorModelStatus
    public ErrorModel(ErrorModelStatus status, Set<Error> errors) {
        this(new _LinksModel(_LinksRel.SELF, HttpUtils.getRelativeURIFromCurrentRequest()), status, errors);
    }

    public ErrorModel(ErrorModelStatus status, Error error) {
        this(new _LinksModel(_LinksRel.SELF, HttpUtils.getRelativeURIFromCurrentRequest()), status, Set.of(error));
    }

    // - HttpStatus
    public ErrorModel(HttpStatus status, Set<Error> errors) {
        this(new _LinksModel(_LinksRel.SELF, HttpUtils.getRelativeURIFromCurrentRequest()), ErrorModelStatus.valueOf(status), errors);
    }

    public ErrorModel(HttpStatus status, Error error) {
        this(new _LinksModel(_LinksRel.SELF, HttpUtils.getRelativeURIFromCurrentRequest()), ErrorModelStatus.valueOf(status), Set.of(error));
    }

    // Given single link as rel + link
    // - ErrorModelStatus
    public ErrorModel(_LinksRel rel, String link, ErrorModelStatus status, Set<Error> errors) {
        this(new _LinksModel(rel, link), status, errors);
    }

    public ErrorModel(_LinksRel rel, String link, ErrorModelStatus status, Error error) {
        this(new _LinksModel(rel, link), status, Set.of(error));
    }

    // - HttpStatus
    public ErrorModel(_LinksRel rel, String link, HttpStatus status, Set<Error> errors) {
        this(new _LinksModel(rel, link), ErrorModelStatus.valueOf(status), errors);
    }

    public ErrorModel(_LinksRel rel, String link, HttpStatus status, Error error) {
        this(new _LinksModel(rel, link), ErrorModelStatus.valueOf(status), Set.of(error));
    }

    // given _LinksModel
    // - ErrorModelStatus: canonical constructor

    public ErrorModel(_LinksModel _links, ErrorModelStatus status, Error error) {
        this(_links, status, Set.of(error));
    }

    // - HttpStatus
    public ErrorModel(_LinksModel _links, HttpStatus status, Set<Error> errors) {
        this(_links, ErrorModelStatus.valueOf(status), errors);
    }

    public ErrorModel(_LinksModel _links, HttpStatus status, Error error) {
        this(_links, ErrorModelStatus.valueOf(status), Set.of(error));
    }
}