package io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.application;

public interface RetrieveAccount {
    Account load(Long accountNo);
}
