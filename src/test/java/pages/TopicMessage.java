package pages;

import java.util.Random;
//additional class with method to pick a random message
public class TopicMessage {

public static Random generator = new Random();//это для получения случайного значения из array
    /**
     * array with quotes
     * @return random value from the array
     */
    public static String PickMessage() {
        String array[] = {
        "Be yourself; everyone else is already taken. (O.Wilde)",
        "We are all in the gutter, but some of us are looking at the stars. (O.Wilde)",
        "To live is the rarest thing in the world. Most people exist, that is all. (O.Wilde)",
        "If one cannot enjoy reading a book over and over again, there is no use in reading it at all. (O.Wilde)",
        "The truth is rarely pure and never simple. (O.Wilde)",
        "Never love anyone who treats you like you're ordinary. (O.Wilde)",
        "A good friend will always stab you in the front. (O.Wilde)"};
        int randomIndex = generator.nextInt(array.length);//находим случайное значение для индекса из диапазона (длина массива)
        return array[randomIndex]; //возвращаем строку под этим индексом в массиве
    }
}
