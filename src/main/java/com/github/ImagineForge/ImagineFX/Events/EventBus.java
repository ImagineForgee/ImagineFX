package com.github.ImagineForge.ImagineFX.Events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private final List<Object> listeners = new ArrayList<>();

    public void register(Object listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }
        listeners.add(listener);
    }

    public void post(Object event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }

        for (Object listener : listeners) {
            for (Method method : listener.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(Subscribe.class)) {
                    if (method.getParameterCount() == 1 && method.getParameterTypes()[0].isAssignableFrom(event.getClass())) {
                        try {
                            method.setAccessible(true);
                            method.invoke(listener, event);

                            if (event instanceof Cancellable && ((Cancellable) event).isCancelled()) {
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
