package alpb.domain;

import alpb.domain.*;

import java.util.Date;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "cartoons", path = "cartoons")
public interface CartoonRepository
    extends PagingAndSortingRepository<Cartoon, Long> {

    Cartoon findByUserIdxAndDate(Long userIdx, Date date);}
