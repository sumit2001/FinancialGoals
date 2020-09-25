package com.ujjawal.financialgoals.Controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ujjawal.financialgoals.Dto.GoalAddDto;
import com.ujjawal.financialgoals.Entity.Goals;
import com.ujjawal.financialgoals.Repository.GoalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
class restController {
    @Autowired
    GoalsRepository goalsRepository;

    @RequestMapping(path="/addGoal")
    public ResponseEntity addGoal(@Valid @RequestBody Goals goal, BindingResult bindingResult)
    {
        HashMap<String,String> errormap=new HashMap<>();
        //System.out.println(bindingResult.getAllErrors().toString());
        if(bindingResult.hasErrors())
        {
            List<FieldError> errors=bindingResult.getFieldErrors();
            for(FieldError e:errors)
            {
                errormap.put(e.getField(),e.getDefaultMessage());
            }
            return new ResponseEntity(errormap, HttpStatus.BAD_REQUEST);
        }
        System.out.println(goal.toString());

        goalsRepository.save(goal);


        return new ResponseEntity(goal,HttpStatus.ACCEPTED);
    }

    @RequestMapping(path="/getGoals")
    public List<Goals> getGoals(){
        return goalsRepository.findAll();
    }


    @PostMapping(path="/addTransaction")
    public Goals addTransaction(@RequestBody  GoalAddDto goalAddDto)
    {
        Optional<Goals> goals= goalsRepository.findById(goalAddDto.getGoal());
        Goals goal=goals.get();
        List<Integer> transactions=goal.getTranscations();
        if(transactions==null)
        {
            transactions=new ArrayList<Integer>();
            transactions.add(goalAddDto.getTransaction());
            goal.setTranscations(transactions);
        }
        else
        goal.getTranscations().add(goalAddDto.getTransaction());
        goalsRepository.save(goal);
        return goal;
    }

    @PostMapping("/deleteGoal")
    public void deleteGoal(@RequestBody Goals goal)
    {


       Optional<Goals> goals= goalsRepository.findById(goal.getGoal());
        Goals g=goals.get();
        
     goalsRepository.delete(g);

    }


}
