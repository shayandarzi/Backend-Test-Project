package io.egroup.backendtestproject.web;


import io.egroup.backendtestproject.dto.response.PlanResponseDto;
import io.egroup.backendtestproject.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {

    private PlanService planService;

    @GetMapping
    public ResponseEntity<PlanResponseDto> plan() {
        return ResponseEntity.ok(planService.plan());
    }

}
