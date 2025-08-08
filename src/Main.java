import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.security.*;
import org.json.*;

public class Main {
    public static String conversationid = null;
    public static String msg = "当贝";
    public static String model = null;
    public static List<Object> options = new ArrayList();

    public static void main(String[] args) throws JSONException, IOException {
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("支持的命令：当贝、创建新聊天+数字、聊天列表、删除聊天+数字、查看聊天记录+数字、继续聊天+数字");
            System.out.print("请输入命令或者聊天内容: ");
            String msg = reader.readLine();
            reader.close();

            if (msg.equals("当贝")) {//使用“当贝”命令查看可用聊天模型
                System.out.println("----------当贝----------");
                System.out.println("① DeepSeek-R1 最新版");
                System.out.println("② DeepSeek-R1 最新版(深度思考)");
                System.out.println("③ DeepSeek-R1 最新版(联网)");
                System.out.println("④ DeepSeek-R1 最新版(深度思考、联网)");
                System.out.println("⑤ 豆包-1.6");
                System.out.println("⑥ 豆包-1.6(深度思考)");
                System.out.println("⑦ 豆包-1.6(联网)");
                System.out.println("⑧ 豆包-1.6(深度思考、联网)");
                System.out.println("⑨ DeepSeek-V3");
                System.out.println("⑩ DeepSeek-V3(联网)");
                System.out.println("⑪ GLM-4.5");
                System.out.println("⑫ GLM-4.5(深度思考)");
                System.out.println("⑬ GLM-4.5(联网)");
                System.out.println("⑭ GLM-4.5(深度思考、联网)");
                System.out.println("⑮ 通义3-235B 最新版");
                System.out.println("⑯ 通义3-235B 最新版(深度思考)");
                System.out.println("⑰ 通义3-235B 最新版(联网)");
                System.out.println("⑱ 通义3-235B 最新版(深度思考、联网)");
                System.out.println("⑲ Kimi K2");
                System.out.println("⑳ Kimi K2(联网)");
                System.out.println("㉑ MiniMax-M1");
                System.out.println("㉒ MiniMax-M1(深度思考)");
                System.out.println("㉓ MiniMax-M1(深度思考)");
                System.out.println("㉔ MiniMax-M1(深度思考)");
                System.out.println("㉕ GLM-4-Plus");
                System.out.println("㉖ GLM-4-Plus(联网)");
                System.out.println("㉗ 豆包");
                System.out.println("㉘ 豆包(联网)");
                System.out.println("㉙ 通义Plus");
                System.out.println("㉚ 通义Plus(联网)");
                System.out.println("㉛ Kimi");
                System.out.println("㉜ Kimi(联网)");
                System.out.println("㉝ 通义QwQ");
                System.out.println("㉞ 通义QwQ(深度思考)");
                System.out.println("㉟ 通义QwQ(联网)");
                System.out.println("㊱ 通义QwQ(深度思考、联网)");
                System.out.println("㊲ 通义Long");
                System.out.println("㊳ 通义Long(联网)");
                System.out.println("㊴ 豆包-1.5");
                System.out.println("㊵ 豆包-1.5(深度思考)");
                System.out.println("㊶ 豆包-1.5(联网)");
                System.out.println("㊷ 豆包-1.5(深度思考、联网)");
                System.out.println("㊸ 文心4.5");
                System.out.println("㊹ 文心4.5(联网)");
                System.out.println("㊺");
                System.out.println("㊻");
                System.out.println("㊼");
                System.out.println("㊽");
                System.out.println("㊾");
                System.out.println("㊿");
                System.out.println("---------AI聊天--------");
            } else if (msg.startsWith("创建新聊天")) {//使用“创建新聊天”命令创建一个新的聊天会话，使用“创建新聊天 数字”选择要创建的聊天会话
                try {
                    int value = Integer.parseInt(msg.replaceAll("\\D", ""));
                    model = null; options.clear();
                    switch (value) {
                        case 1: model = "deepseek"; break;
                        case 2: model = "deepseek";options.add("deep");break;
                        case 3: model = "deepseek";options.add("deep");options.add("online");break;
                            // 。。。
                    }
                    conversationid = createConversation(model, options);//调用该方法创建一个新的聊天会话，返回会话ID
                    System.out.println("创建成功 !");
                } catch (Exception e) {
                    System.out.println("请选择数字！" + e);
                }
            } else if (msg.equals("聊天列表")) {//使用命令“聊天列表”查看已经创建的聊天会话
                JSONArray chatList = chatList();//调用该方法获取已经创建的聊天会话
                if (chatList.length() > 1) {
                    for (int i = 0; i < chatList.length(); i++) {
                        String conversationId = chatList.getJSONArray(i).getString(0);
                        System.out.println(chatList.getJSONArray(0).getString(0));
                        String title = chatList.getJSONArray(i).getString(1);
                        String model = chatList.getJSONArray(i).getString(2);
                        JSONArray options = chatList.getJSONArray(i).getJSONArray(3);

                        System.out.println("第 " + (i + 1) + " 组数据:");
                        System.out.println("对话ID: " + conversationId);
                        System.out.println("标题: " + title);
                        System.out.println("模型: " + model);
                        System.out.println("选项: " + options);
                        System.out.println("-------------------");
                    }
                } else if (chatList.length() == 1) {
                    System.out.println(chatList.optString(0));
                } else if (chatList.length() < 1) {
                    System.out.println("没有聊天会话");
                }
            } else if (msg.startsWith("删除聊天")) {//使用命令“删除聊天 数字”删除指定聊天会话
                try {
                    int i = Integer.parseInt(msg.replaceAll("\\D", "")) - 1;
                    JSONArray chatList = chatList();//调用该方法获取已经创建的聊天会话
                    String conversationId =  chatList.getJSONArray(i).getString(0);//获取会话ID：conversationId
                    System.out.println(deleteConversation(conversationId));//调用该方法删除指定聊天会话，参数为chatList()方法获取的conversationId，返回删除成功：true，失败：false
                } catch (Exception e) {
                    System.out.println("请输入数字！");
                }
            } else if (msg.startsWith("查看聊天记录")) {////使用命令“查看聊天记录 数字”查看指定聊天会话的具体聊天内容
                try {
                    int i = Integer.parseInt(msg.replaceAll("\\D", "")) - 1;
                    JSONArray chatList = chatList();//调用该方法获取已经创建的聊天会话
                    String conversationId =  chatList.getJSONArray(i).getString(0);//获取会话ID：conversationId
                    String[] chatHistory = chatHistory(conversationId);//调用该方法查询指定会话的聊天记录，参数为会话ID，正常返回聊天记录标题和聊天记录，错误返回错误内容
                    if (chatHistory.length > 1) {
                        System.out.println("标题：" + chatHistory[0]);
                        System.out.println("聊天记录：" + chatHistory[1]);
                    } else {
                        System.out.println(chatHistory[0]);
                    }
                } catch (Exception e) {
                    System.out.println("请选择数字！");
                }
            } else if (msg.startsWith("继续聊天")) {////使用命令“继续聊天 数字”继续指定聊天会话聊天
                try {
                    int i = Integer.parseInt(msg.replaceAll("\\D", "")) - 1;
                    System.out.println(i);
                    JSONArray chatList = chatList();//调用该方法获取已经创建的聊天会话
                    conversationid =  chatList.getJSONArray(i).getString(0);//获取会话ID：conversationId
                    model = chatList.getJSONArray(i).getString(2);
                    JSONArray op = chatList.getJSONArray(i).getJSONArray(3);
                    options.clear();
                    if (model != null && conversationid != null && model != null) {
                        String userAction = "";
                        if (op.length() == 1) {
                            userAction = op.optString(0);
                            options.add(userAction);
                        } else if (op.length() == 2) {
                            userAction = op.optString(0) + "," + op.optString(1);
                            options.add(op.optString(0));
                            options.add(op.optString(1));
                        }
                        System.out.println("已继续该聊天，请发送聊天内容，进行聊天");
                    }
                } catch (Exception e) {
                    System.out.println("请输入命令+数字！");
                }
            } else {//聊天
                if (model != null && conversationid != null) {
                    String userAction = "";
                    if (options.size() == 1) {
                        userAction = options.get(0).toString();
                    } else if (options.size() == 2) {
                        userAction = options.get(0).toString() + "," + options.get(1).toString();
                    }
                    System.out.println((chat(conversationid, msg, userAction, model)));//调用该方法进行聊天，返回AI回答，参数为会话ID、问题、深度思考和联网、模型
                } else {
                    System.out.println("当前无聊天模型，请先择一个聊天会话或者继续一个聊天会话");
                }
            }
        }
    }

    /**
     * 创建新会话
     * model：聊天模型
     * options: 深度思考、联网查询
     */
    public static String createConversation(String model, List options) {
        StringBuilder fullContent = new StringBuilder();
        try {
            // 准备请求参数
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String key = "nLwtHf7QZbm5FLXTlWJLZ";

            // 构建请求体
            // 构建请求体
            Map<String, Object> requestBodyMap = new LinkedHashMap<>();

            Map<String, Object> metaData = new LinkedHashMap<>();
            Map<String, Object> chatModelConfig = new LinkedHashMap<>();
            chatModelConfig.put("model", model);
            chatModelConfig.put("options", options);  // 使用ArrayList而不是Map

            metaData.put("chatModelConfig", chatModelConfig);
            requestBodyMap.put("metaData", metaData);

            JSONObject requestBody = new JSONObject(requestBodyMap);

            // 请求体键名
            List<String> keys = Arrays.asList("metaData");

            // 生成签名
            String sign = generateSign(timestamp, requestBodyMap, key, keys);

            // 设置请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("Host", "ai-api.dangbei.net");
            headers.put("Connection", "keep-alive");
            headers.put("Content-Length", String.valueOf(requestBody.toString().getBytes(StandardCharsets.UTF_8).length));
            headers.put("deviceId", "1752c6df7d8e442792a079470fb4642e");
            headers.put("Origin", "file://");
            headers.put("nonce", key);
            headers.put("appType", "1");
            headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 10; PEMT00 Build/RP1A.200720.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.186 Mobile Safari/537.36");
            headers.put("Content-Type", "application/json");
            headers.put("localName", "OP4E2F");
            headers.put("timestamp", timestamp);
            headers.put("appVersionCode", "111");
            headers.put("channel", "znds");
            headers.put("sign", sign);
            headers.put("appVersion", "1.1.1");
            headers.put("Accept", "*/*");
            headers.put("Accept-Encoding", "gzip, deflate");
            headers.put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
            headers.put("X-Requested-With", "com.dangbei.ai");

            // 发送请求
            URL url = new URL("https://ai-api.dangbei.net/ai-search/app/conversationApi/v1/create");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // 设置请求头
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // 写入请求体
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
            }
            //获取响应
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {

                    JSONObject data = new JSONObject(reader.readLine());
                    fullContent.append(data.optJSONObject("data").optString("conversationId"));
                }
                return fullContent.toString();
            } else {
                return "请求失败!";
            }

        } catch (Exception e) {
            return "发生错误：" + e;
        }
    }

    /**
     * 删除聊天会话
     * conversationId：会话ID
     */
    public static boolean deleteConversation(String conversationId) {
        try {
            // 准备请求参数
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String key = "1c697148312f4eb087faaff7d7c77e6b";

            // 生成签名
            String raw = timestamp + key;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(raw.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            String sign = sb.toString().toLowerCase();

            String url = "https://ai-api.dangbei.net/ai-search/app/conversationApi/v1/delete";
            String jsonInputString = "{\"conversationId\":\"" + conversationId + "\"}";

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("DELETE");

            // Set headers
            connection.setRequestProperty("user-agent", "Dart/3.7 (dart:io)");
            connection.setRequestProperty("appversion", "1.1.1");
            connection.setRequestProperty("accept-encoding", "gzip");
            connection.setRequestProperty("channel", "znds");
            connection.setRequestProperty("apptype", "1");
            connection.setRequestProperty("content-type", "application/json");
            connection.setRequestProperty("timestamp", timestamp);
            connection.setRequestProperty("token", "");
            connection.setRequestProperty("sign", sign);
            connection.setRequestProperty("appversioncode", "111");
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("localid", "1752c6df7d8e442792a079470fb4642e");
            connection.setRequestProperty("localname", "OP4E2F");
            connection.setRequestProperty("host", "ai-api.dangbei.net");
            connection.setRequestProperty("nonce", key);
            connection.setRequestProperty("Content-Length", "39");

            // Enable output and send request body
            connection.setDoOutput(true);
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            //获取响应
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    JSONObject data = new JSONObject(reader.readLine());
                    return data.getBoolean("success");
                }
            } else {
                try (BufferedReader errorReader = new BufferedReader(
                    new InputStreamReader(connection.getErrorStream()))) {
                    JSONObject data = new JSONObject(errorReader.readLine());
                    System.out.println(data);
                    return data.getBoolean("success");
                }

            }

        } catch (Exception e) {
            System.out.println("发生错误！" + e);
            return false;
        }
    }

    /**
     * 聊天列表
     */
    public static JSONArray chatList() {
        JSONArray Result = new JSONArray();

        try {
            // 准备请求参数
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String key = "nLwtHf7QZbm5FLXTlWJLZ";

            // 构建请求体
            Map<String, Object> requestBodyMap = new LinkedHashMap<>();
            requestBodyMap.put("timestamp", 0);

            JSONObject requestBody = new JSONObject(requestBodyMap);

            // 请求体键名
            List<String> keys = Arrays.asList("timestamp");

            // 生成签名
            String sign = generateSign(timestamp, requestBodyMap, key, keys);

            // 设置请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("Host", "ai-api.dangbei.net");
            headers.put("Connection", "keep-alive");
            headers.put("Content-Length", String.valueOf(requestBody.toString().getBytes(StandardCharsets.UTF_8).length));
            headers.put("deviceId", "1752c6df7d8e442792a079470fb4642e");
            headers.put("Origin", "file://");
            headers.put("nonce", key);
            headers.put("appType", "1");
            headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 10; PEMT00 Build/RP1A.200720.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.186 Mobile Safari/537.36");
            headers.put("Content-Type", "application/json");
            headers.put("localName", "OP4E2F");
            headers.put("timestamp", timestamp);
            headers.put("appVersionCode", "111");
            headers.put("channel", "znds");
            headers.put("sign", sign);
            headers.put("appVersion", "1.1.1");
            headers.put("Accept", "*/*");
            headers.put("Accept-Encoding", "gzip, deflate");
            headers.put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
            headers.put("X-Requested-With", "com.dangbei.ai");

            // 发送请求
            URL url = new URL("https://ai-api.dangbei.net/ai-search/app/conversationApi/v1/list");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // 设置请求头
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // 写入请求体
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
            }
            //获取响应
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    JSONObject data = new JSONObject(reader.readLine());
                    JSONArray msgList = data.getJSONArray("data");
                    for (int i = 0; i < msgList.length(); i++) {
                        JSONArray content = new JSONArray();
                        JSONObject msg = msgList.getJSONObject(i);
                        content.put(msg.optString("conversationId"));
                        content.put(msg.getString("title"));
                        content.put(msg.getJSONObject("metaData").getJSONObject("chatModelConfig").getString("model"));
                        content.put(msg.getJSONObject("metaData").getJSONObject("chatModelConfig").getJSONArray("options"));
                        Result.put(content);
                    }
                }
                return Result;
            } else {
                return new JSONArray("请求失败!" + connection.getResponseCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("发生错误：", e);
        }
    }

    /**
     * 聊天记录
     * conversationId：会话ID
     */
    public static String[] chatHistory(String conversationId) {
        StringBuilder content = new StringBuilder();
        String title;
        try {
            // 准备请求参数
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String key = "nLwtHf7QZbm5FLXTlWJLZ";

            // 构建请求体
            Map<String, Object> requestBodyMap = new LinkedHashMap<>();
            requestBodyMap.put("conversationId", conversationId);
            requestBodyMap.put("order", "desc");
            requestBodyMap.put("limit", 100);

            JSONObject requestBody = new JSONObject(requestBodyMap);

            // 请求体键名
            List<String> keys = Arrays.asList("conversationId", "order", "limit");

            // 生成签名
            String sign = generateSign(timestamp, requestBodyMap, key, keys);

            // 设置请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("Host", "ai-api.dangbei.net");
            headers.put("Connection", "keep-alive");
            headers.put("Content-Length", String.valueOf(requestBody.toString().getBytes(StandardCharsets.UTF_8).length));
            headers.put("deviceId", "1752c6df7d8e442792a079470fb4642e");
            headers.put("Origin", "file://");
            headers.put("nonce", key);
            headers.put("appType", "1");
            headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 10; PEMT00 Build/RP1A.200720.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.186 Mobile Safari/537.36");
            headers.put("Content-Type", "application/json");
            headers.put("localName", "OP4E2F");
            headers.put("timestamp", timestamp);
            headers.put("appVersionCode", "111");
            headers.put("channel", "znds");
            headers.put("sign", sign);
            headers.put("appVersion", "1.1.1");
            headers.put("Accept", "*/*");
            headers.put("Accept-Encoding", "gzip, deflate");
            headers.put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
            headers.put("X-Requested-With", "com.dangbei.ai");

            // 5. 发送请求
            URL url = new URL("https://ai-api.dangbei.net/ai-search/app/messageApi/v1/list");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // 设置请求头
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // 写入请求体
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
            }
            //获取响应
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    JSONObject data = new JSONObject(reader.readLine());
                    title = data.getJSONObject("data").getString("conversationTitle");
                    JSONArray msgList = data.getJSONObject("data").getJSONArray("msgList");
                    for (int i = msgList.length() - 1; i >= 0; i--) {
                        JSONObject msg = msgList.getJSONObject(i);
                        if (i % 2 != 0) {
                            content.append(msg.getString("question") + "ꙕ");
                        } else {
                            content.append(msg.getString("content"));
                        }
                    }
                }
                return new String[]{title, content.toString()};
            } else {
                return new String[]{"请求失败!" + connection.getResponseCode()};
            }

        } catch (Exception e) {
            return new String[]{"发生错误：" + e};
        }
    }

    /**
     * 聊天
     * conversationId：会话ID
     * question：问题
     * userAction：深度思考、联网
     * model：聊天模型
     */
    public static String chat(String conversationId, String question, String userAction, String model) {
        StringBuilder fullContent = new StringBuilder();
        try {
            // 准备请求参数
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String key = "nLwtHf7QZbm5FLXTlWJLZ";

            // 构建请求体
            Map<String, Object> requestBodyMap = new LinkedHashMap<>();
            requestBodyMap.put("stream", true);
            requestBodyMap.put("botCode", "AI_SEARCH");
            requestBodyMap.put("conversationId", conversationId);
            requestBodyMap.put("question", question);
            requestBodyMap.put("userAction", userAction);
            requestBodyMap.put("model", model);

            Map<String, Object> chatOption = new LinkedHashMap<>();
            chatOption.put("autoTts", false);
            requestBodyMap.put("chatOption", chatOption);
            requestBodyMap.put("files", new ArrayList<>());

            JSONObject requestBody = new JSONObject(requestBodyMap);

            // 请求体键名
            List<String> keys = Arrays.asList("stream", "botCode", "conversationId", "question", "userAction", "model", "chatOption", "files");

            // 生成签名
            String sign = generateSign(timestamp, requestBodyMap, key, keys);

            // 设置请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("Host", "ai-api.dangbei.net");
            headers.put("Connection", "keep-alive");
            headers.put("Content-Length", String.valueOf(requestBody.toString().getBytes(StandardCharsets.UTF_8).length));
            headers.put("deviceId", "1752c6df7d8e442792a079470fb4642e");
            headers.put("Origin", "file://");
            headers.put("nonce", key);
            headers.put("appType", "1");
            headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 10; PEMT00 Build/RP1A.200720.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.186 Mobile Safari/537.36");
            headers.put("Content-Type", "application/json");
            headers.put("localName", "OP4E2F");
            headers.put("timestamp", timestamp);
            headers.put("appVersionCode", "111");
            headers.put("channel", "znds");
            headers.put("sign", sign);
            headers.put("appVersion", "1.1.1");
            headers.put("Accept", "*/*");
            headers.put("Accept-Encoding", "gzip, deflate");
            headers.put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
            headers.put("X-Requested-With", "com.dangbei.ai");

            // 5. 发送请求
            URL url = new URL("https://ai-api.dangbei.net/ai-search/app/chatApi/v1/chat");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // 设置请求头
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // 写入请求体
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
            }
            //获取响应
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("data:")) {
                            String content = line.substring(5).trim().toString();
                            if (content.equals("{}")) break;

                            JSONObject data = new JSONObject(content);
                            String newContent = data.optString("content", "");
                            if (!newContent.equals(fullContent)) {
                                fullContent.append(newContent);
                            }
                        }
                    }
                }
                return fullContent.toString();
            } else {
                return "请求失败!";
            }

        } catch (Exception e) {
            return "发生错误：" + e;
        }
    }

    /**
     * 签名生成
     * timestamp：时间戳
     * data： 需要加密的请求体
     * key：密钥
     * keys：请求体键名
     */
    public static String generateSign(String timestamp, Map<String, Object> data, String key, List keys) throws Exception {
        Map<String, Object> orderedData = new LinkedHashMap<>();
        for (String k : keys) {
            orderedData.put(k, data.get(k));
        }

        String raw = timestamp + new JSONObject(orderedData).toString() + key;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(raw.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString().toUpperCase();
    }
}
