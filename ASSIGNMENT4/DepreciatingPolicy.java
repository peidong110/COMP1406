public class DepreciatingPolicy extends Policy{

    private float rate;
    public DepreciatingPolicy(float amount, float rate){
        super(amount);
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }
    public String toString(){
        return String.format("DepreciatingPolicy: "+super.toString().substring(8)+" rate: %2.1f%s",rate*100,"%");
    }
    public boolean isExpired(){
        return getAmount() < 0.01;
    }
    public void depreciate(){
        super.amount *= 1-getRate();
    }
    public float handleClaim() {
        float value = super.amount;
        depreciate();
        return value;

    }

}
