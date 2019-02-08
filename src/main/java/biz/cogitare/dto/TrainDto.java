package biz.cogitare.dto;

import java.time.LocalDateTime;

public class TrainDto {

    private final String id;
    private final String origin;
    private final String destination;

    private final LocalDateTime scheduledTimeDeparture;
    private final LocalDateTime createTime;


    public TrainDto(String id, String origin, String destination, LocalDateTime scheduledTimeDeparture) {
        this.id = id;
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
