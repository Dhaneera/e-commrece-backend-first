package edu.icet.clothify.service;

import edu.icet.clothify.dto.SalesDto;
import edu.icet.clothify.entity.Sales;

public interface SalesService {
    public Sales addSales(Sales sales);
    public boolean addSales(SalesDto salesDto);
    public Sales updateSales(Sales sales);
    void deleteSales(Long id);
}
