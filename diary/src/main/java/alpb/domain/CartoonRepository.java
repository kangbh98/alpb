package alpb.domain;

import alpb.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "cartoons", path = "cartoons")
public interface CartoonRepository
    extends PagingAndSortingRepository<Cartoon, Long> {}
