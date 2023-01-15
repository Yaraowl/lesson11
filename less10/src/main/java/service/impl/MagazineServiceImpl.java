package service.impl;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import dao.DAOException;
import dao.MagazineDAO;
import dao.impl.MagazineDAOImpl;
import domain.Magazine;
import service.MagazineService;


public class MagazineServiceImpl implements MagazineService {
    private static MagazineService magazineService;
    private MagazineDAO magazineDAO;

    private MagazineServiceImpl() {
        this.magazineDAO = new MagazineDAOImpl();
    }

    public static MagazineService getMagazineService() {
        if (magazineService == null) {
            magazineService = new MagazineServiceImpl();
        }

        return magazineService;
    }

    @Override
    public Magazine insert(Magazine t) throws DAOException {
        return magazineDAO.insert(t);
    }

    @Override
    public List<Magazine> readAll() throws DAOException {
        return magazineDAO.readAll();
    }

    @Override
    public Magazine readByID(int id) throws DAOException {
        return magazineDAO.readByID(id);
    }

    @Override
    public boolean updateByID(Magazine t) throws DAOException {
        return magazineDAO.updateByID(t);
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return magazineDAO.delete(id);
    }

    @Override
    public Map<Integer, Magazine> readAllMap() throws DAOException {
        List<Magazine> magazines = readAll();
        return magazines.stream().collect(Collectors.toMap(Magazine::getId, Function.identity()));
    }
}
