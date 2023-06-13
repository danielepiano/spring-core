package com.dp.spring.springcore.observer;

/**
 * An Observer is the entity interested in monitoring behaviours or changes in state of a publisher. <br>
 * When notified about an event from the publisher, the observer perform some action accordingly.
 *
 * @param <O> the type of observers for the defined publisher
 * @param <P> the type of the publishers for the given observers
 * @param <C> the type of the context to consider for observer to react properly
 */
public interface ObserverService<O, P extends SimplePublisher<O>, C> {

    /**
     * Making the given observer perform some action when the {@link PublisherService} associated to a given {@link SimplePublisher}
     * invokes the observer.
     *
     * @param observer  the observer to make react after a publisher event
     * @param publisher the publisher triggering the observer reaction
     * @param context   context variable
     */
    void react(O observer, P publisher, C context);

}
