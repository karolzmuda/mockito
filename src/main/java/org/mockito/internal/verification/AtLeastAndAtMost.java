package org.mockito.internal.verification;

import static org.mockito.internal.exceptions.Reporter.neverWantedButInvoked;
import static org.mockito.internal.exceptions.Reporter.tooLittleActualInvocations;
import static org.mockito.internal.exceptions.Reporter.tooManyActualInvocations;
import static org.mockito.internal.invocation.InvocationMarker.markVerified;
import static org.mockito.internal.invocation.InvocationsFinder.findInvocations;
import static org.mockito.internal.invocation.InvocationsFinder.getLastLocation;

import java.util.List;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.internal.reporting.Discrepancy;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.Location;
import org.mockito.verification.VerificationMode;

public class AtLeastAndAtMost implements VerificationMode {

    private int min;
    private int max;


    public AtLeastAndAtMost(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void verify(VerificationData data) {
        InvocationMatcher wanted = data.getWanted();
        List<Invocation> actualInvocations = findInvocations(data.getAllInvocations(), wanted);
        
        int actualCount = actualInvocations.size();
        if (actualCount < min) {
            Location lastInvocation = getLastLocation(actualInvocations);
            throw tooLittleActualInvocations(new Discrepancy(min, actualCount), wanted, lastInvocation);
        } 
        if (max == 0 && actualCount > 0) {
            Location firstUndesired = actualInvocations.get(max).getLocation();
            throw neverWantedButInvoked(wanted, firstUndesired); 
        } 
        if (actualCount > max) {
            Location firstUndesired = actualInvocations.get(max).getLocation();
            throw tooManyActualInvocations(max, actualCount, wanted, firstUndesired);
        }
        
        markVerified(actualInvocations, wanted);
    }

    @Override
    public VerificationMode description(String description) {
        return null;
    }
}
