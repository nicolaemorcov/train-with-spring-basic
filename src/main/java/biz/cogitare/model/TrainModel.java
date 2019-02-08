package biz.cogitare.model;

import biz.cogitare.controller.ValidationUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "train")
public class TrainModel {
    @Id
    private final String id;
    private final String origin;
    private final String destination;

    private final LocalDateTime scheduledTimeDeparture;
    private final LocalDateTime createTime;


    public TrainModel(String id, String origin, String destination, LocalDateTime scheduledTimeDeparture) {
        this.id = ValidationUtils.isNotNullAndNotEmpty(id, "train ID should not be empty");
        this.origin = origin;
        this.destination = destination;
        this.scheduledTimeDeparture = scheduledTimeDeparture;
        this.createTime = LocalDateTime.now();
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
