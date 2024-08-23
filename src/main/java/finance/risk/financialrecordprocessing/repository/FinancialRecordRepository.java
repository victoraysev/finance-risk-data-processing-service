// src/main/java/com/yourpackage/repository/FinancialRecordRepository.java
package finance.risk.financialrecordprocessing.repository;


import finance.risk.financialrecordprocessing.model.FinancialRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FinancialRecordRepository extends MongoRepository<FinancialRecord, String> {
    Optional<FinancialRecord> findByCompanyName(String companyName);

    @Query(value = "{ 'companyName': ?0, 'records.date': { $gte: ?1, $lte: ?2 } }", fields = "{ 'records.$': 1 }")
    List<FinancialRecord.Record> findByCompanyNameAndDateRange(String companyName, String startDate, String endDate);

}
