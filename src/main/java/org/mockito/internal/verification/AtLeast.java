/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */

package org.mockito.internal.verification;

import static org.mockito.internal.verification.checkers.AtLeastXNumberOfInvocationsChecker.checkAtLeastNumberOfInvocations;
import static org.mockito.internal.verification.checkers.MissingInvocationChecker.checkMissingInvocation;

import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.internal.verification.api.VerificationDataInOrder;
import org.mockito.internal.verification.api.VerificationInOrderMode;
import org.mockito.verification.VerificationMode;
import org.mockito.verification.VerificationModeAtLeast;

public class AtLeast implements VerificationInOrderMode, VerificationModeAtLeast {
    
    final int minCount;
    
    public AtLeast(int wantedNumberOfInvocations) {
        if (wantedNumberOfInvocations < 0) {
            throw new MockitoException("Negative value is not allowed here");
        }
        this.minCount = wantedNumberOfInvocations;
    }
    
    @Override
    public void verify(VerificationData data) {
        if (minCount == 1) {
             checkMissingInvocation(data.getAllInvocations(), data.getWanted());
        }
        checkAtLeastNumberOfInvocations(data.getAllInvocations(), data.getWanted(), minCount);
    }
    
    @Override
    public void verifyInOrder(VerificationDataInOrder data) {
        if (minCount == 1) {
             checkMissingInvocation(data.getAllInvocations(), data.getWanted(),  data.getOrderingContext());
        }
        
        checkAtLeastNumberOfInvocations(data.getAllInvocations(), data.getWanted(), minCount, data.getOrderingContext());
    }

    @Override
    public String toString() {
        return "Wanted invocations count: at least " + minCount;
    }

    @Override
    public VerificationMode description(String description) {
        return VerificationModeFactory.description(this, description);
    }
    
    public VerificationMode atMost(int max){
        return VerificationModeFactory.atLeastAndMost(minCount,max);
    }
}