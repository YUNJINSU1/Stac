package com.example.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.spr.Spr;
import com.example.strapi.MyHttpClient;
import com.example.strapi.StrContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    StrContext vertex;
    List<String> perms=new ArrayList<>(Arrays.asList(new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE

    }));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
//            vertex = Spr.insert(new StrContext());
            vertex=new StrContext();
            vertex.setClient(new MyHttpClient());
        } else {
            vertex = (StrContext) savedInstanceState.getSerializable("context");
        }
//        intent=new Intent(this,MapsActivity.class);
    }
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("context", vertex);
    }
    public void mOnClick(View v){
        if(v.getId()==R.id.start){
            startActivity(new Intent(this,MapsActivity.class));
        }else if(v.getId()==R.id.check){
            tryOutContact(perms);
        }else if(v.getId()==R.id.test){
            Intent intent1=new Intent(this,TestQuery.class);
            intent1.putExtra("context",vertex);
            startActivity(intent1);
        }else if(v.getId()==R.id.friend){
            Intent intent1=new Intent(this,FriendListActivity.class);
            intent1.putExtra("context",vertex);
            startActivity(intent1);
        }else if(v.getId()==R.id.startApp){
            Intent intent1=new Intent(this,StartActivity.class);
            intent1.putExtra("context",vertex);
            startActivity(intent1);
        }else if(v.getId()==R.id.profile){
            Intent intent1=new Intent(this,ProfileActivity.class);
            intent1.putExtra("context",vertex);
            startActivity(intent1);
        }
    }
    static final int PERMISSION_GROUP=3;
    private void checkForPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                Toast.makeText(this, "Please grant the requested permission to get your task done!", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if(grantResults.length>0){
                    String msg="denied permission list:\n";
                    for(int i=0;i<grantResults.length;i++){
                        if(grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                            msg+=permissions[i]+"\n";
                        }
                    }
                }
                break;
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission with request code 1 granted
                    Toast.makeText(this, "Permission Granted" , Toast.LENGTH_LONG).show();
                } else {
                    //permission with request code 1 was not granted
                    Toast.makeText(this, "Permission was not Granted" , Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public void tryOutContact(List<String>perm){
        AtomicBoolean show= new AtomicBoolean(false);
        List<String>result=new ArrayList<>();
        perm.forEach(item->{
            if(ContextCompat.checkSelfPermission(this,item)==PackageManager.PERMISSION_DENIED){
                if(shouldShowRequestPermissionRationale(item)){
                    show.set(true);
                    result.add(item);
                }else{
                    result.add(item);
                }
            }
        });
        if(!result.isEmpty()){
            if(show.get()){
                new AlertDialog.Builder(this).setTitle("permission").setMessage(perm.toArray().toString())
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                requestPermissions(result.toArray(new String[result.size()]),0);
                            }
                        }).setNegativeButton("no",null).show();
            }else{
                requestPermissions(result.toArray(new String[result.size()]),0);
            }
        }
    }
}