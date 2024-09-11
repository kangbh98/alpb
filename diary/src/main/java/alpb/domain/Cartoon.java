package alpb.domain;

import alpb.DiaryApplication;
import alpb.domain.DiaryCreated;
import alpb.domain.DiaryDeleted;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Cartoon_table")
@Data
//<<< DDD / Aggregate Root
public class Cartoon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long diaryId;

    private Date date;

    private Long userIdx;

    private String imageUrl;

    private String comment;

    @Embedded
    private cartoon_status diaryStatus;

    @PostPersist
    public void onPostPersist() {
        DiaryCreated diaryCreated = new DiaryCreated(this);
        diaryCreated.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        DiaryDeleted diaryDeleted = new DiaryDeleted(this);
        diaryDeleted.publishAfterCommit();
    }

    public static CartoonRepository repository() {
        CartoonRepository cartoonRepository = DiaryApplication.applicationContext.getBean(
            CartoonRepository.class
        );
        return cartoonRepository;
    }

    //<<< Clean Arch / Port Method
    public void deleteDiary() {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void deleteDiary(CalendarDeleted calendarDeleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Cartoon cartoon = new Cartoon();
        repository().save(cartoon);

        DiaryDeleted diaryDeleted = new DiaryDeleted(cartoon);
        diaryDeleted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(calendarDeleted.get???()).ifPresent(cartoon->{
            
            cartoon // do something
            repository().save(cartoon);

            DiaryDeleted diaryDeleted = new DiaryDeleted(cartoon);
            diaryDeleted.publishAfterCommit();

         });
        */

    }

    public Cartoon(java.sql.Date date2, int userIdx2, String imageUrl2, String comment2) {
        //TODO Auto-generated constructor stub
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
