public class Group {
    private String date;

    int cases, deaths, vax, population;

    public Group(String date, int cases, int deaths, int vax, int population) {
        this.date = date;
        this.cases = cases;
        this.deaths = deaths;
        this.vax = vax;
        this.population = population;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getVax() {
        return vax;
    }

    public void setVax(int vax) {
        this.vax = vax;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
