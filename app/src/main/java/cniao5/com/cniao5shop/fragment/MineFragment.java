package cniao5.com.cniao5shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.squareup.picasso.Picasso;

import cniao5.com.cniao5shop.CniaoApplication;
import cniao5.com.cniao5shop.Contants;
import cniao5.com.cniao5shop.LoginActivity;
import cniao5.com.cniao5shop.R;
import cniao5.com.cniao5shop.bean.User;
import de.hdodenhof.circleimageview.CircleImageView;


public class MineFragment extends BaseFragment{



    @ViewInject(R.id.img_head)
    private CircleImageView mImageHead;

    @ViewInject(R.id.txt_username)
    private TextView mTextUserName;

    @ViewInject(R.id.btn_logout)
    private Button mBtn_logout;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine,container,false);
    }

    @Override
    public void init() {
        User user=CniaoApplication.getInstance().getUser();
        showUser(user);


    }



    @OnClick(R.id.btn_logout)
    public  void  logout(View view){

        CniaoApplication.getInstance().clearUser();
        showUser(null);
    }


    @OnClick(value ={R.id.img_head,R.id.txt_username})
    public  void  toLogin(View view){


        Intent intent=new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent, Contants.REQUEST_CODE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        User user=CniaoApplication.getInstance().getUser();
        showUser(user);

    }

    private   void  showUser(User user){

        if (user!=null){


            mTextUserName.setText(user.getUsername());

            Picasso.with(getActivity()).load(user.getLogo_url()).into(mImageHead);
            mBtn_logout.setVisibility(View.VISIBLE);

        }
        else {
            mTextUserName.setText(R.string.to_login);
            mImageHead.setImageResource(R.drawable.default_head);
            mBtn_logout.setVisibility(View.GONE);

        }


    }
}
