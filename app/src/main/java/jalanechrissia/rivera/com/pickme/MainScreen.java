package jalanechrissia.rivera.com.pickme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainScreen extends AppCompatActivity {

    private static final int PICK_REQUEST = 888;
    private ImageView mCamera;
    private ImageView mSelectPhoto;
    private Button mBtnShare;
    private Uri mSelectedPhotoUri;
    private TextView mTxtPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        findViews();
    }

    private void findViews() {
        mCamera = (ImageView) findViewById(R.id.camera);
        mSelectPhoto = (ImageView) findViewById(R.id.selectedPhoto);
        mBtnShare = (Button) findViewById(R.id.share);
        mTxtPick = (TextView) findViewById(R.id.pick);
    }

    public void pick(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        Intent chooser = Intent.createChooser(intent, "Pick a photo");
        startActivityForResult(chooser, PICK_REQUEST);
        mBtnShare.setVisibility(View.VISIBLE);
        mCamera.setVisibility(View.GONE);
        mTxtPick.setVisibility(View.GONE);

    }

    public void share(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, mSelectedPhotoUri);

        Intent chooser = Intent.createChooser(intent, "Share photo");
        startActivity(chooser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_REQUEST && resultCode == RESULT_OK){
            mSelectPhoto.setVisibility(View.VISIBLE);
            mSelectedPhotoUri = data.getData();
            mSelectPhoto.setImageURI(mSelectedPhotoUri);
        }
    }
}
