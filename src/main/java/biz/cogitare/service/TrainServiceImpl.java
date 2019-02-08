package biz.cogitare.service;

import biz.cogitare.dto.TrainDto;
import biz.cogitare.model.TrainModel;
import biz.cogitare.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class TrainServiceImpl {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Autowired
//    TrainDto trainDto;

    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public TrainModel getTrainById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, TrainModel.class);
    }


}
