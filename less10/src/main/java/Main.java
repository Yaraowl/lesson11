import domain.Magazine;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import dao.DAOException;
import service.MagazineService;
import service.impl.MagazineServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws DAOException {
        Logger log = Logger.getLogger(Main.class);
        Map<Integer, Magazine> magazinesMap = new HashMap<>();
        for (Map.Entry entry : magazinesMap.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());


        }
    }
}

