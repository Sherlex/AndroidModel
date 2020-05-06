package com.example.eco;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ArticleViewHolder> {


    public interface onClickListener {
        void onVariantClick(Model model);
    }

    private onClickListener listener;

    private List<Model> models;

    public Adapter(List<Model> models) {
        this.models = models;
    }

    private LayoutInflater layoutInflater;
    private Context context;

   public Adapter(Context context) {
        this.context = context;
    }

    /*public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }*/

   /*@Override
    public int getCount() {
        return models.size();
    }*/

   /* @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }*/

   /* @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        //ImageView imageView;
        //TextView title, desc;

        //imageView = view.findViewById(R.id.image);
        //title = view.findViewById(R.id.title);
        //desc = view.findViewById(R.id.desc);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDescription());

        container.addView(view, 0);

        return view;

    }*/

    @Override
    public ArticleViewHolder onCreateViewHolder (ViewGroup context, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(context.getContext()).inflate(R.layout.item, context, false));
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, final int position) {
        final Model model = models.get(position);

        if (holder !=null) {
            //ImageView imageView = holder.imageView;

            //Picasso.with(context).load(model.Image).into(holder.imageView);

            //imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.image1));
            holder.title.setText(model.Title);
            holder.desc.setText(model.Description);
            //holder.content.setText(model.Content);

            if (listener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onVariantClick(model);
                        //Toast.makeText(context, "Click" + position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, ArticleActivity.class);
                        context.startActivity(intent);
                        intent.putExtra("article", model.Content);
                    }
                });
            }
        }
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click" + position, Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    public void setListener(onClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


   /* @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }*/

    public class ArticleViewHolder extends  RecyclerView.ViewHolder {

        //ImageView imageView;
        TextView title, desc, content;

        public ArticleViewHolder(View view) {
            super(view);
           ButterKnife.bind(this, itemView);

            //imageView = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title);
            desc = view.findViewById(R.id.desc);
            //content = view.findViewById(R.id.articleTextView);
        }

    }
}
