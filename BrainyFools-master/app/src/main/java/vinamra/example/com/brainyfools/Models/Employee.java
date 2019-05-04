package vinamra.example.com.brainyfools.Models;

public class Employee
{
    private String name;
    private String emp_id;
    private String out_time;
    private String in_time;

    public Employee()
    {

    }
    public Employee(String name, String emp_id, String out_time, String in_time)
    {
        this.name = name;
        this.emp_id=emp_id;
        this.out_time=out_time;
        this.in_time=in_time;
    }

    public Employee(String name, String emp_id) {
        this.name = name;
        this.emp_id = emp_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmp_id()
    {
        return emp_id;
    }

    public void setEmp_id(String emp_id)
    {
        this.emp_id = emp_id;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }
}
