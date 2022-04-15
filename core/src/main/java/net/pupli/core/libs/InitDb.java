package net.pupli.core.libs;

import net.pupli.core.models.*;

import java.util.ArrayList;
import java.util.List;

public class InitDb {

    public void initRawRealData() {
        MyContext.rawRealDataRepository.deleteAll();
    }

    public void initRawBooleanData() {
        MyContext.rawBooleanDataRepository.deleteAll();
    }

    public void initRealData() {
        MyContext.realDataRepository.deleteAll();

        var realDataList = new ArrayList<RealData>();

        MyContext.myCache.getItems().forEach((s, monitoringItem) -> {
            // process only real data type
            if (monitoringItem.getItemType() == 2) {
                RealData realData = new RealData(monitoringItem.getId());
                realDataList.add(realData);
            }
        });

        MyContext.realDataRepository.saveAll(realDataList);
    }

    public void initBooleanData() {
        MyContext.booleanDataRepository.deleteAll();

        var booleanDataList = new ArrayList<BooleanData>();

        MyContext.myCache.getItems().forEach((s, monitoringItem) -> {
            // process only boolean data type
            if (monitoringItem.getItemType() == 1) {
                BooleanData booleanData = new BooleanData(monitoringItem.getId());
                booleanDataList.add(booleanData);
            }
        });

        MyContext.booleanDataRepository.saveAll(booleanDataList);
    }
}
