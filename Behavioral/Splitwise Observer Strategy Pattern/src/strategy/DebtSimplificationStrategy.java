package strategy;

import model.User;
import model.Transaction;
import java.util.Map;
import java.util.List;

public interface DebtSimplificationStrategy {
    List<Transaction> simplifyDebts(Map<User, Double> netBalances);
}