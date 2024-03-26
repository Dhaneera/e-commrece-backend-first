package edu.icet.clothify.service.impl;

import edu.icet.clothify.dto.SalesDto;
import edu.icet.clothify.entity.Sales;
import edu.icet.clothify.service.SalesService;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl implements SalesService {
    @Override
    public Sales addSales(Sales sales) {
        return null;
    }

    @Override
    public boolean addSales(SalesDto salesDto) {
        return false;
    }

    @Override
    public Sales updateSales(Sales sales) {
        return null;
    }

    @Override
    public void deleteSales(Long id) {

    }
}
