package problem3;

public class HealthInsuranceSurveyor implements InsuranceSurveyor {
    @Override
    public boolean isValidClaim() {
        System.out.println("Health Insurance Surveyor: Validating healthy insurance claim...");
        // business logic to validate this claim
        return true; // to make it simple, we always return true
    }
}
