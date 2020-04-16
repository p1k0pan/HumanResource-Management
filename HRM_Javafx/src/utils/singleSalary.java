package utils;

public class singleSalary {
    private String id;
    private String name;
    private double basic;
    private double subsidy;
    private double gov;
    private double pos;
    private double self;
    private double total;



    public singleSalary( String id, String name, double basic, double  subsidy, double gov, double pos, double self, double total) {
        this.id = id;
        this.name = name;
        this.basic = basic;
        this.subsidy = subsidy;
        this.gov = gov;
        this.pos = pos;
        this.self = self;
        this.total = total;
    }
    public void setId(String id) {
        this.id=id;
    }

    public void setName(String name) {
        this.name=name;
    }
    public void setTotal(double total) {
        this.total=total;
    }
    public void setBasic(double basic) {
        this.basic=basic;
    }
    public void setSubsidy(double subsidy) {
        this.subsidy=subsidy;
    }
    public void setGov(double gov) {
        this.gov=gov;
    }
    public void setPos(double pos) {
        this.pos=pos;
    }
    public void setSelf(double self) {
        this.self=self;
    }

    public String getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public double getSelf() {
        return self;
    }

    public double getPos() {
        return pos;
    }

    public double getGov() {
        return gov;
    }

    public double getSubsidy() {
        return subsidy;
    }

    public double getBasic() {
        return basic;
    }

    public String getName() {
        return name;
    }
    public String get(int column)
    {
        String str="";
        switch (column)
        {
            case 0:str=getId();break;
            case 1:str=getName();break;
            case 2:str=Double.toString(getBasic());break;
            case 3:str=Double.toString(getPos());break;
            case 4:str=Double.toString(getSubsidy());break;
            case 5:str=Double.toString(getGov());break;
            case 6:str=Double.toString(getSelf());break;
            case 7:str=Double.toString(getTotal());break;
        }
        return str;

    }

}
