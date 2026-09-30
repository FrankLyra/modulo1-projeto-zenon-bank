package br.com.zenon.fraud;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

    public List<Transaction> getTransactions(String file) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        Path path = Paths.get(file);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine(); // skip header: step,type,amount,nameOrig,oldbalanceOrg,newbalanceOrig,nameDest,oldbalanceDest,newbalanceDest,isFraud,isFlaggedFraud

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length < 11) {
                    continue;
                }

                Transaction transaction = new Transaction(
                        Integer.parseInt(parts[0]),
                        TransactionType.valueOf(parts[1]),
                        new BigDecimal(parts[2]),
                        new Customer(parts[3], new BigDecimal(parts[4]), new BigDecimal(parts[5])),
                        new Customer(parts[6], new BigDecimal(parts[7]), new BigDecimal(parts[8])),
                        Integer.parseInt(parts[9]),
                        Integer.parseInt(parts[10])
                );

                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
