package alpb.domain;

import alpb.domain.*;
import alpb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DiaryCreated extends AbstractEvent {

    private Long diaryId;
    private Date date;
    private Long userIdx;
    private String imageUrl;
    private String comment;

    public DiaryCreated(Cartoon aggregate) {
        super(aggregate);
    }

    public DiaryCreated() {
        super();
    }
}
//>>> DDD / Domain Event
