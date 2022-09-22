package org.santander.marketprice;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Price {

    private long id;
    private String instrumentName;
    private Double bid;
    private Double ask;
    private Timestamp timestamp;
}
