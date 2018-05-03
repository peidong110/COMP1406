public class IndividualClient extends Client {
    private int counter = 0;

    public IndividualClient(String n) {
        super(n);
    }

    @Override
    public float makeClaim(int polNum) {
        float result = 0;
        for (int i = 0; i < numPolicies; i++){
            if (policies[i].getPolicyNumber() == polNum && !policies[i].isExpired()){
                if (policies[i] instanceof DepreciatingPolicy == false &&policies[i] instanceof ExpiringPolicy== false&& counter <=0){
                    counter+=1;
                    result = policies[i].amount;
                    cancelPolicy(policies[i].getPolicyNumber());
//                    break;
                }
                //if(policies[i].getClass().getName()=="Policy"){
                  //  rPclaimed = true;
                //}
                else{
                    if (policies[i] instanceof DepreciatingPolicy){
                        ((DepreciatingPolicy)policies[i]).depreciate();
                        result = policies[i].amount;
//                        break;
                    }
                    if(policies[i] instanceof ExpiringPolicy){
                        result = policies[i].amount;
//                        break;
                    }
                }
                break;
            }
            else{
                result = 0;
            }
        }
        return result;
    }

}
