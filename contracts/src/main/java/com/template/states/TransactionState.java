package com.template.states;

import com.template.contracts.TransactionContract;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

import java.util.Arrays;
import java.util.List;

// *********
// * Transaction State *
// *********
@BelongsToContract(TransactionContract.class)
public class TransactionState implements ContractState {

    //private variables
    private final String item;
    private final int amt;

    private final Party supplier;
    private final Party buyer;

    /* Constructor of your Corda state */
    public TransactionState(String item, int amt, Party supplier, Party buyer) {
        this.item = item;
        this.amt = amt;
        this.supplier = supplier;
        this.buyer = buyer;
    }

    //getters
    public String getItem() { return item; }
    public int getAmt() { return amt; }
    public Party getSupplier() { return supplier; }
    public Party getBuyer() { return buyer; }

    /* This method will indicate who are the participants and required signers when
     * this state is used in a transaction. */
    @Override
    public List<AbstractParty> getParticipants() {
        return Arrays.asList(supplier,buyer);
    }
}