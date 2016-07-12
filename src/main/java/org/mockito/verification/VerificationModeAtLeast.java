package org.mockito.verification;

public interface VerificationModeAtLeast extends VerificationMode{

    VerificationMode atMost(int times);
}
