package HomeWork4;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long minors = persons.stream()
                .filter(n -> n.getAge() < 18)
                .count();
        System.out.println("Hесовершеннолетних: " + minors);

        List<String> conscripts = persons.stream()
                .filter(n -> n.getAge() >= 18 && n.getAge() <= 27)
                .filter(n -> n.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("Призывники " + conscripts.size());
        String first = String.valueOf(conscripts.stream()
                .limit(10)
                .collect(Collectors.toList()));
        System.out.println("Фамилии: " + first);

        List<Person> worker = persons.stream()
                .filter(n -> n.getEducation() == Education.HIGHER)
                .filter(n -> {
                    if (n.getSex() == Sex.MAN) {
                        return n.getAge() >= 18 && n.getAge() < 65;
                    } else {
                        return n.getAge() >= 18 && n.getAge() < 60;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Работоспособных " + worker.size());
        String family = String.valueOf(worker.stream()
                .limit(10)
                .collect(Collectors.toList()));
        System.out.println("Фамилии: " + family);


    }
}

