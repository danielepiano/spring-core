package com.dp.spring.springcore.observer;

import java.util.List;

/**
 * A Publisher is an entity for which observers are interested in monitoring its behaviours or changes in its state.
 * {@link PublisherService} declares methods allowing subscription and unsubscription of observers to a given publisher.
 *
 * @param <O> the type of observers for the defined publisher
 */
public interface SimplePublisher<O> {

    /**
     * Getter for a list of observers: forcing to define an observer fields.
     *
     * @return the list of observers for the given publisher
     */
    List<O> getObservers();

}
