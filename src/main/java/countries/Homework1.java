package countries;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

/** @noinspection unused*/
public class Homework1 {

    private List<Country> countries;

    public Homework1() {
        countries = new CountryRepository().getAll();
    }

    /**
     * Returns whether there is at least one country with the word "island" in its name ignoring case.
     */
    public boolean streamPipeline1() {
        return countries.stream().filter(country -> country.getName().toLowerCase().contains("island")).count()>0;
    }

    /**
     *  Returns the first country name that contains the word "island" ignoring case.
     */
    public Optional<String> streamPipeline2() {
        return countries.stream().map(Country::getName).filter(name -> name.toLowerCase().contains("island")).findFirst();
    }

    /**
     * Prints each country name in which the first and the last letters are the same ignoring case.
     */
    public void streamPipeline3() {
        //countries.stream().filter(country -> { var name = country.getName().toLowerCase(); return name.charAt(0) == name.charAt(name.length() - 1 );}).map(country -> country.getName()).forEach(System.out::println);
        countries.stream().map(Country::getName).filter(country -> { var name = country.toLowerCase(); return name.charAt(0) == name.charAt(name.length() - 1 );}).forEach(System.out::println);
    }

    /**
     * Prints the populations of the first ten least populous countries.
     */
    public void streamPipeline4() {
        countries.stream().mapToLong(Country::getPopulation).sorted().limit(10L).forEach(System.out::println);
    }

    /**
     * Prints the names of the first ten least populous countries.
     */
    public void streamPipeline5() {
        countries.stream().sorted(Comparator.comparing(Country::getPopulation)).limit(10L).map(Country::getName).forEach(System.out::println);
    }

    /**
     * Returns summary statistics about the number of country name translations associated with each country.
     */
    public IntSummaryStatistics streamPipeline6() {
        return countries.stream().mapToInt(country -> country.getTranslations().size()).summaryStatistics();
    }

    /**
     * Prints the names of countries in the ascending order of the number of timezones.
     */
    public void streamPipeline7() {
        countries.stream().sorted(Comparator.comparingInt(country -> country.getTimezones().size())).map(Country::getName).forEach(System.out::println);
    }

    /**
     * Prints the number of timezones for each country in the form {@code name:timezones}, in the ascending order of the number of timezones.
     */
    public void streamPipeline8() {
        countries.stream().sorted(Comparator.comparingInt(country -> country.getTimezones().size())).forEach(country -> System.out.println(country.getName() + ":" + country.getTimezones().size()));
    }

    /**
     * Returns the number of countries with no Spanish country name translation (the Spanish language is identified by the language code "es").
     */
    public long streamPipeline9() {
        return countries.stream().map(Country::getTranslations).filter(translations->!(translations.containsKey("es"))).count();
    }

    /**
     * Prints the names of countries with null area.
     */
    public void streamPipeline10() {
        countries.stream().filter(countries->countries.getArea() == null).map(Country::getName).forEach(System.out::println);
    }

    /**
     * Prints all distinct language tags of country name translations sorted in alphabetical order.
     */
    public void streamPipeline11() {
        countries.stream().map(country -> country.getTranslations().keySet()).flatMap(java.util.Set::stream).sorted().distinct().forEach(System.out::println);
    }

    /**
     * Returns the average length of country names.
     */
    public double streamPipeline12() {
        return countries.stream().map(Country::getName).mapToInt(String::length).average().getAsDouble();
    }

    /**
     * Prints all distinct regions of the countries with null area.
     */
    public void streamPipeline13() {
        countries.stream().filter(countries->countries.getArea() == null).map(Country::getRegion).distinct().forEach(System.out::println);
    }

    /**
     * Returns the largest country with non-null area.
     */
    public Optional<Country> streamPipeline14() {
        return countries.stream().filter(country -> country.getArea()!=null).max(Comparator.comparing(Country::getArea));
    }

    /**
     * Prints the names of countries with a non-null area below 1.
     */
    public void streamPipeline15() {
        countries.stream().filter(country -> {BigDecimal area = country.getArea(); return area !=null && area.compareTo(new BigDecimal(1)) < 0; }).map(Country::getName).forEach(System.out::println);
    }

    /**
     * Prints all distinct timezones of European and Asian countries.
     */
    public void streamPipeline16() {
        countries.stream().filter(country -> {Region region = country.getRegion(); return Region.EUROPE == region || Region.ASIA == region;}).map(Country::getTimezones).flatMap(java.util.Collection::stream).distinct().forEach(System.out::println);
    }

}
