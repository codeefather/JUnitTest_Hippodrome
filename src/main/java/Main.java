import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Main {

    private final static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        List<Horse> horses = List.of(
                new Horse("Bucefal", 2.4),
                new Horse("Tuz Peack", 2.5),
                new Horse("Zephire", 2.6),
                new Horse("Pozhar", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Vishnya", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        log.info("Начало скачек. Количество участников: {}", horses.size());

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println("Winner " + winnerName + "!");
        log.info("Окончание скачек. Победитель: {}", winnerName);
    }

    private static void watch(Hippodrome hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }

}
