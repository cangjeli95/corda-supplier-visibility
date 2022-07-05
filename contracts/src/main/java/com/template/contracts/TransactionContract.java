package com.template.contracts;

import com.template.states.TransactionState;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;

import static net.corda.core.contracts.ContractsDSL.requireThat;

public class TransactionContract implements Contract {

    public static final String ID = "com.template.contracts.TransactionContract";

    @Override
    public void verify(LedgerTransaction tx) {

    }


    public interface Commands extends CommandData {
        //In our hello-world app, We will only have one command.
        class Buy implements Commands {}
    }
}