package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.time.LocalDate;
import java.util.List;

@Service
public class FilmService extends BaseService<Film, FilmStorage> {

    @Autowired
    public FilmService(FilmStorage storage) {
        super(storage);
    }

    @Override
    protected void validate(Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.parse("1895-12-25")))
            throw new ValidationException("Дата релиза должна быть после 28 декабря 1895 года", String.valueOf(film.getReleaseDate()));
    }

    public void addLike(Long id, Long userId) {
        baseValidate(id);
        baseValidate(userId);
        storage.addLike(id, userId);
    }

    public void deleteLike(Long id, Long userId) {
        baseValidate(id);
        baseValidate(userId);
        storage.deleteLike(id, userId);
    }

    public List<Film> getPopularFilms(Integer count) {
        return storage.getPopularFilms(count);
    }

}
