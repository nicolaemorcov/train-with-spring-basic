package biz.cogitare.dto;

import biz.cogitare.controller.ValidationUtils;
import biz.cogitare.model.TrainModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public final class TrainDto implements Serializable {



    private final String id;
    private final String origin;
    private final String destination;
    private final LocalDateTime scheduledTimeDeparture;
    private final LocalDateTime createTime;


    public TrainDto(String id, String origin, String destination, LocalDateTime scheduledTimeDeparture) {
        this.id = ValidationUtils.isNotNullAndNotEmpty(id, "train ID should not be empty");
        this.origin = ValidationUtils.isNotNullAndNotEmpty(origin, "origin Should not be empty");
        this.destination = ValidationUtils.isNotNullAndNotEmpty(destination, "destination should not be empty");
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

    public String getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getScheduledTimeDeparture() {
        return scheduledTimeDeparture;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }



}
