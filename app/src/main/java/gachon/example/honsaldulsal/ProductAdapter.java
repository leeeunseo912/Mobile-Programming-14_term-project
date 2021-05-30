package gachon.example.honsaldulsal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class  ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CustomViewHolder> {



    private ArrayList<Product> arrayList;
    private Context context;

    public ProductAdapter(ArrayList<Product> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //ViewHolder 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() { //클릭시 이벤트
            @Override //click시 상품의 데이터들을 product_info.java로 보내준다
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Product_info.class);
                intent.putExtra("image", arrayList.get(position).getImage());
                intent.putExtra("item", arrayList.get(position).getItem());
                intent.putExtra("location", arrayList.get(position).getLocation());
                intent.putExtra("people", people);
                intent.putExtra("etc",arrayList.get(position).getEtc());
                intent.putExtra("price", arrayList.get(position).getPrice());
                intent.putExtra("quantity", arrayList.get(position).getQuantity());
                intent.putExtra("productKey",arrayList.get(position).getProductKey());
                view.getContext().startActivity(intent);
                System.out.println("Check "+ arrayList.get(position).getProductKey());

            }
        });
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
            this.people = itemView.findViewById(R.id.people);

        }
    }


}