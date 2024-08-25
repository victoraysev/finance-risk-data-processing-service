package finance.risk.financialrecordprocessing.controller;

import finance.risk.financialrecordprocessing.model.FinancialRecord;
import finance.risk.financialrecordprocessing.service.FinancialRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financial-records")
public class FinancialRecordController {

    @Autowired
    private FinancialRecordService service;

    @PostMapping
    public void saveOrUpdateRecords(@RequestParam String companyName, @RequestBody List<FinancialRecord.Record> records) {
        service.saveOrUpdateRecords(companyName, records);
    }

    @GetMapping
    public List<FinancialRecord.Record> getRecords(@RequestParam String companyName,
                                                   @RequestParam String startDate,
                                                   @RequestParam String endDate) {
        return service.getRecords(companyName, startDate, endDate);
    }

    @GetMapping("/companies")
    public List<String> getCompanies() {
        return service.getAllCompanyNames();
    }
}

