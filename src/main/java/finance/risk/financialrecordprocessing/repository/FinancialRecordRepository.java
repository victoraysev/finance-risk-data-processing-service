// src/main/java/com/yourpackage/repository/FinancialRecordRepository.java
package finance.risk.financialrecordprocessing.repository;


import finance.risk.financialrecordprocessing.model.FinancialRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FinancialRecordRepository extends MongoRepository<FinancialRecord, String> {
    Optional<FinancialRecord> findByCompanyName(String companyName);
}
