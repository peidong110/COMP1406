import java.util.*;
public abstract class Client {
    private static final int MAX_POLICIES_PER_CLIENT = 10;
    private static int NEXT_CLIENT_ID = 1;
    private String name;
    private int id;
    protected Policy[] policies;
    protected int numPolicies;
    public Client(String n) {
        name = n;
        id = NEXT_CLIENT_ID++;
        policies = new Policy[MAX_POLICIES_PER_CLIENT];
        numPolicies = 0;
    }
    public String getName() { return name; }
    public int getId() { return id; }
    public Policy[] getPolicies() { return policies; }
    public int getNumPolicies() { return numPolicies; }
    public String toString()
    {

            return String.format(this.getClass().getName()+":%06d amount: %s", id, name);
    }

    public float totalCoverage(){
        float amount =0;
        for (int i = 0; i < numPolicies; i++){
            amount += policies[i].amount;
        }
        return amount;
    }
    public Policy addPolicy(Policy policy) {
        if (numPolicies < MAX_POLICIES_PER_CLIENT) {
            policies[numPolicies] = policy;
            numPolicies++;
            return policy;
        }
        else{
            return null;

        }
    }
    public void openPolicyFor(float amt){
        Policy temp = new Policy(amt);
        addPolicy(temp);
    }
    public void openPolicyFor(float amt,float rate){
        DepreciatingPolicy temp = new DepreciatingPolicy(amt,rate);
        addPolicy(temp);

    }
    public void openPolicyFor(float amt, Date expire){
        ExpiringPolicy temp = new ExpiringPolicy(amt,expire);
        addPolicy(temp);
    }


    public Policy getPolicy(int polNum){
        int temp = -1;
        for (int i = 0 ;i < numPolicies; i++){
            if (policies[i].getPolicyNumber() == polNum){
                temp = i;
            }
            else
                temp = -1;
        }
        return policies[temp];

    }
    public boolean cancelPolicy(int polNum){
        boolean flag = false;
        for(int i = 0; i < numPolicies; i++){
            if (policies[i].getPolicyNumber() == polNum){
                policies[i] = policies[numPolicies-1];
                flag = true;
                numPolicies-=1;
                break;
            }
        }
        return flag;
    }

    public abstract float makeClaim(int polNum);
}
