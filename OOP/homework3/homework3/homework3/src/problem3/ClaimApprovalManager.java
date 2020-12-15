package problem3;


public class ClaimApprovalManager {
    public void processHealthClaim(HealthInsuranceSurveyor surveyor) {
        if (surveyor.isValidClaim()) {
            System.out.println("Claim Approval Manager: Valid claim, Currently processing claim for approval...");
        }
    }
}
