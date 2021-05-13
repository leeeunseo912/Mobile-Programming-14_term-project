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
        /*
        String temp = arrayList.get(position).getChat();
        int idx = temp.indexOf("/");

        String tranTemp = temp.substring(0, idx);
        String chat_numTemp = temp.substring(idx+1);

        idx = chat_numTemp.indexOf("chat_num/");
        chat_numTemp = chat_numTemp.substring(idx+1, idx+2);

        String host_nameTemp = chat_numTemp.substring(idx+2);
        idx = host_nameTemp.indexOf("host_name/");
        host_nameTemp = host_nameTemp.substring(idx+1);
        idx = host_nameTemp.indexOf("/");
        host_nameTemp = host_nameTemp.substring(0, idx);
         */

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