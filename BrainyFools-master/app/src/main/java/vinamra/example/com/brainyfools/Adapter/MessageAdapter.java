package vinamra.example.com.brainyfools.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

import vinamra.example.com.brainyfools.Models.Employee;
import vinamra.example.com.brainyfools.R;
import vinamra.example.com.brainyfools.Views.SendMessage;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>
{
    List<Employee> employeeList;
    Context context;

    public MessageAdapter(List<Employee> employeeList, Context context) {
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
        holder.status.setImageResource(R.drawable.ic_message_black_24dp);
        holder.status.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.dialog_send_message, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                final EditText editTextTitle=promptsView.findViewById(R.id.editTextTitle);
                final EditText editTextMsg=promptsView.findViewById(R.id.editTextMessage);
                alertDialogBuilder.setCancelable(false).setPositiveButton("Send", (dialog, id) -> {
                    String title = editTextTitle.getText().toString().trim();
                    String message = editTextMsg.getText().toString().trim();
                    sendMessage(employee.getEmp_id(), title, message);
                }).setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
    private void sendMessage(String id, String title, String message){

    }

    @Override
    public int getItemCount() {
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
