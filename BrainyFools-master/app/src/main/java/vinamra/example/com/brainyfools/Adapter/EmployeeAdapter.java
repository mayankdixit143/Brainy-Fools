package vinamra.example.com.brainyfools.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

import vinamra.example.com.brainyfools.Models.Employee;
import vinamra.example.com.brainyfools.R;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>
{

    List<Employee> employeeList;
    Context context;

    public EmployeeAdapter(List<Employee> employeeList, Context context)
    {
        this.employeeList = employeeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v=LayoutInflater.from(context).inflate(R.layout.emp_fetch,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Employee employee=employeeList.get(holder.getAdapterPosition());
        holder.textView.setText(employee.getName());
        holder.timegap.setText("09:00 AM");
        if(employee.getIn_time()==null)
        {
            holder.status.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
        }
        else
        {
            holder.status.setImageResource(R.drawable.ic_check_circle_black_24dp);
        }
    }

    @Override
    public int getItemCount()
    {
        return employeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        ImageView status;
        TextView timegap;
        ConstraintLayout constraintLayout;

        public ViewHolder(View itemView)
        {
            super(itemView);
            textView = itemView.findViewById(R.id.ename);
            status=itemView.findViewById(R.id.status);
            timegap=itemView.findViewById(R.id.timeget);
        }
    }
}
