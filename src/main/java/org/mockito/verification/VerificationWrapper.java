package org.mockito.verification;

import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.internal.verification.api.VerificationData;

public abstract class VerificationWrapper<Self extends VerificationWrapper<Self,WrapperType>,WrapperType extends VerificationMode> implements VerificationMode {

    protected final WrapperType wrappedVerification;

    public VerificationWrapper(WrapperType wrappedVerification) {
        this.wrappedVerification = wrappedVerification;
    }

    public void verify(VerificationData data) {
        wrappedVerification.verify(data);
    } 
    
    protected abstract Self copySelfWithNewVerificationMode(VerificationMode verificationMode);

    public Self times(int wantedNumberOfInvocations) {
        return copySelfWithNewVerificationMode(VerificationModeFactory.times(wantedNumberOfInvocations));
    }
    
    public Self never() {
        return copySelfWithNewVerificationMode(VerificationModeFactory.atMost(0));
    }

    public Self atLeastOnce() {
        return copySelfWithNewVerificationMode(VerificationModeFactory.atLeastOnce());
    }

    public Self atLeast(int minNumberOfInvocations) {
        return copySelfWithNewVerificationMode(VerificationModeFactory.atLeast(minNumberOfInvocations));
    }

    public Self atMost(int maxNumberOfInvocations) {
        return copySelfWithNewVerificationMode(VerificationModeFactory.atMost(maxNumberOfInvocations));
    }

    public Self only() {
        return copySelfWithNewVerificationMode(VerificationModeFactory.only());
    }
    
}
