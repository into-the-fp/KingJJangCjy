package me.kingcjy.common.event;


import net.jodah.typetools.TypeResolver;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
public interface EventHandler<T> {
    void handle(T event);

    default boolean canHandle(Object event) {
        Class<?>[] typeArgs = TypeResolver.resolveRawArguments(
                EventHandler.class, this.getClass());
        return typeArgs[0].isAssignableFrom(event.getClass());
    }
}

