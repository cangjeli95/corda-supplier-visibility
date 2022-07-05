package com.template.contracts;

import com.template.states.TransactionState;
import org.junit.Test;

public class StateTests {

    //Mock State test check for if the state has correct parameters type
    @Test
    public void hasFieldOfCorrectType() throws NoSuchFieldException {
        TransactionState.class.getDeclaredField("msg");
        assert (TransactionState.class.getDeclaredField("msg").getType().equals(String.class));
    }
}