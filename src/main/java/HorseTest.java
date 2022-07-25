import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

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
    void firstArgIsSpace(String argument) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(argument, 10, 10)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

//    Проверить, что при передаче в конструктор вторым параметром отрицательного числа,
//      будет выброшено IllegalArgumentException;

    @Test
    void secondArgIsNegative(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Буцефал", -1, 10)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

//    Проверить, что при передаче в конструктор третьим параметром отрицательного числа,
//        будет выброшено IllegalArgumentException;

    @Test
    void thirdArgIsNegative(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Марриот", -1, 10)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    void getName() {
    }

    @Test
    void getSpeed() {
    }

    @Test
    void getDistance() {
    }

    @Test
    void move() {
    }


}