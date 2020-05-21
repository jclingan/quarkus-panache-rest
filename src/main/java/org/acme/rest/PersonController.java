package org.acme.rest;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface PersonController extends PanacheEntityResource<Person, Long> {
}
