package com.sparky.safefromfire.screens.report;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.sparky.safefromfire.R;
import com.sparky.safefromfire.SessionManager;
import com.sparky.safefromfire.base.BaseActivity;
import com.sparky.safefromfire.utils.BitmapUtils;
import com.sparky.safefromfire.utils.PermissionUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Erkut Demirhan on 30/04/17.
 */
public class SendPictureActivity extends BaseActivity {

    private static final int TAKE_PHOTO_REQUEST_CODE = 3131;
    private static final String CAMERA_IMAGE_FILENAME = "camera_image.png";

    @BindView(R.id.reportactivity_picture)
    ImageView pictureIw;

    private Uri cameraImageFileUri;
    private Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_sendpic);
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if(!PermissionUtils.hasPermissions(SendPictureActivity.this, permissions)) {
            requestPermissions(permissions, 0);
        }
    }

    @Override
    protected void setupViews() {

    }

    @OnClick(R.id.reportactivity_takepicture)
    public void onTakePicturePressed() {
        takePictureFromCamera();
    }

    private void takePictureFromCamera() {
        final String dir        = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/FoodApp/";
        File cameraImageFileDir = new File(dir);
        cameraImageFileDir.mkdirs();
        File cameraImageFile    = new File(dir + CAMERA_IMAGE_FILENAME);
        try {
            cameraImageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cameraImageFileUri  = Uri.fromFile(cameraImageFile);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageFileUri);
        if(cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, TAKE_PHOTO_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TAKE_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if(cameraImageFileUri != null) {
                try {
                    selectedImage = BitmapUtils.decodeUri(SendPictureActivity.this, cameraImageFileUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if(data != null) {
                Bundle extras = data.getExtras();
                selectedImage = (Bitmap) extras.get("data");
            }
            if(selectedImage != null) {
                pictureIw.setImageBitmap(selectedImage);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.reportactivity_nextbutton)
    public void onNextButtonPressed() {
        final Location userLocation = SessionManager.getInstance(SendPictureActivity.this).getUserLocation();
        final Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?" + "saddr="+ userLocation.getLatitude() + "," + userLocation.getLongitude() + "&daddr=" + 44.452760 + "," + 26.085811));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.reportactivity_backbutton)
    public void onBackButtonPressed() {
        finish();
    }
}
