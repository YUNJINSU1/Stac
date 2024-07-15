package com.example.strapi;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MyHttpClient extends Thread implements Serializable {
    private final String SERVER_URL = "http://10.26.143.75:80/Auth/register"; // 실제 서버 URL로 변경해야 합니다.
    private String mail, id, ps, name;

    public void postFormat(String email, String id, String ps, String name) {
        this.mail = email;
        this.id = id;
        this.ps = ps;
        this.name = name;
    }

    public void run() {
        try {
            // 쿼리 문자열 작성
            String query = "Email=" + URLEncoder.encode(mail, StandardCharsets.UTF_8.name()) +
                    "&Name=" + URLEncoder.encode(id, StandardCharsets.UTF_8.name()) +
                    "&Nickname=" + URLEncoder.encode(name, StandardCharsets.UTF_8.name()) +
                    "&Password=" + URLEncoder.encode(ps, StandardCharsets.UTF_8.name());

            // 서버 URL 생성 (쿼리 문자열을 포함)
            URL url = new URL(SERVER_URL + "?" + query);

            // HttpURLConnection 객체 생성 및 설정
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "text/plain");
            conn.setDoOutput(true);

            // 요청 바디는 비워두기
            // try (OutputStream os = conn.getOutputStream()) {
            //     byte[] input = query.getBytes(StandardCharsets.UTF_8);
            //     os.write(input, 0, input.length);
            // }

            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 응답 데이터를 읽어오는 경우, 필요하면 추가 작성 가능

            // 연결 종료
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
