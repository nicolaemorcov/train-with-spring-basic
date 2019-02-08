package biz.cogitare.repository;

import biz.cogitare.dto.TrainDto;
import biz.cogitare.model.TrainModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepository extends MongoRepository<TrainModel, String> {

    TrainModel getTrainById(String id);

 //   TrainModel findByOrigin(String origin);

    List<TrainModel> findByOrigin(String origin);

    TrainModel findByOriginAndDestination(String origin);

  // public void createTrain(TrainModel trainModel);

}
