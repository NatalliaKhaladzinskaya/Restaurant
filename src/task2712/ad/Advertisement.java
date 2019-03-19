package task2712.ad;

public class Advertisement {
    private Object content ;     //видео
    private String name;
    private long initialAmount;        //стоимость рекламы в копейках
    private long amountPerOneDisplaying;
    private int hits;                  // количество оплаченных показов
    private int duration;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = (hits > 0) ? initialAmount / hits : 0;
    }

    public int getHits() {
        return hits;
    }

    public String getName() {
        return name;
    }
    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }
    public void revalidate(){
        if(hits==0) throw new UnsupportedOperationException();
        hits = hits-1;
    }


}
