package alpb.domain;

import alpb.domain.*;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "plans", path = "plans")
public interface PlanRepository
    extends PagingAndSortingRepository<Plan, Long> {
    List<Plan> findAllByUserIdx(Integer userIdx); 
    void createPlan(Plan plan);
    List<Plan> findAllByUserIdxAndDate(int userIdx, String date);}
