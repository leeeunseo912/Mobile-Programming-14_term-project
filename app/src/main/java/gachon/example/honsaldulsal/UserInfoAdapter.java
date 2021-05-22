package gachon.example.honsaldulsal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class  UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.CustomViewHolder> {

    private ArrayList<Product> arrayList;
    private Context context;


    public UserInfoAdapter(ArrayList<Product> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //ViewHolder 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) { //매칭
        String people;
        holder.item.setText(arrayList.get(position).getItem());
        holder.location.setText(arrayList.get(position).getLocation());
        people = String.valueOf(arrayList.get(position).getCurrentNum()) + "명 / " + String.valueOf(arrayList.get(position).getPeopleNum()+"명");
        holder.people.setText(people);
    }

    @Override
    public int getItemCount() {
        return (arrayList  != null ?arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView item;
        TextView location;
        TextView people;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item = itemView.findViewById(R.id.item);
            this.location = itemView.findViewById(R.id.location);
            this.people = itemView.findViewById(R.id.chat_num);
        }
    }
}
