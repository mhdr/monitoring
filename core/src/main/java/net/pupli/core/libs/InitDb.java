package net.pupli.core.libs;

import net.pupli.core.models.*;

import java.util.ArrayList;
import java.util.Objects;

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

    public void initPrevRealData() {
        MyContext.prevRealDataRepository.deleteAll();

        var dataList = new ArrayList<PrevRealData>();

        MyContext.myCache.getItems().forEach((s, monitoringItem) -> {
            if (monitoringItem.getItemType() == 2) {
                var newData = new PrevRealData(monitoringItem.getId());
                dataList.add(newData);
            }
        });

        MyContext.prevRealDataRepository.saveAll(dataList);
    }

    public void initPrevBooleanData() {
        MyContext.prevBooleanDataRepository.deleteAll();

        var dataList = new ArrayList<PrevBooleanData>();

        MyContext.myCache.getItems().forEach((s, monitoringItem) -> {
            if (monitoringItem.getItemType() == 1) {
                var newData = new PrevBooleanData(monitoringItem.getId());
                dataList.add(newData);
            }
        });

        MyContext.prevBooleanDataRepository.saveAll(dataList);
    }

    public void initAlarmStatusReal() {
        var dataList = MyContext.alarmStatusRealRepository.findAll();
        var alarms = MyContext.alarmRealRepository.findAll();

        alarms.forEach(alarmReal -> {

            var alarmStatus = dataList.stream().filter(x -> Objects.equals(x.getAlarmId(), alarmReal.getId())).findFirst();

            if (alarmStatus.isEmpty()) {
                var alarmStatusValue = new AlarmStatusReal(alarmReal.getId());
                dataList.add(alarmStatusValue);
            }
        });

        MyContext.alarmStatusRealRepository.saveAll(dataList);
    }
}
