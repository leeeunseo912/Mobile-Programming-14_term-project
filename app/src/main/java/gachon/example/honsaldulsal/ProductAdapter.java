package gachon.example.honsaldulsal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CustomViewHolder> {

     private ArrayList<Product> arrayList;
     private Context context;

    public ProductAdapter(ArrayList<Product> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //ViewHolder 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        CustomViewHolder holder =  new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) { //매칭
        holder.item.setText(arrayList.get(position).getItem());
        holder.location.setText(arrayList.get(position).getLocation());
        holder.peopleNum.setText(arrayList.get(position).getPeopleNum());
    }

    @Override
    public int getItemCount() {
        return (arrayList  != null ?arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        TextView location;
        TextView peopleNum;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item= itemView.findViewById(R.id.item);
            this.location=itemView.findViewById(R.id.location);
            this.peopleNum=itemView.findViewById(R.id.peopleNum);
        }


    }
}
