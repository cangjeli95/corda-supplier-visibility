package com.template.flows;

import com.template.contracts.TransactionContract;
import com.template.states.TransactionState;
import net.corda.core.contracts.Command;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import net.corda.core.utilities.ProgressTracker;


@InitiatingFlow
@StartableByRPC
public class Initiator extends FlowLogic<Void> {
    private final String item;
    private final int amt;
    private final Party otherParty;

    private final ProgressTracker progressTracker = new ProgressTracker();

    public Initiator (String item, int amt, Party otherParty) {
        this.item = item;
        this.amt = amt;
        this.otherParty = otherParty;
    }


    @Override
    public Void call() throws FlowException {
        final Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);

        //(String item, int amt, Party supplier, Party buyer)
        final TransactionState output = new TransactionState(item,amt,otherParty,getOurIdentity());

        Command command = new Command(new TransactionContract.Commands.Buy(), getOurIdentity().getOwningKey());

        final TransactionBuilder builder = new TransactionBuilder(notary);
        builder.addOutputState(output);
        builder.addCommand(command);

        final SignedTransaction signedTx = getServiceHub().signInitialTransaction(builder);

        FlowSession otherPartySession = initiateFlow(otherParty);
        subFlow(new FinalityFlow(signedTx, otherPartySession));


        return null;
    }
}