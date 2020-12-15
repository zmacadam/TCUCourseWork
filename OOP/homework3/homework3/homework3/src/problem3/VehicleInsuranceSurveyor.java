package problem3;

public class VehicleInsuranceSurveyor implements InsuranceSurveyor {
    @Override
    public boolean isValidClaim() {
        System.out.println("Vehicle Insurance Surveyor: Validating car has no preexisting damages...");
        // business logic to validate this claim
        return true; // to make it simple, we always return true
    }
}
