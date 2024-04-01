package edu.icet.clothify.service.impl;

import edu.icet.clothify.dto.DistanceMatrixDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceServiceImpl {
    private String apiKey="AIzaSyCEBtTPwHD0mXLMRL-1kJ2Cj-ueZP3LAUk";

    public ResponseEntity<DistanceMatrixDto> getDistance(String origin, String destination) throws Exception{
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin
                + "&destinations=" + destination + "&key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DistanceMatrixDto> response =restTemplate.getForEntity(url, DistanceMatrixDto.class);
        if (response.getStatusCode()== HttpStatus.OK){
            return response;
        }else {
            throw new Exception("Failed to get Distance try again");
        }
    }

}
