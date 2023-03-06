package com.dp.spring.springcore.v2.model._links;

import java.util.EnumMap;
import java.util.Map;

/**
 * Model for including helpful links in either an ErrorModel or a SuccessModel.
 */
public class _LinksModel extends EnumMap<_LinksRel, String> {
    public _LinksModel() {
        super(_LinksRel.class);
    }

    public _LinksModel(_LinksRel rel, String link) {
        this();
        super.put(rel, link);
    }

    public _LinksModel(EnumMap<_LinksRel, ? extends String> m) {
        super(m);
    }

    public _LinksModel(Map<_LinksRel, ? extends String> m) {
        super(m);
    }
}
