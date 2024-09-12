package alpb.domain;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PlanService {
    
    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository){
        this.planRepository =planRepository;
    }

    public void createPlan(Plan plan){
        planRepository.save(plan);
    }

    public List<Plan> getPlan(int userIdx) {
        return planRepository.findAllByUserIdx(userIdx);
    }

    public List<Plan> getDatePlan(int userIdx, String date) {
        return planRepository.findAllByUserIdxAndDate(userIdx, date);
    }
}
