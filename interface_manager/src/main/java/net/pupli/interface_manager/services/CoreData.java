package net.pupli.interface_manager.services;

import com.google.gson.Gson;
import net.pupli.interface_manager.dto.RequestItemsDto;
import net.pupli.interface_manager.dto.ResponseItemsDto;
import net.pupli.interface_manager.libs.ConfigFile;
import net.pupli.interface_manager.libs.MyContext;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;

@Component
public class CoreData implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(CoreData.class);

    @Override
    public void run(String... args) throws Exception {
        try {
            ConfigFile configFile = new ConfigFile();
            String url = configFile.getCoreWebAddress() + "/api/interface/items";

            RequestItemsDto requestDto = new RequestItemsDto();
            Gson gson = new Gson();
            String json = gson.toJson(requestDto);

            MediaType mediaType = MediaType.get("application/json;");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(json, mediaType);

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String responseStr = response.body().string();
            ResponseItemsDto responseDto = gson.fromJson(responseStr, ResponseItemsDto.class);
            for (ResponseItemsDto.Item item :
                    responseDto.getItems()) {
                MyContext.myCache.getItems().put(item.getItemId(), item);
            }

            logger.info(responseStr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
