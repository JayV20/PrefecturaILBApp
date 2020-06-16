package com.example.prefecturailb.moduleAccount.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prefecturailb.R;
import com.example.prefecturailb.common.pojo.Maestro;
import com.example.prefecturailb.moduleAccount.AccountPresenter;
import com.example.prefecturailb.moduleAccount.AccountPresenterClass;
import com.example.prefecturailb.moduleAccount.adapters.MaestrosAdapter;
import com.example.prefecturailb.moduleAccount.adapters.OnItemClickListener;
import com.example.prefecturailb.moduleAccount.utils.Constants;
import com.example.prefecturailb.moduleLogin.view.MainActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Jay Vega, Francisco Briseño, Jacqueline Ramirez.
 */
public class AccountActivity extends AppCompatActivity implements AccountView, OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab1)
    FloatingActionButton fab1;
    @BindView(R.id.fab3)
    FloatingActionButton fab3;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private AccountPresenter mPresenter;
    private MaestrosAdapter mAdapter;
    /**
     * ButterKnife dependence.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        mPresenter = new AccountPresenterClass(this);
        mPresenter.onCreate();
        configAdapter();
        configRecyclerView();
        setSupportActionBar(toolbar);
    }

    private void configRecyclerView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(mAdapter);
    }

    private void configAdapter() {
        mAdapter = new MaestrosAdapter( new ArrayList<Maestro>(), this);
    }

    //Método de la interface OnItemClickListener
    @Override
    public void onItemClicked(Maestro maestro) {
        /*
        Intent intent = new Intent(AccountActivity.this, MateriaActivity.class);
        intent.putExtra(User.NAME,maestro.getNombre());
        startActivity(intent);
        finish();*/
    }

    @Override
    public void onGetList(ArrayList<Maestro> maestros) {
        mAdapter.setMaestros(maestros);
    }



    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @OnClick(R.id.fab1)
    public void onViewClicked_SCAN() {
        mPresenter.openScan();
    }

    @OnClick(R.id.fab3)
    public void onViewClicked_LOGOUT() {
        mPresenter.signOut();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.onPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onResult(requestCode,resultCode,data);
    }

    private void checkPermissionsToApp(String permissionStr, int requestPermission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(AccountActivity.this, permissionStr) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AccountActivity.this, new String[]{permissionStr}, requestPermission);
            }
            return;
        }
    }

    @Override
    public void openScan() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(AccountActivity.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("LECTOR DE QR");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.initiateScan();
    }


    @Override
    public void checkPermissionsToApp() {
        checkPermissionsToApp(Manifest.permission.WRITE_EXTERNAL_STORAGE, Constants.RC_CAMERA);
    }

    @Override
    public void onOpenLogin() {
        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onMessage(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}
