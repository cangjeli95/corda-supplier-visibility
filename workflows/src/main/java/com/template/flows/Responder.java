package com.template.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.template.contracts.TransactionContract;
import com.template.states.TransactionState;
import net.corda.core.contracts.Command;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import net.corda.core.utilities.ProgressTracker;

import javax.annotation.Signed;


@InitiatedBy(Initiator.class)
public class Responder extends FlowLogic<Void> {
    private FlowSession otherPartySession;

    public Responder (FlowSession otherPartySession) {this.otherPartySession = otherPartySession;};


    @Suspendable
    @Override
    public Void call() throws FlowException {
        SignedTransaction signedTransaction = subFlow(new SignTransactionFlow(otherPartySession) {
            @Suspendable
            @Override
            protected void checkTransaction(SignedTransaction stx) throws FlowException {

            }
        });

        subFlow(new ReceiveFinalityFlow(otherPartySession));
        return null;
    }
}