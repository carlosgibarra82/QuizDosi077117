package com.athena.quizdosi077117.Parser;


import com.athena.quizdosi077117.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUser {

    public static List<User> getDataJson(String s) throws JSONException {
        //
        JSONArray jsonArray = new JSONArray(s);
        List<User> UserList = new ArrayList<>();


        for (int i =0; i< jsonArray.length(); i++)
        {
            JSONObject item = jsonArray.getJSONObject(i);

            User User = new User();

            User.setName(item.getString("name"));
            User.setUsername(item.getString("username"));
            User.setEmail(item.getString("email"));
            User.setStreet(item.getJSONObject("address").get("street").toString());

            UserList.add(User);
        }

        return UserList;
    }

}
