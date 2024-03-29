package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.SalesDto;
import edu.icet.clothify.entity.Sales;
import edu.icet.clothify.repository.SalesRepository;
import edu.icet.clothify.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    SalesRepository salesRepository;
    @Override
    public Sales addSales(Sales sales) {
        return salesRepository.save(sales);
    }

    @Override
    public boolean addSales(SalesDto salesDto) {
        Sales sales = mapper.convertValue(salesDto, Sales.class);
        Sales saved = salesRepository.save(sales);
        return saved.getId()!=null;
    }

    @Override
    public Sales updateSales(Long id ,Sales sales) {
        if (!salesRepository.existsById(id)){
            throw new ResourceNotFoundException("Sales not found from id : "+id);
        }
        sales.setId(id);
        return salesRepository.save(sales);
    }

    @Override
    public Boolean deleteSales(Long id) {
        if (salesRepository.existsById(id)){
            salesRepository.deleteById(id);
            return true;
        }else {
            throw new ResourceNotFoundException("no sales info not available for this id to delete: "+id);
        }
    }
}
