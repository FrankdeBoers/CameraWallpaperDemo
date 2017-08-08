package ghc.camerawallpaperdemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1024;
    private Context context;
    static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        findViewById(R.id.btn_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAllSelfPermission();
            }
        });


    }


    public void checkAllSelfPermission() {
        if (ContextCompat.checkSelfPermission(context, PERMISSION_CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{PERMISSION_CAMERA},
                    PERMISSION_REQUEST_CODE);
        } else {
            startCameraWallpaper();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    setTransparentWallpaper();
                    startCameraWallpaper();

                } else {
                    Toast.makeText(context, "Please open permissions", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void startCameraWallpaper() {
        final Intent systemWallpaperPick = new Intent(Intent.ACTION_SET_WALLPAPER);
        Intent chooser = Intent.createChooser(systemWallpaperPick, "Choose wallpaper.");

        startActivity(chooser);
    }
}
