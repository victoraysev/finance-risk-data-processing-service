package finance.risk.financialrecordprocessing.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "financial_records")
@CompoundIndexes({
        @CompoundIndex(name = "company_date_index", def = "{'companyName': 1, 'records.date': 1}")
})
public class FinancialRecord {

    @Id
    private String id;

    private String companyName;

    private List<Record> records;

    // Getters and Setters

    @Getter
    @Setter
    public static class Record {
        private Double closingPrice;
        private String date;

        // Getters and Setters
    }
}
