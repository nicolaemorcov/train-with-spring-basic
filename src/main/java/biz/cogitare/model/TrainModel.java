package biz.cogitare.model;

import biz.cogitare.controller.ValidationUtils;
import biz.cogitare.dto.TrainDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "train")
public final class TrainModel {
    @Id
    private final String id;
    private final String origin;
    private final String destination;
    private final LocalDateTime scheduledTimeDeparture;
    private final LocalDateTime createTime;


    public TrainModel(String id, String origin, String destination, LocalDateTime scheduledTimeDeparture) {
        this.id = ValidationUtils.isNotNullAndNotEmpty(id, "train ID should not be empty");
        this.origin = ValidationUtils.isNotNullAndNotEmpty(origin, "origin Should not be empty");
        this.destination = ValidationUtils.isNotNullAndNotEmpty(destination, "destination should not be empty");
        this.scheduledTimeDeparture = scheduledTimeDeparture;
        this.createTime = LocalDateTime.now();
    }

    public TrainDto converToTrainDto(){
        TrainDto trainDto = new TrainDto(
                this.id,
                this.origin,
                this.destination,
                this.scheduledTimeDeparture
        );
                return trainDto;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", scheduledTimeDeparture=" + scheduledTimeDeparture +
                ", createTime=" + createTime +
                '}';
    }

}
