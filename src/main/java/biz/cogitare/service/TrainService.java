package biz.cogitare.service;

import biz.cogitare.dto.TrainDto;
import biz.cogitare.model.TrainModel;


public interface TrainService {
    TrainDto getTrainById(String id);
    public void createTrain(TrainDto trainDto);
}
