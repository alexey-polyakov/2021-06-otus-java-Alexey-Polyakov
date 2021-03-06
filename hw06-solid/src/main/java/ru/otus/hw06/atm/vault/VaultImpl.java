package ru.otus.hw06.atm.vault;

import ru.otus.hw06.atm.exception.EmptyVaultException;
import ru.otus.hw06.banknote.Banknote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static ru.otus.hw06.util.Validations.requireNonEmpty;

public class VaultImpl implements Vault {

    private final Map<Banknote, Integer> banknotesStorage;

    public VaultImpl() {
        this.banknotesStorage = new TreeMap<>(Comparator.comparing(Banknote::getDenominationValue).reversed());
    }

    @Override
    public List<Banknote> get(int amount) {
        requireNonEmpty(banknotesStorage, () -> new EmptyVaultException("no banknotes in vault"));
        List<Banknote> banknotes = new ArrayList<>();
        amount = fillBanknotesWithAmount(amount, banknotes);
        return amount == 0 ? banknotes : Collections.emptyList();
    }

    @Override
    public void put(List<Banknote> banknotes) {
        for (Banknote banknote : banknotes) {
            Integer banknotesCount = banknotesStorage.get(banknote);
            banknotesStorage.put(banknote, banknotesCount != null ? ++banknotesCount : 1);
        }
    }

    @Override
    public int calculateBalance() {
        return banknotesStorage.entrySet().stream()
                .mapToInt(this::calculateBanknoteEntryBalance)
                .sum();
    }

    private int calculateBanknoteEntryBalance(Map.Entry<Banknote, Integer> banknoteEntry) {
        return banknoteEntry.getKey().getDenominationValue() * banknoteEntry.getValue();
    }

    private int fillBanknotesWithAmount(int amount, List<Banknote> banknotes) {
        for (Map.Entry<Banknote, Integer> banknoteEntry : banknotesStorage.entrySet()) {
            if (amount != 0) {
                Banknote banknote = banknoteEntry.getKey();
                int count = banknoteEntry.getValue();
                int banknoteValue = banknote.getDenominationValue();
                int requiredBanknotesCount = Math.min(amount / banknoteValue, count);
                if (requiredBanknotesCount > 0) {
                    int remainingCount = count - requiredBanknotesCount;
                    while (requiredBanknotesCount != 0) {
                        amount -= banknoteValue;
                        banknotes.add(banknote);
                        requiredBanknotesCount--;
                    }
                    banknotesStorage.put(banknote, remainingCount);
                }
            }
        }
        return amount;
    }
}
