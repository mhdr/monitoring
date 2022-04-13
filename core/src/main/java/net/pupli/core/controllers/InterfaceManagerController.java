package net.pupli.core.controllers;

import net.pupli.core.dto.RequestItemsDto;
import net.pupli.core.dto.ResponseItemsDto;
import net.pupli.core.libs.MyContext;
import net.pupli.core.models.MonitoringItem;
import net.pupli.core.services.RabbitMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class InterfaceManagerController {
    Logger logger = LoggerFactory.getLogger(InterfaceManagerController.class);

    @RequestMapping(value = "/api/interface/test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseItemsDto test(HttpServletRequest request, HttpServletResponse response, @RequestBody RequestItemsDto requestDto) {
        try {

            ResponseItemsDto responseDto = new ResponseItemsDto();

            List<MonitoringItem> items = MyContext.monitoringItemRepository.findAll();

            for (MonitoringItem item : items) {
                responseDto.getItems().add(new ResponseItemsDto.Item(item.getId(), item.getItemId(), item.getQos()));
            }

            return responseDto;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/api/interface/items", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseItemsDto items(HttpServletRequest request, HttpServletResponse response) {
        try {

            ResponseItemsDto responseDto = new ResponseItemsDto();

            List<MonitoringItem> items = MyContext.monitoringItemRepository.findAll();

            for (MonitoringItem item : items) {
                responseDto.getItems().add(new ResponseItemsDto.Item(item.getId(), item.getItemId(), item.getQos()));
            }

            return responseDto;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
