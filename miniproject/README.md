# MiniBank (Practical 12–14 integrated project)

## Requirements
- JDK 17 or newer
- No external library is needed to run the application.

## Compile and run (from this folder)
```bash
javac *.java
java MiniBank
```

## Run JUnit tests and build a runnable JAR (Maven)
```bash
mvn test
mvn package
java -jar target/minibank-1.0.0.jar
```

The JUnit 5 tests check deposit, withdrawal-limit exceptions, and concurrent deposits.

## Create a runnable JAR manually
```bash
javac *.java
jar --create --file MiniBank.jar --main-class MiniBank *.class
java -jar MiniBank.jar
```

## Included
- `AccountStore`: `HashMap` storage, `ArrayList` transaction history, balance sorting with `Comparator`.
- `Account implements Comparable<Account>`: account-number comparison.
- `Repository<T>`: generic CRUD and reflective `@Id` lookup, `@SafeVarargs saveAll`.
- `BankService`: open, deposit, withdraw, transfer, statement, list, persistence, batch and report.
- `TransactionProcessor`: concurrent map and thread-pool batch processing.
- `StatePersister`: serializes accounts to `data/accounts.dat`.
- `TransactionLog` and `ReportGenerator`: append log and generate totals in `reports/transaction-report.txt`.

## Notes
- Account balances are represented as whole-number units to match the existing project.
- Batch input currently supports `DEPOSIT accountNumber amount` and `WITHDRAW accountNumber amount`; use menu option 4 for transfers.
- Generated `data/`, `logs/`, and `reports/` files are runtime data; do not commit them.
