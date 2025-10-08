package ru.storage.service;

import io.spring.guides.gs_producing_web_service.XmlTrain;
import org.springframework.stereotype.Service;
import ru.storage.entity.Train;

@Service
public class TrainMapping {
    public XmlTrain trainToXmlTrain(Train train) {
        XmlTrain xmlTrain = new XmlTrain();
        xmlTrain.setId(train.getId());
        xmlTrain.setSeriesName(train.getNameSeries());
        xmlTrain.setTrainNumber(train.getTrainNumber());
        xmlTrain.setDataBuild(train.getDataBuild().toString());
        xmlTrain.setBuildingMileage(train.getBuildingMileage());
        xmlTrain.setLastRepair(train.getLastRepair());
        xmlTrain.setCondition(train.getCondition());
        xmlTrain.setImageUrl(train.getImageUrl());

        return xmlTrain;
    }
}
