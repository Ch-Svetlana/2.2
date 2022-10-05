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

        long children = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(children);


        List<String> soldiers = persons.stream()
                .filter(x -> x.getSex().equals(Sex.MAN))
                .filter(x -> x.getAge() <= 27)
                .filter(x -> x.getAge() >= 18)
                .map(x -> x.getFamily())
                .sorted()
                .collect(Collectors.toList());

//        soldiers.stream()
//                .forEach(System.out::println);


        List<Person> degreeMan = persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getSex().equals(Sex.MAN))
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 65)
                .collect(Collectors.toList());

        List<Person> degreeWoman = persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getSex().equals(Sex.WOMAN))
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 60)
                .collect(Collectors.toList());

        List<Person> degree = new ArrayList<>();
        degree.addAll(degreeMan);
        degree.addAll(degreeWoman);

        degree.stream()
                .sorted(Comparator.comparing(Person::getFamily))
//                .forEach(System.out::println);
                .collect(Collectors.toList());


    }
}