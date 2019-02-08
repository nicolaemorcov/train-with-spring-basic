package biz.cogitare.repository;

import biz.cogitare.model.TrainModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends MongoRepository<TrainModel, String> {

    TrainModel getTrainById(String id);

}
