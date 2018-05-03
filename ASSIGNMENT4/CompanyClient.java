public class CompanyClient extends Client{

    public CompanyClient(String name){
        super(name);
    }

    @Override
    public float makeClaim(int polNum) {
        float val = 0;
        for (int i = 0; i < numPolicies; i++){
            if (policies[i].getPolicyNumber() == polNum){
                    val = policies[i].handleClaim();
                    break;
            }
        }
        return val;
    }
}
