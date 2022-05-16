package net.pupli.core.controllers;

import net.pupli.core.dto.ResponseItemsDto;
import net.pupli.core.libs.MyContext;
import net.pupli.core.models.InterfaceCredential;
import net.pupli.core.models.MonitoringItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class InterfaceController {
    Logger logger = LoggerFactory.getLogger(InterfaceController.class);

    @RequestMapping(value = "/api/interface/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseItemsDto items(HttpServletRequest request, HttpServletResponse response) {
        try {

            ResponseItemsDto responseDto = new ResponseItemsDto();

            List<MonitoringItem> items = MyContext.monitoringItemRepository.findAll();

            for (MonitoringItem item : items) {
                responseDto.getItems().add(new ResponseItemsDto.Item(item.getId(), item.getItemId(), item.getQos()));
            }

            // find valid credentials

            List<InterfaceCredential> credentials = MyContext.interfaceCredentialRepository.findAll();

            for (InterfaceCredential credential : credentials) {
                if (credential.getIsDisabled()) {
                    continue;
                }

                if (credential.getValidUntil().isBeforeNow()) {
                    continue;
                }

                responseDto.getCredentials().add(credential.getCredential());
            }

            return responseDto;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
