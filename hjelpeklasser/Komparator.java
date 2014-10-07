package hjelpeklasser;

@FunctionalInterface
public interface Komparator<T>{
    int compare(T x, T y); // I interface er automatisk alle abstrakte metoder public, og alle metoder er også abstrakte
}