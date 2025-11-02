package ru.user_service.service;

import io.spring.guides.gs_producing_web_service.XmlTrain;
import org.springframework.stereotype.Service;
import ru.user_service.dto.Train;

@Service
public class TrainMapping {
    public final XmlTrain trainToXmlTrain(Train train) {
        XmlTrain xmlTrain = new XmlTrain();
        xmlTrain.setId(train.getId());
        xmlTrain.setSeriesName(train.getSeriesName());
        xmlTrain.setTrainNumber(train.getTrainNumber());
        xmlTrain.setDataBuild(train.getDataBuild());
        xmlTrain.setBuildingMileage(train.getBuildingMileage());
        xmlTrain.setLastRepair(train.getLastRepair());
        xmlTrain.setCondition(train.getCondition());
        xmlTrain.setImageUrl(train.getImageUrl());

        return xmlTrain;
    }
}
