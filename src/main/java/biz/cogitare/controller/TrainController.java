package biz.cogitare.controller;

import biz.cogitare.model.TrainModel;
import biz.cogitare.repository.TrainRepository;
import biz.cogitare.service.TrainServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TrainController {

    TrainServiceImpl trainService;

    public TrainController(TrainServiceImpl trainService) {
        this.trainService = trainService;
    }

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TrainModel getTrain(@PathVariable String id) {
        LOG.info("Getting train with ID: {}.", id);
        TrainModel train = trainService.getTrainById(id);
        LOG.info(train.toString());

        return train;
    }

}
