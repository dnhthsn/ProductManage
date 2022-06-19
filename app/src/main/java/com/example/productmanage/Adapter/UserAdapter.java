package com.example.productmanage.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanage.Database.ProductSQLite;
import com.example.productmanage.Database.UserSQLite;
import com.example.productmanage.Model.Products;
import com.example.productmanage.Model.Users;
import com.example.productmanage.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private List<Users> users;
    private UserSQLite userSQLite;

    public UserAdapter(Context context, List<Users> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.users_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.idUser.setText(String.valueOf(users.get(position).getIdUser()));
        holder.nameUser.setText(users.get(position).getUserName());
        holder.passUser.setText(users.get(position).getPasswordUser());
        holder.phoneUser.setText(users.get(position).getPhoneUser());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("You want to delete " +"'"+ users.get(position).getUserName() +"'" + " ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userSQLite = new UserSQLite(context);
                        userSQLite.deleteUser(users.get(position).getIdUser());
                        users.remove(position);


                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void filterList(ArrayList<Users> filteredList) {
        users = filteredList;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView idUser, nameUser, passUser, phoneUser;
        private Button btnDelete;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            idUser = itemView.findViewById(R.id.txt_id_user);
            nameUser = itemView.findViewById(R.id.txt_name_user);
            passUser = itemView.findViewById(R.id.txt_password_user);
            phoneUser = itemView.findViewById(R.id.txt_phone_user);
            btnDelete = itemView.findViewById(R.id.btn_delete_user);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
