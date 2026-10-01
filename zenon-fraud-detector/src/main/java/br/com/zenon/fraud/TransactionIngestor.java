package br.com.zenon.fraud;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionIngestor {


    public List<Transaction> getTransactions(String file) throws IOException {
        Path path = Paths.get(file);
        List<String> lines = Files.readAllLines(path);
        return lines.stream().skip(1)
                .limit(1000)
                .map(this::parseTransaction)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

    }
    private Optional<Transaction> parseTransaction(String line) {
        try {
        String[] parts = line.split(",");
            if(parts[2] == null || parts[2].trim().isEmpty())
                throw new IllegalArgumentException("O campo amount não pode ser vazio ou null");

        return Optional.of(new Transaction(
                Integer.parseInt(parts[0]),
                TransactionType.valueOf(parts[1]),
                new BigDecimal(parts[2]),
                new Customer(parts[3], new BigDecimal(parts[4]), new BigDecimal(parts[5])),
                new Customer(parts[6], new BigDecimal(parts[7]), new BigDecimal(parts[8])),
                Integer.parseInt(parts[9]),
                Integer.parseInt(parts[10])
        ));

        } catch (Exception e) {
            System.err.println("Erro ao fazer parse: " + line + "|" + e);
            e.printStackTrace();
            return Optional.empty();
        }

    }



    public List<Transaction> getTransactionsOld(String file) throws IOException {
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
