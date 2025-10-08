package ru.user_service.rest;

import io.spring.guides.gs_producing_web_service.GetTrainRequest;
import io.spring.guides.gs_producing_web_service.GetTrainResponse;
import io.spring.guides.gs_producing_web_service.XmlTrain;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.user_service.dto.Train;
import ru.user_service.service.ProducerService;
import ru.user_service.service.TrainMapping;


@Endpoint
public class TrainEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private final ProducerService producerService;
    private final TrainMapping mapping;

    public TrainEndpoint(ProducerService producerService, TrainMapping mapping) {
        this.producerService = producerService;
        this.mapping = mapping;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTrainRequest")
    @ResponsePayload
    public GetTrainResponse getTrainResponse(@RequestPayload GetTrainRequest request) throws InterruptedException {
        GetTrainResponse response = new GetTrainResponse();
        String trainNumber = String.valueOf(request.getTrainNumber());
        Train train = producerService.sendRequestToStorage(trainNumber);

        XmlTrain xmlTrain;
        if (train != null) {
            xmlTrain = mapping.trainToXmlTrain(train);
        } else {
            xmlTrain = new XmlTrain();
        }
        response.setTrain(xmlTrain);

        return response;
    }
}