package ui;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import dao.DAOException;
import domain.Magazine;
import domain.Subscribe;
import dto.SubscribesDTO;
import service.MagazineService;
import service.SubscribeService;
import service.impl.MagazineServiceImpl;
import service.impl.SubscribeServiceImpl;

@WebServlet("/subscribes")
public class SubscribesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //декларуємо змінні MagazineService і SubscribeService
    private MagazineService magazineService = MagazineServiceImpl.getMagazineService();
    private SubscribeService subscribeService = SubscribeServiceImpl.getSubscribeService();
    //додаємо логер
    private Logger log = Logger.getLogger(SubscribesServlet.class);
//створення нового об’єкта SubscribesDTO.
    public List<SubscribesDTO> map(List<Subscribe> subscribes, Map<Integer, Magazine> magazinesMap) {
        return subscribes.stream().map(value -> {
            SubscribesDTO subscribesDTO = new SubscribesDTO();
          //  встановлює іd
            subscribesDTO.id = value.getId();
            //назву отриманої з Map magazinesMap.
            subscribesDTO.title = magazinesMap.get(value.getMagazineID()).getTitle();
            // опис отриманий з Map magazinesMap.
            subscribesDTO.description = magazinesMap.get(value.getMagazineID()).getDescription();
            // дату публікації
            subscribesDTO.publishDate = magazinesMap.get(value.getMagazineID()).getPublishDate();
          //  Ціна за підписку розраховується з допомогою subscribePrice і його subscribeStatus.
            subscribesDTO.subscribePrice = magazinesMap.get(value.getMagazineID()).getSubscribePrice();
            //статус підписки
            subscribesDTO.subscribeStatus = value.getSubscribeStatus();
            subscribesDTO.subscribeDate = value.getSubscribeDate();
            //Період підпски, визначається методами getSubscribePeriod() і getSubscribeDate().
            subscribesDTO.subscribePeriod = value.getSubscribePeriod();
//Після призначення значень повертається список підписок.
            return subscribesDTO;
        }).collect(Collectors.toList());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Subscribe> subscribes = null;

        try {
            log.trace("Getting list of subscribes from database...");
            //отримання списку subscribe із бази даних
            subscribes = subscribeService.readAll();
            log.warn( subscribes = subscribeService.readAll());
        } catch (DAOException e) {
            log.error("Getting list of subscribes failed!", e);
        }

        Map<Integer, Magazine> magazinesMap = null;

        try {
            log.trace("Getting map of magazines from database...");
            //отримання magazinesMap
            magazinesMap = magazineService.readAllMap();
            log.warn(  magazinesMap = magazineService.readAllMap());
        } catch (DAOException e) {
            log.error("Getting map of magazines failed!", e);
        }
//створення масиву об’єктів subscribesDTO, які мають ключі до id magazinesMap.
        List<SubscribesDTO> subscribesDTO = map(subscribes, magazinesMap);

        if (subscribes == null || magazinesMap == null) {
            log.warn("There is no any magazine or subscribe in database registered!");
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("There are no magazines in database!");
        } else {
            log.trace("Returning list of subscribes DTO...");
            String json = new Gson().toJson(subscribesDTO);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

}