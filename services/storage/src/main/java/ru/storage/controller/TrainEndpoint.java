package ru.storage.controller;

import io.spring.guides.gs_producing_web_service.GetTrainRequest;
import io.spring.guides.gs_producing_web_service.GetTrainResponse;
import io.spring.guides.gs_producing_web_service.XmlTrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.storage.entity.Train;
import ru.storage.repository.TrainRepository;
import ru.storage.service.TrainMapping;

import java.util.Optional;


@Endpoint
public class TrainEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private TrainRepository trainRepository;
    private TrainMapping trainMapping;

    @Autowired
    public TrainEndpoint(TrainRepository trainRepository, TrainMapping trainMapping) {
        this.trainRepository = trainRepository;
        this.trainMapping = trainMapping;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTrainRequest")
    @ResponsePayload
    public GetTrainResponse getTrainResponse(@RequestPayload GetTrainRequest request) {
        GetTrainResponse response = new GetTrainResponse();
        long trainNumber = request.getTrainNumber();
        Optional<Train> byTrainNumber = trainRepository.findByTrainNumber(trainNumber);
        XmlTrain xmlTrain;
        if (byTrainNumber.isPresent()) {
            xmlTrain = trainMapping.trainToXmlTrain(byTrainNumber.get());
        } else {
            xmlTrain = new XmlTrain();
        }
        response.setTrain(xmlTrain);

        return response;
    }
}