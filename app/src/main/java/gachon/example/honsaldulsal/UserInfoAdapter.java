package gachon.example.honsaldulsal;

import androidx.annotation.NonNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.UserInfoHolder> {

    private ArrayList<UserInfo> arrayList;
    private Context context;

    public UserInfoAdapter(ArrayList<UserInfo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat, parent, false);
        UserInfoHolder holder = new UserInfoHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoAdapter.UserInfoHolder holder, int position) {
        holder.transaction.setText(arrayList.get(position).getTransaction());
        holder.host_name.setText(arrayList.get(position).getChat());
        holder.chat_num.setText(arrayList.get(position).getChat());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class UserInfoHolder extends RecyclerView.ViewHolder {
        TextView transaction;
        TextView host_name;
        TextView chat_num;

        public UserInfoHolder(@NonNull View itemView) {
            super(itemView);
            this.transaction = itemView.findViewById(R.id.transaction);
            this.host_name = itemView.findViewById(R.id.host_name);
            this.chat_num = itemView.findViewById(R.id.chat_num);
        }
    }
}