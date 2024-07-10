package com.example.myapplication;

import android.util.Log;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class test {

    private static final String SERVER_URL = "http://10.26.143.75:80/Auth/register"; // 실제 서버 URL로 변경해야 합니다.
    public void sendPostRequest() {
        try {
            // 서버 URL 생성
            URL url = new URL(SERVER_URL);

            // HttpURLConnection 객체 생성 및 설정
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 요청 바디 데이터 작성
            String jsonInputString = "{ \"email\": \"1\", \"name\": \"yun\", \"password\": \"asd\" }";

            // 요청 바디 데이터를 OutputStream을 사용해 전송
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);

            }

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
