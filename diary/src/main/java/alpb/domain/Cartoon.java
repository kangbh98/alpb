package alpb.domain;

import alpb.DiaryApplication;
import alpb.domain.DiaryCreated;
import alpb.domain.DiaryDeleted;

import java.sql.Date;
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

    @Column(length = 1000)  // Length set to 1000 characters
    private String imageUrl;

    private String comment;

    @Embedded
    private cartoon_status diaryStatus;

    @PostPersist
    public void onPostPersist() {
        DiaryCreated diaryCreated = new DiaryCreated(this);
        diaryCreated.setDate(this.date);
        diaryCreated.setUserIdx(this.userIdx);
        diaryCreated.setImageUrl(this.imageUrl);
        diaryCreated.setComment(this.comment);
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

    public Cartoon(Date date, Long userIdx, String imageUrl, String comment) {
        System.out.println("지나침");
        this.date = date;
        this.userIdx = userIdx;
        this.imageUrl = imageUrl;
        this.comment = comment;
    }

    public Cartoon(){
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
