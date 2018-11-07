package com.utilnepal.AudioRecorder.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.utilnepal.AudioRecorder.Files.FileNames;
import com.utilnepal.MobileHelp.adapters.EmergencyNumberAdapter;
import com.utilnepal.R;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecordingAdapter extends RecyclerView.Adapter<RecordingAdapter.RecordingViewHolder> {

    private ArrayList<FileNames> fileNames;
    private Context c;
    private MediaPlayer mPlayer;
    private ImageView playButton;
    private ImageView pauseButton;

    public RecordingAdapter(ArrayList<FileNames> fileNames, Context c, MediaPlayer mplayer)
    {
        this.fileNames = fileNames;
        this.c= c;
        this.mPlayer = mplayer;
    }

    @NonNull
    @Override
    public RecordingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(c).inflate(R.layout.each_recording_view,viewGroup,false);

        RecordingViewHolder recordingViewHolder = new RecordingViewHolder(v);
        return recordingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordingAdapter.RecordingViewHolder recordingViewHolder, int position) {
                final String source = fileNames.get(position).getFilename();
                recordingViewHolder.recordingName.setText( fileNames.get(position).getFilename());
                playButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playFile(source);
                    }
                });

                pauseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pauseFile();
                    }
                });

    }

    @Override
    public int getItemCount() {
        return fileNames.size();
    }

    public class RecordingViewHolder extends RecyclerView.ViewHolder {

        private TextView recordingName;


        public RecordingViewHolder(@NonNull View itemView) {
            super(itemView);
            recordingName = itemView.findViewById(R.id.eachFileName);
            playButton = itemView.findViewById(R.id.playButton);
            pauseButton = itemView.findViewById(R.id.pauseButton);
        }
    }

        private void playFile(String source) {
            mPlayer = new MediaPlayer();
            try {
                mPlayer.setDataSource(source);
                mPlayer.prepare();
                mPlayer.start();
                changePauseIcon();
            } catch (IOException e) {
                Log.e("Error Occured", "prepare() failed " +e.getMessage());
                Toast.makeText(c,"Cannot Play File, Error",Toast.LENGTH_LONG).show();
                changePlayIcon();
            }
        }

        private void pauseFile() {
            if (mPlayer != null) {
                mPlayer.release();
                mPlayer = null;
                changePlayIcon();
            }
        }

        private void changePauseIcon() {
            if (playButton.getVisibility() == View.VISIBLE) {
                playButton.setVisibility(View.GONE);
                pauseButton.setVisibility(View.VISIBLE);
            }
        }

        private void changePlayIcon() {
            if (pauseButton.getVisibility() == View.VISIBLE) {
                playButton.setVisibility(View.VISIBLE);
                pauseButton.setVisibility(View.GONE);
            }
        }

}
