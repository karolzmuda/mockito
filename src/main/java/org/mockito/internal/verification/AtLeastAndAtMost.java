package org.mockito.internal.verification;

import org.mockito.internal.verification.api.VerificationData;
import org.mockito.verification.VerificationMode;

public class AtLeastAndAtMost implements VerificationMode {

    public AtLeastAndAtMost(int min, int max) {
    }

    @Override
    public void verify(VerificationData data) {
    }

    @Override
    public VerificationMode description(String description) {
        return null;
    }

}
