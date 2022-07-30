import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HippodromeTest {
    public List<Horse> horses;

//    Проверить, что при передаче в конструктор null, будет выброшено IllegalArgumentException;
//    Проверить, что при передаче в конструктор null,
//      выброшенное исключение будет содержать сообщение "Horses cannot be null.";
//    Проверить, что при передаче в конструктор пустого списка, будет выброшено IllegalArgumentException;
//    Проверить, что при передаче в конструктор пустого списка,
//      выброшенное исключение будет содержать сообщение "Horses cannot be empty.";

    @Test
    void constructorParamIsNull(){
        horses = null;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(horses)
        );

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorParamIsEmpty(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

//    Проверить, что метод возвращает список, который содержит те же объекты и в той же последовательности,
//    что и список который был передан в конструктор.
//    При создании объекта Hippodrome передай в конструктор список из 30 разных лошадей;
    @Test
    void getHorsesTest(){
        horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horse"+i, i+1, i+2));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

//   Проверить, что метод вызывает метод move у всех лошадей.
//   При создании объекта Hippodrome передай в конструктор список из 50 моков лошадей и воспользуйся методом verify.

    @Test
    void horseMoveTest(){
        horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse: horses) {
            verify(horse).move();
        }
    }

//    метод getWinner
//    Проверить, что метод возвращает лошадь с самым большим значением distance.

    @Test
    void returnMaxDistanceHorse(){
        horses = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            horses.add(new Horse("Horse"+i, i+1, i+2));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(12, hippodrome.getWinner().getDistance());
    }
}