package com.dp.spring.springcore.observer;

/**
 * Business logic methods - separated from the {@link SimplePublisher} entity information - declaring methods allowing
 * subscription and unsubscription of observers to a given publisher.
 *
 * @param <O> the type of observers for the defined publisher
 */
public interface PublisherService<O, P extends SimplePublisher<O>> {

    /**
     * Subscribing a given observer to the given publisher.
     *
     * @param observer  the observer to subscribe
     * @param publisher the publisher to subscribe the observer to
     */
    void addObserver(O observer, P publisher);

    /**
     * Unsubscribing a given observer from the given publisher.
     *
     * @param observer  the observer to unsubscribe
     * @param publisher the publisher to unsubscribe the observer from
     */
    void removeObserver(O observer, P publisher);


    /**
     * Notifying each observer to react to a publisher event, according to the given reaction strategy.
     *
     * @param publisher       the publisher triggering the event
     * @param observerService the strategy defining the reaction for each observer to call
     * @param context         the context to consider for observer to react properly
     * @param <C>             the type of the context to consider for observer to react properly
     * @param <OS>            an observer service strategy to call for triggering the reaction of each observer
     */
    <C, OS extends ObserverService<O, P, C>> void notifyObservers(P publisher, OS observerService, C context);

}
