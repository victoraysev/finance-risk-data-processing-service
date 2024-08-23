// src/main/java/com/yourpackage/service/FinancialRecordService.java
package finance.risk.financialrecordprocessing.service;
import finance.risk.financialrecordprocessing.model.FinancialRecord;
import finance.risk.financialrecordprocessing.repository.FinancialRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository repository;

    // Save or update financial records for a company
    public void saveOrUpdateRecords(String companyName, List<FinancialRecord.Record> records) {
        Optional<FinancialRecord> existingRecord = repository.findByCompanyName(companyName);
        FinancialRecord entity;
        if (existingRecord.isPresent()) {
            entity = existingRecord.get();
            entity.setRecords(records);
        } else {
            entity = new FinancialRecord();
            entity.setCompanyName(companyName);
            entity.setRecords(records);
        }
        repository.save(entity);
    }

    // Retrieve financial records for a company within a time interval
    public List<FinancialRecord.Record> getRecords(String companyName, String startDate, String endDate) {
        List<FinancialRecord.Record> entity = repository.findByCompanyNameAndDateRange(companyName, startDate, endDate);
        return entity;
    }
}
