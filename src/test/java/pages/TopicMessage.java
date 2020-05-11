package pages;

import java.util.Random;

public class TopicMessage {
    //additional class with method to pick a random message

    /**
     * array with quotes
     * @return random value from the array
     */
    public static String PickMessage() {
        String array[] = new String[7];
        array[0] = "Be yourself; everyone else is already taken. (O.Wilde)";
        array[1] = "We are all in the gutter, but some of us are looking at the stars. (O.Wilde)";
        array[2] = "To live is the rarest thing in the world. Most people exist, that is all. (O.Wilde)";
        array[3] = "If one cannot enjoy reading a book over and over again, there is no use in reading it at all. (O.Wilde)";
        array[4] = "The truth is rarely pure and never simple. (O.Wilde)";
        array[5] = "Never love anyone who treats you like you're ordinary. (O.Wilde)";
        array[6] = "A good friend will always stab you in the front. (O.Wilde)";
        Random generator = new Random(); //это для получения случайного значения из array
        int randomIndex = generator.nextInt(array.length);//находим случайное значение для индекса из диапазона (длина массива)
        return array[randomIndex]; //возвращаем строку под этим индексом в массиве
    }
}
