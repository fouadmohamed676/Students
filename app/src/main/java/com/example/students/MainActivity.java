package com.example.students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionBarPolicy;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Maindata>dataArrayList=new ArrayList();
//        String net_url = "http://iparkingappksa.com/students_app/show/students.php";

    public String net_url = "https://127.0.0.1/studentApi/students.php";

    Context context = this;
    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        VolleyAdapter va;
//        va = new VolleyAdapter();
        lstView = findViewById(R.id.students_list_view);

        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();



        StringRequest stringRequest= new StringRequest(net_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response !=null){
                    progressDialog.dismiss();
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        paseArray(jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue Queue= Volley.newRequestQueue(this);
            Queue.add(stringRequest);

    }

    private void paseArray(JSONArray jsonArray) {
        for (int i=0;i < jsonArray.length();i ++){
            try {
                JSONObject object= jsonArray.getJSONObject(i);
                Maindata data=new Maindata();
                data.setName(object.getString("name"));
                data.setAge(object.getInt("age"));
                data.setDate(object.getInt("date"));
                dataArrayList.add(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listView.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return dataArrayList.size();
                }

                @Override
                public Object getItem(int i) {
                    return null;
                }

                @Override
                public long getItemId(int i) {
                    return 0;
                }

                @Override
                public View getView(int postion, View convertView, ViewGroup viewGroup) {
                   View view=getLayoutInflater().inflate(R.layout.students,null);
                    Maindata data=dataArrayList.get(postion);

                    TextView tv_name=findViewById(R.id.text_name);
                    TextView tv_age=findViewById(R.id.textage);
                    TextView tv_date=findViewById(R.id.textdate);

                    tv_name.setText(data.getName());
                    tv_age.setText(data.getAge());
                    tv_date.setText(data.getDate());

                    return view;
                }
            });
        }

    }


//
//    ArrayList<NewsModel> arrNews = new ArrayList<>();
//
//    private void checkApi() {
//        ProgressDialog pd = ProgressDialog.show(this, "جاري...", "التحقق من البيانات ...", false, false);
//
////        String net_url = "http://iparkingappksa.com/students_app/show/students.php";
//
//
//                StringRequest jr = new StringRequest(
//                        Request.Method.GET,
//                        net_url, response -> {
////            Log.e(TAG, response);
//            pd.dismiss();
//            parseJSON(response);
//            tost_done();
////            va.notifyDataSetChanged();
//        }, error -> {
//            pd.dismiss();
//            tost_fo();
//            Log.e("error Response ", error.toString());
////            Log.e(TAG, error.getMessage());
//
//        });
//        Volley.newRequestQueue(this).add(jr);
//    }
//
//
//    private void getpashin(int id) {
//        ProgressDialog pd = ProgressDialog.show(this, "جاري...", "جلب البيانات ...", false, false);
//        StringRequest jr = new StringRequest(Request.Method.GET, "http://iparkingappksa.com/students_app/show/students.php"+id, response -> {
////            Log.e(TAG, response);
//            pd.dismiss();
//            try {
//
//                JSONArray jsonArray = new JSONArray(response);
//
//
//                JSONObject item = jsonArray.getJSONObject(0);
//                String name = item.getString("name");
//                String age = String.valueOf(item.getInt("age"));
//                String date= String.valueOf(item.getInt("date"));
//
//
////                phone	name	blood	address	comment	email	password	date
//                startActivity(new Intent(context,MainActivity.class).putExtra("name",name).putExtra("age",age).putExtra("date",date));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//        }, error -> {
//            pd.dismiss();
//            Log.e("error Response ", error.toString());
////            Log.e(TAG, error.getMessage());
//
//        }) {
////            @Nullable
////            @Override
////            protected Map<String, String> getParams() {
////                HashMap<String, String> map = new HashMap<>();
////                map.put("id", String.valueOf(id));
////                return map;
////            }
//
//        };
//
//        Volley.newRequestQueue(this).add(jr);
//    }
//
////    private void dooone(int id) {
////        startActivity(new Intent(context,DoneSend.class));
////
////    }
//
//    private void parseJSON(String json) {
//
//        try {
//
//            JSONArray jsonArray = new JSONArray(json);
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject item = jsonArray.getJSONObject(i);
//                NewsModel nm = new NewsModel();
//                nm.setId(Integer.parseInt(String.valueOf(item.getInt("id"))));
//                nm.setName(item.optString("name"));
//                nm.setAge(item.optInt("age"));
////                nm.setPhone(Integer.parseInt(String.valueOf(item.getInt("phone"))));
//                nm.setDate(item.optInt("date"));
//                arrNews.add(nm);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    class NewsModel {
//        //        private int phone;
//        public Integer date;
//        public int id;
//        public String name;
//        public Integer Age;
//
//
////        public int getPhone() {
////            return phone;
////        }
//
////        public void setPhone(int phone) {
////            this.phone = phone;
////        }
//
//        public Integer getDate() {
//            return date;
//        }
//
//        public void setDate(Integer date) {
//            this.date = date;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        void setName(String name) {
//            this.name = name;
//        }
//
//        void setAge(Integer Age) {
//            this.Age = Age;
//        }
//
//        Integer getAge() {
//            return Age;
//        }
//
//        String getName() {
//
//            return name;
//        }
//
//        public int getAget() {
//            return 0;
//        }
//    }
//
//
//    public class VolleyAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return arrNews.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return arrNews.get(i);
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
////        @Override
////        public View getView(int i, View view, ViewGroup viewGroup) {
////            return null;
////        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            ViewHolder vh;
//            if (view == null) {
//
//                vh = new ViewHolder();
//                view = LayoutInflater.from(getBaseContext()).inflate(R.layout.students, null);
//                vh.tvName = view.findViewById(R.id.text_name);
//                vh.tvAge = view.findViewById(R.id.textage);
//                vh.tvDate = view.findViewById(R.id.textdate);
//
//                //on click Listener
//
//                view.findViewById(R.id.leen).setOnClickListener(view1 -> getpashin(arrNews.get(i).id));
////                view.findViewById(R.id.donsnd).setOnClickListener(view1 -> dooone(arrNews.get(i).id));
//
//                view.setTag(vh);
//            } else {
//                vh = (ViewHolder) view.getTag();
//            }
//
//            NewsModel nm = arrNews.get(i);
//            vh.tvName.setText(nm.getName());
//            vh.tvAge.setText(nm.getAget());
//            vh.tvDate.setText(nm.getDate());
//
//            return view;
//        }
//
//        class ViewHolder {
//            TextView tvAge;
//            TextView tvDate;
//            TextView tvName;
//            LinearLayout layout;
//
//        }
//
//    }

    void tost_fo() {
        Toast toast = Toast.makeText(this, "Error", Toast.LENGTH_SHORT);
        toast.show();
    }

    void tost_done() {
        Toast toast = Toast.makeText(this, "Done Response", Toast.LENGTH_SHORT);
        toast.show();
    }
    void tost_id() {
        Toast toast = Toast.makeText(this, "Please Try Again Later", Toast.LENGTH_SHORT);
        toast.show();
    }

}