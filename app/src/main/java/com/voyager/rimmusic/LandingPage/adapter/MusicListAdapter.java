package com.voyager.rimmusic.LandingPage.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import com.voyager.rimmusic.LandingPage.model.MusicDetails;
import com.voyager.rimmusic.R;


import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 04-Sep-18.
 */

public class MusicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MusicDetails> musicDetails =new ArrayList<>();
    private Context context;
    private ClickListener clickListener;
    private LayoutInflater infalter;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;

    Gson gson;


    public MusicListAdapter(List<MusicDetails> musicDetails, Context context) {
        this.musicDetails = musicDetails;
        this.infalter = LayoutInflater.from(context);
        this.context = context;
        gson = new Gson();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //System.out.println(" ------------ DrawerListAdapter onCreateViewHolder viewType : "+viewType);
    /*    if(viewType == 1){
            View rootView = infalter.inflate(R.layout.content_music_list_footer,parent,false);
            System.out.println(" ------------ DrawerListAdapter drawer_list_menu_card");
            return new DrawerFooterHolder(rootView);
        }else{*/
            View rootView = infalter.inflate(R.layout.content_music_list_header,parent,false);
            //System.out.println(" ------------ DrawerListAdapter drawer_header");
            return new mHeaderViewHolder(rootView);

    }

    public void update(int position,MusicDetails musicDetails){
        this.musicDetails.get(position).setNotify(musicDetails.getNotify());
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holderViews, final int position) {
       /* if(holderViews instanceof DrawerFooterHolder) {
            DrawerFooterHolder holder = (DrawerFooterHolder) holderViews;
            System.out.println(" ------------ DrawerListAdapter DrawerFooterHolder drawer_Fo0ter position : "+position);

            if(musicDetails.get(position) instanceof FooterItems){
                    FooterItems dataItem = (FooterItems) musicDetails.get(position);
                    String json = gson.toJson(dataItem);
                    System.out.println(" ------------ DrawerListAdapter DrawerFooterHolder json  : "+json);

                    if(!dataItem.isEnabled()) {
                        Drawable yourDrawableNext = MaterialDrawableBuilder.with(context) // provide a context
                                .setIcon(dataItem.getIconDrawNext()) // provide an icon
                                .setColor(context.getResources().getColor(R.color.black)) // set the icon color
                                .setSizeDp(24) // set the icon size
                                .build();


                        holder.ivNext.setImageDrawable(yourDrawableNext);

                        Drawable yourDrawablePause = MaterialDrawableBuilder.with(context) // provide a context
                                .setIcon(dataItem.getIconDraw2PlayPause()) // provide an icon
                                .setColor(context.getResources().getColor(R.color.black)) // set the icon color
                                .setSizeDp(24) // set the icon size
                                .build();
                        holder.ivPlay.setImageDrawable(yourDrawablePause);
                        Drawable yourDrawablePrevious = MaterialDrawableBuilder.with(context) // provide a context
                                .setIcon(dataItem.getIconDrawPrevious()) // provide an icon
                                .setColor(context.getResources().getColor(R.color.black)) // set the icon color
                                .setSizeDp(24) // set the icon size
                                .build();
                        holder.ivPrev.setImageDrawable(yourDrawablePrevious);
                    }

                        //holder.root.setVisibility(View.GONE);

                }



        } */ if (holderViews instanceof mHeaderViewHolder ){
            MusicDetails musicDetail = musicDetails.get(position);
            //System.out.println(" ------------ DrawerListAdapter mHeaderViewHolder drawer_header position : "+position);
            final mHeaderViewHolder holder = (mHeaderViewHolder) holderViews;
           // System.out.println(" ------------ DrawerListAdapter onBindViewHolder drawer_list_menu_card position : "+position);
            if (musicDetail.getPath() != null) {
                Drawable yourDrawable = MaterialDrawableBuilder.with(context) // provide a context
                        .setIcon(MaterialDrawableBuilder.IconValue.SKIP_PREVIOUS) // provide an icon
                        .setColor(context.getResources().getColor(R.color.cardview_shadow_end_color)) // set the icon color
                        .setSizeDp(24) // set the icon size
                        .build();
                holder.ivAlbumHeader.setImageDrawable(yourDrawable);
                holder.tvSongTitleHeader.setText(musicDetail.getTitle());
                holder.tvSongSubTitleHeader.setText(musicDetail.getArtist());
                holder.tvSongSecondSubTitleHeader.setText(musicDetail.getDuration());
            }
            //holder.tvDrawerItem.setText(musicDetails.get(position).getName());*/
        }

    }


    @Override
    public int getItemCount() {
        if (musicDetails != null && musicDetails.size() > 0) {
            return musicDetails.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        //System.out.println(" ------------ DrawerListAdapter getItemViewType position : "+position);
        if(position == 0 && musicDetails.get(position) instanceof MusicDetails){
          //  System.out.println(" ------------ DrawerListAdapter onBindViewHolder getItemViewType position : "+position);
            return TYPE_HEADER;
        }
        return TYPE_FOOTER;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * ViewHolder class which holds Initialisation to all views and buttons.
     */

    public class DrawerFooterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvSongTitle;
        TextView tvSongSubTitle;
        ImageView ivAlbum;
        ImageView ivPrev;
        ImageView ivPlay;
        ImageView ivNext;
        View root;

        public DrawerFooterHolder(View itemView) {
            super(itemView);
            root = itemView;
            itemView.setOnClickListener(this);
            ivAlbum = (ImageView) itemView.findViewById(R.id.ivAlbum);
            ivPrev = (ImageView) itemView.findViewById(R.id.ivPrev);
            ivPlay = (ImageView) itemView.findViewById(R.id.ivPlay);
            ivNext = (ImageView) itemView.findViewById(R.id.ivNext);
            tvSongTitle = (TextView) itemView.findViewById(R.id.tvSongTitle);
            tvSongSubTitle = (TextView) itemView.findViewById(R.id.tvSongSubTitle);
        }

        @Override
        public void onClick(View v) {

          /*  if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
            }*/

            //delete(getPosition());

        }
    }

    public  class mHeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView tvSongTitleHeader;
        TextView tvSongSubTitleHeader;
        TextView tvSongSecondSubTitleHeader;
        ImageView ivAlbumHeader;

        public mHeaderViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivAlbumHeader = (ImageView) itemView.findViewById(R.id.ivAlbumHeader);
            tvSongTitleHeader = (TextView) itemView.findViewById(R.id.tvSongTitleHeader);
            tvSongSubTitleHeader = (TextView) itemView.findViewById(R.id.tvSongSubTitleHeader);
            tvSongSecondSubTitleHeader = (TextView) itemView.findViewById(R.id.tvSongSecondSubTitleHeader);

        }

        @Override
        public void onClick(View v) {

            if(clickListener!=null){
                clickListener.itemClicked(v,getLayoutPosition());
            }

            //delete(getPosition());

        }
    }

    public void setClickListener(ClickListener clicklistener){

        this.clickListener = clicklistener;

    }



    public interface ClickListener{
        public void itemClicked(View view, int position);
    }

    public List<MusicDetails> getData(){

        return this.musicDetails;
    }

}