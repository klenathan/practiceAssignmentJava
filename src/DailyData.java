public class DailyData {
    String date, new_cases, new_death, vaxed, population;

    public DailyData(String date, String new_cases, String new_death, String vaxed, String population) {
        this.date = date;
        this.new_cases = new_cases;
        this.new_death = new_death;
        this.vaxed = vaxed;
        this.population = population;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNew_cases() {
        return new_cases;
    }

    public void setNew_cases(String new_cases) {
        this.new_cases = new_cases;
    }

    public String getNew_death() {
        return new_death;
    }

    public void setNew_death(String new_death) {
        this.new_death = new_death;
    }

    public String getVaxed() {
        return vaxed;
    }

    public void setVaxed(String vaxed) {
        this.vaxed = vaxed;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
}
