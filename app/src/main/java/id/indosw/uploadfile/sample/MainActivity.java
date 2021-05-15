package id.indosw.uploadfile.sample;

import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import id.indosw.uploadfile.service.UploadService;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.ftp_upload)
    public void onFTPUpload(View view) {
        FTPUploadActivity.show(this);
    }

    @OnClick(R.id.cancelAllUploadsButton)
    public void onCancelAllUploadsButtonClick() {
        UploadService.stopAllUploads();
    }

}