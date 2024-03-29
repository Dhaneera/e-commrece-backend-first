package edu.icet.clothify.controller;

import edu.icet.clothify.dto.BillingInfoDto;
import edu.icet.clothify.entity.BillingInfo;
import edu.icet.clothify.service.BillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/billinfo")
public class BillInfoController {
    @Autowired
    private BillingInfoService billingInfoService;

    @PostMapping("/add")
    public  Boolean createBillingInfo(@RequestBody BillingInfoDto billingInfoDto){
        return billingInfoService.createBillingInfo(billingInfoDto);
    }
    @PutMapping("update/{id}")
    public BillingInfo updateBillinfo(@PathVariable Long id, @RequestBody BillingInfoDto billingInfoDto){
        return  billingInfoService.updateBillingInfo(id,billingInfoDto);
    }
//    @DeleteMapping("/remove/{id}")
//    public  Boolean deleteBillinfo(@PathVariable Long id){
//        return billingInfoService.deleteBillingInfo(id);
//    }
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String,String> error(MethodArgumentNotValidException exception){
    Map <String, String> map=new HashMap<>();
    List<ObjectError> list=exception.getBindingResult().getAllErrors();
    for(ObjectError item:list) {
        FieldError fieldError=(FieldError) item;
        String fieldName= fieldError.getField();
        String message = item.getDefaultMessage();
        map.put(fieldName,message);
    }
    return map;
}

}
