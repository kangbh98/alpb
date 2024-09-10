package alpb.infra;

import alpb.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/plans")
@Transactional
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    PlanRepository planRepository;
    @Autowired
    PlanService planService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPlan(@RequestBody Plan plan) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.CREATED;
        try {
            planService.createPlan(plan);
            status = HttpStatus.OK;
            resultMap.put("status", status);
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/{userIdx}")
    public ResponseEntity<Map<String, Object>> getPlan(@PathVariable int userIdx) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            List<Plan> plan = planService.getPlan(userIdx);
            status = HttpStatus.OK;
            resultMap.put("planinfo", plan);
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    @RequestMapping(
        value = "/{id}//deleteplan",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Plan deletePlan(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /plan/deletePlan  called #####");
        Optional<Plan> optionalPlan = planRepository.findById(id);

        optionalPlan.orElseThrow(() -> new Exception("No Entity Found"));
        Plan plan = optionalPlan.get();
        plan.deletePlan();

        planRepository.save(plan);
        return plan;
    }
}
//>>> Clean Arch / Inbound Adaptor
