package alpb.domain;

import alpb.domain.*;
import alpb.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DiaryCreated extends AbstractEvent {

    private Long diaryId;
    private Date date;
    private Long userIdx;
    private String imageUrl;
    private String comment;
}
