package dev.zooty;

import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.reflections.scanners.Scanners.SubTypes;

public class AdventOfCode {

    /**
     * Runs the program, calculates every day's both solutions and prints it to the standard output.
     * If arguments are present, only those days are calculated
     *
     * @param args A list of numbers. Only those days will be processed.
     */
    public static void main(String[] args) {
        getDaySolutions()
                .filter(filterByAppArguments(args))
                .forEach(day -> {
                    long startMillis = System.currentTimeMillis();
                    System.out.printf("Day %d solutions:%n    1: %s, 2: %s (took %d milliseconds)%n", //NOSONAR
                            day.getDay(),
                            invoke("getSolution1", day),
                            invoke("getSolution2", day),
                            System.currentTimeMillis() - startMillis);
                });
    }

    @SneakyThrows
    private static String invoke(String methodName, Day day) {
        Method method = day.getClass().getMethod(methodName);
        if (method.isAnnotationPresent(Ignored.class)) {
            return "IGNORED" + getReason(method.getAnnotation(Ignored.class));
        } else {
            return (String) method.invoke(day);
        }
    }

    private static String getReason(Ignored annotation) {
        return (annotation.reason().isEmpty() ? "" : "(%s)".formatted(annotation.reason()));
    }

    private static Predicate<Day> filterByAppArguments(String[] args) {
        return args.length > 0 ?
                day -> Arrays.stream(args).map(Integer::parseInt).toList().contains(day.getDay()) :
                day -> true;
    }

    @SuppressWarnings("unchecked")
    private static Stream<Day> getDaySolutions() {
        return new Reflections("dev.zooty")
                .get(SubTypes.of(Day.class).asClass())
                .stream()
                .filter(aClass -> Arrays.asList(aClass.getInterfaces()).contains(Day.class))
                .map(aClass -> construct((Class<Day>) aClass))
                .sorted(Comparator.comparingInt(Day::getDay));
    }

    @SneakyThrows
    private static Day construct(Class<Day> aClass) {
        return aClass.getDeclaredConstructor().newInstance();
    }
}

