package service;

import dao.DAOAbstractCRUD;
import dao.DAOException;
import domain.Magazine;

import java.util.Map;

public interface MagazineService extends DAOAbstractCRUD<Magazine> {
    public Map<Integer, Magazine> readAllMap() throws DAOException;

}
