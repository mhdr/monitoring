package net.pupli.core.libs;

import net.pupli.core.models.*;

import java.util.ArrayList;

public class InitDb {

    public void initRawRealData() {
        MyContext.rawRealDataRepository.deleteAll();
    }

    public void initRawBooleanData() {
        MyContext.rawBooleanDataRepository.deleteAll();
    }

    public void initFinalRealData() {
        MyContext.finalRealDataRepository.deleteAll();

        var realDataList = new ArrayList<FinalRealData>();

        MyContext.myCache.getItems().forEach((s, monitoringItem) -> {
            // process only real data type
            if (monitoringItem.getItemType() == 2) {
                FinalRealData finalRealData = new FinalRealData(monitoringItem.getId());
                realDataList.add(finalRealData);
            }
        });

        MyContext.finalRealDataRepository.saveAll(realDataList);
    }

    public void initPrevRealData() {
        MyContext.prevRealDataSavedRepository.deleteAll();

        var dataList = new ArrayList<PrevRealDataSaved>();

        MyContext.myCache.getItems().forEach((s, monitoringItem) -> {
            if (monitoringItem.getItemType() == 2) {
                PrevRealDataSaved newData = new PrevRealDataSaved(monitoringItem.getId());
                dataList.add(newData);
            }
        });

        MyContext.prevRealDataSavedRepository.saveAll(dataList);
    }

    public void initFinalBooleanData() {
        MyContext.finalBooleanDataRepository.deleteAll();

        var booleanDataList = new ArrayList<FinalBooleanData>();

        MyContext.myCache.getItems().forEach((s, monitoringItem) -> {
            // process only boolean data type
            if (monitoringItem.getItemType() == 1) {
                FinalBooleanData finalBooleanData = new FinalBooleanData(monitoringItem.getId());
                booleanDataList.add(finalBooleanData);
            }
        });

        MyContext.finalBooleanDataRepository.saveAll(booleanDataList);
    }
}
