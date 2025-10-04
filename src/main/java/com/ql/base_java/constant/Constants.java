package com.ql.base_java.constant;

import java.util.Map;

public class Constants {

    public static final Map<Integer, String> RESPONSE = Map.ofEntries(

            Map.entry(210, "User Registered Successfully."),
            Map.entry(211, "Users Fetched Successfully."),


            Map.entry(400, "Bad Request."),
            Map.entry(401, "Unauthorized Access."),
            Map.entry(402, "Forbidden Access."),
            Map.entry(403, "Resource Not Found."),
            Map.entry(404, "Method Not Allowed."),
            Map.entry(405, "Internal Server Error."),
            Map.entry(406, "Service Unavailable.")

    );

}
