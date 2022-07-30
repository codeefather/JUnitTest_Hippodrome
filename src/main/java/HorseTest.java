
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

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
//
//    Проверить, что при передаче в конструктор первым параметром null,
//      будет выброшено IllegalArgumentException. Для этого нужно воспользоваться методом assertThrows;

    @Test
    public void firstParamNotNull(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 10, 10)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

//    Проверить, что при передаче в конструктор первым параметром пустой строки или строки содержащей
//        только пробельные символы (пробел, табуляция и т.д.),
//            будет выброшено IllegalArgumentException.
//            Чтобы выполнить проверку с разными вариантами пробельных символов,
//                нужно сделать тест параметризованным


    @ParameterizedTest
    @ValueSource(strings = { "", " ", "     "})
    public void firstArgIsSpace(String argument) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(argument, 10, 10)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

//    Проверить, что при передаче в конструктор вторым параметром отрицательного числа,
//      будет выброшено IllegalArgumentException;

    @Test
    public void secondArgIsNegative(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Буцефал", -1, 10)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

//    Проверить, что при передаче в конструктор третьим параметром отрицательного числа,
//        будет выброшено IllegalArgumentException;

    @Test
    public void thirdArgIsNegative(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Марриот", 10, -1)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    public void getName() {
        String testName = "John";
        Horse horse = new Horse(testName, 10);
        assertEquals(testName, horse.getName());
    }

    @Test
    public void getSpeed() {
        double testSpeed = 22;
        Horse horse = new Horse("John", testSpeed);
        assertEquals(testSpeed, horse.getSpeed());
    }

//    Проверить, что метод возвращает число, которое было передано третьим параметром в конструктор;
//    Проверить, что метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами;

    @Test
    public void getDistance() {
        double testDistance = 25;
        Horse horse1 = new Horse("John", 10, testDistance);
        Horse horse2 = new Horse("John", 17);
        assertEquals(testDistance, horse1.getDistance());
        assertEquals(0, horse2.getDistance());
    }

//    - Проверить, что метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9.
//      Для этого нужно использовать MockedStatic и его метод verify;
//    - Проверить, что метод присваивает дистанции значение высчитанное по формуле:
//        distance + speed * getRandomDouble(0.2, 0.9). Для этого нужно замокать getRandomDouble,
//        чтобы он возвращал определенные значения, которые нужно задать параметризовав тест.

    @ExtendWith(MockitoExtension.class)
    @Test
    public void verifyTestMove() {
        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("Марриот", 10, 10);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }


}