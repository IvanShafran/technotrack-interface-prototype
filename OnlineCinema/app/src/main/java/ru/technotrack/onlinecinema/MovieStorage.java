package ru.technotrack.onlinecinema;

import java.util.HashMap;
import java.util.Map;

public class MovieStorage {
    public static Map<Integer, Movie> movies;

    public static void init() {
        movies = new HashMap<>();

        Movie movie = new Movie();
        movie.addTag(Movie.TAG.DRAMA);
        movie.addTag(Movie.TAG.HOT);
        movie.setFree(false);
        movie.setPrice("10$");
        movie.setActors("Питер Динклэйдж, Лина Хиди, Эмилия Кларк, ...");
        movie.setCountry("США");
        movie.setInfo("К концу подходит время благоденствия, и лето, длившееся почти десятилетие," +
                " угасает. " +
                "Вокруг средоточия власти Семи королевств, Железного трона, зреет заговор, " +
                "и в это непростое время король решает искать поддержки " +
                "у друга юности Эддарда Старка. ");

        movie.setName("Игра престолов");
        movie.setPicture(R.drawable.game_pic);

        movies.put(0, movie);

        movie = new Movie();
        movie.addTag(Movie.TAG.COMEDY);
        movie.addTag(Movie.TAG.HOT);
        movie.setFree(true);
        movie.setActors("Александр Демьяненко, " +
                "Юрий Никулин, " +
                "Евгений Моргунов, " +
                "Георгий Вицин, ...");
        movie.setCountry("СССР");
        movie.setInfo("Фильм состоит из трех новелл, объединенных фигурой главного героя Шурика, " +
                "попадающего в самые невероятные ситуации.");

        movie.setName("Операция «Ы»");
        movie.setPicture(R.drawable.shourik);

        movies.put(1, movie);

        movie = new Movie();
        movie.addTag(Movie.TAG.DRAMA);
        movie.addTag(Movie.TAG.HOT);
        movie.setFree(false);
        movie.setPrice("3$");
        movie.setActors("Вигго Мортенсен, " +
                "Шарлиз Терон, " +
                "Гай Пирс, " +
                "Роберт Дювалл ...");
        movie.setCountry("США");
        movie.setInfo("На Землю обрушились чудовищные катаклизмы, цивилизация уничтожена, " +
                "как и практически вся жизнь на планете. Оставшееся человечество разделилось " +
                "на каннибалов и их добычу. По дороге, покрытой пеплом, идут отец с сыном. " +
                "Они хотят добраться до теплых мест, чтобы выжить…");

        movie.setName("Дорога");
        movie.setPicture(R.drawable.road);

        movies.put(3, movie);

        movie = new Movie();
        movie.addTag(Movie.TAG.DRAMA);
        movie.setFree(true);
        movie.setActors("");
        movie.setCountry("США");
        movie.setInfo("Крис Гарднер — отец-одиночка. Воспитывая пятилетнего сына, " +
                "Крис изо всех сил старается сделать так, чтобы ребенок рос счастливым. " +
                "Работая продавцом, он не может оплатить квартиру, и их выселяют.");

        movie.setName("В погоне \nза счастьем");
        movie.setPicture(R.drawable.happy);

        movies.put(4, movie);

        movie = new Movie();
        movie.addTag(Movie.TAG.DRAMA);
        movie.setFree(true);
        movie.setActors("");
        movie.setCountry("США");
        movie.setInfo("Крис Гарднер — отец-одиночка. Воспитывая пятилетнего сына, " +
                "Крис изо всех сил старается сделать так, чтобы ребенок рос счастливым. " +
                "Работая продавцом, он не может оплатить квартиру, и их выселяют.");

        movie.setName("В погоне за счастьем");
        movie.setPicture(R.drawable.happy);

        movies.put(4, movie);

        movie = new Movie();
        movie.addTag(Movie.TAG.COMEDY);
        movie.setFree(true);
        movie.setActors("Одри Тоту, " +
                "Матьё Кассовиц, " +
                "Рюфюс, ...");
        movie.setCountry("Франция");
        movie.setInfo("Знаете ли вы, что все события, происходящие в нашем мире, " +
                "даже самые незначительные, взаимосвязаны самым удивительным и чудесным образом?");

        movie.setName("Амели");
        movie.setPicture(R.drawable.ameli);

        movies.put(5, movie);

        movie = new Movie();
        movie.addTag(Movie.TAG.COMEDY);
        movie.setFree(false);
        movie.setPrice("5$");
        movie.setActors("Джаред Лето, " +
                "Сара Полли, " +
                "Дайан Крюгер, ...");
        movie.setCountry("Бельгия");
        movie.setInfo("Проснувшийся немощным стариком Немо Никто оказывается последним " +
                "смертным в гротескном будущем. Все люди уже давно бессмертны и с удовольствием " +
                "наблюдают за телешоу, где главная звезда — дряхлый и безумный старик Немо, " +
                "доживающий свои последние дни. Накануне конца к нему приходит журналист, " +
                "и Немо рассказывает ему свою историю перескакивая из одной жизни в другую, " +
                "параллельную, несколько раз за рассказ успев умереть.");

        movie.setName("Господин Никто");
        movie.setPicture(R.drawable.nobody);

        movies.put(6, movie);

        movie = new Movie();
        movie.addTag(Movie.TAG.COMEDY);
        movie.addTag(Movie.TAG.HOT);
        movie.setFree(false);
        movie.setPrice("7$");
        movie.setActors("Джиннифер Гудвин, " +
                "Джейсон Бейтман, " +
                "Идрис Эльба, ...");
        movie.setCountry("США");
        movie.setInfo("Добро пожаловать в Зверополис — современный город, населенный самыми разными" +
                " животными, от огромных слонов до крошечных мышек. Зверополис разделен на районы, " +
                "полностью повторяющие естественную среду обитания разных жителей — здесь есть " +
                "и элитный район Площадь Сахары и неприветливый Тундратаун.");

        movie.setName("Зверополис");
        movie.setPicture(R.drawable.zver);

        movies.put(7, movie);

        movie = new Movie();
        movie.addTag(Movie.TAG.DRAMA);
        movie.setFree(true);
        movie.setActors("Джаред Лето, " +
                "Дженнифер Коннелли, " +
                "Марлон Уайанс ...");
        movie.setCountry("США");
        movie.setInfo("Каждый из главных героев фильма стремился к своей заветной мечте. " +
                "Сара Голдфарб мечтала сняться в известном телешоу, " +
                "ее сын Гарольд со своим другом Тайроном — сказочно разбогатеть, " +
                "подруга Гарольда Мэрион грезила о собственном модном магазине, " +
                "но на их пути были всяческие препятствия." +
                " События фильма разворачиваются стремительно, герои погрязли в наркотиках.");

        movie.setName("Реквием по мечте");
        movie.setPicture(R.drawable.dream);

        movies.put(8, movie);


    }
}
