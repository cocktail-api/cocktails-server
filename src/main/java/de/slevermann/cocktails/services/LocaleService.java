package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.LocaleDao;
import de.slevermann.cocktails.models.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional
@Validated
public class LocaleService {

    private final LocaleDao localeDao;

    public LocaleService(LocaleDao localeDao) {
        this.localeDao = localeDao;
    }

    public List<Locale> getAll() {
        return localeDao.getAll();
    }

}
