package biz.cogitare.service;

import biz.cogitare.dto.TrainDto;
import biz.cogitare.model.TrainModel;
import biz.cogitare.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public final class TrainServiceImpl implements TrainService {

    private TrainRepository trainRepository;

    @Autowired
    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    private TrainModel convertToTrainModel(TrainDto trainDto){
        TrainModel trainModel = new TrainModel(
                trainDto.getId(),
                trainDto.getOrigin(),
                trainDto.getDestination(),
                trainDto.getScheduledTimeDeparture()
        );
        return trainModel;
    }

    @Override
    public TrainDto getTrainById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        TrainModel trainModel = trainRepository.getTrainById(id);
        TrainDto trainDto = trainModel.converToTrainDto();
        return trainDto;
    }

    @Override
    public void createTrain(TrainDto trainDto) {
        trainRepository.save(convertToTrainModel(trainDto));
    }


}
