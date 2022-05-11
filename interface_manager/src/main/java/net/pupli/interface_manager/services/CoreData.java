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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class CoreData implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(CoreData.class);

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    boolean isSuccessful = false;

    @Override
    public void run(String... args) throws Exception {

        // why we need rescheduling?
        // because this app should run after core app, because we need some data from it
        // we could simply introduce core app as interface manager dependency in systemd service file,
        // but we can't do that because it's possible that core and interface manager are not running on the same server,
        // so it's required because of scalability and distributed nature of the app

        executorService.execute(() -> {
            try {
                while (!isSuccessful) {
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
                        for (ResponseItemsDto.Item item : responseDto.getItems()) {
                            MyContext.myCache.getItems().put(item.getItemId(), item);
                        }

                        for (String credential : responseDto.getCredentials()) {
                            MyContext.myCache.getCredentials().add(credential);
                        }

                        // make this variable true to exit the while loop
                        isSuccessful = true;
                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                    } finally {
                        // reschedule every 1 second
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        });
    }
}
