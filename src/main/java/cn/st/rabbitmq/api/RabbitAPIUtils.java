package cn.st.rabbitmq.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * description:通过httpclient调用api
 * </p>
 * 
 * @author coolearth
 * @since 2015年4月23日
 * @version v1.0
 */
public class RabbitAPIUtils {

    private String host = "192.168.44.131";
    private int port = 15672;
    private String authUsername = "yykfk";
    private String authPassword = "wzga218";
    private String scheme = "http";

    private static final String VHOSTS_URI = "/api/vhosts/";
    private static final String WHOAMI_URI = "/api/whoami/";
    private static final String USERS_URI = "/api/users/";

    /**
     * 
     * @param host 主机
     * @param port 端口
     * @param authUsername 用户名
     * @param authPassword 密码
     * @param scheme 协议
     */
    public RabbitAPIUtils(String host, int port, String authUsername, String authPassword,
            String scheme) {
        this.host = host;
        this.port = port;
        this.authUsername = authUsername;
        this.authPassword = authPassword;
        this.scheme = scheme;
    }

    public RabbitAPIUtils() {
        super();
    }

    /**
     * 
     * <p>
     * description:获取vhost列表
     * </p>
     * 
     * @throws Exception
     * 
     * @author coolearth
     * @since 2015年4月24日
     */
    public void getVhosts() throws Exception {
        HttpHost targetHost = new HttpHost(host, port, scheme);
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials(authUsername, authPassword));
        CloseableHttpClient httpClient =
                HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicScheme = new BasicScheme();
        authCache.put(targetHost, basicScheme);
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credentialsProvider);
        context.setAuthCache(authCache);
        HttpGet request = new HttpGet(VHOSTS_URI);
        CloseableHttpResponse response = httpClient.execute(targetHost, request, context);
        System.out.println(response.getStatusLine());
        if (response.getEntity() != null) {
            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);
        }
        response.close();
        httpClient.close();
    }

    /**
     * 
     * <p>
     * description:创建vhost
     * </p>
     * 
     * @param vhost
     * @throws Exception
     * 
     * @author coolearth
     * @since 2015年4月24日
     */
    public void createVhost(String vhost) throws Exception {
        HttpHost targetHost = new HttpHost(host, port, scheme);
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials(authUsername, authPassword));
        CloseableHttpClient httpClient =
                HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicScheme = new BasicScheme();
        authCache.put(targetHost, basicScheme);
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credentialsProvider);
        context.setAuthCache(authCache);
        HttpPut request = new HttpPut(VHOSTS_URI + vhost);
        request.setHeader("content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(targetHost, request, context);
        System.out.println(response.getStatusLine());
        response.close();
        httpClient.close();

    }

    /**
     * 
     * <p>
     * description:当前用户
     * </p>
     * 
     * @throws Exception
     * 
     * @author coolearth
     * @since 2015年4月24日
     */
    public void currentUser() throws Exception {
        HttpHost targetHost = new HttpHost(host, port, scheme);
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials(authUsername, authPassword));
        CloseableHttpClient httpClient =
                HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicScheme = new BasicScheme();
        authCache.put(targetHost, basicScheme);
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credentialsProvider);
        context.setAuthCache(authCache);

        HttpGet request = new HttpGet(WHOAMI_URI);
        request.setHeader("content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(targetHost, request, context);
        System.out.println(response.getStatusLine());
        if (response.getEntity() != null) {
            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);
        }

        response.close();
        httpClient.close();

    }

    /**
     * 
     * <p>
     * description:创建用户
     * </p>
     * 
     * @param username
     * @param password
     * @param tags
     * @throws Exception
     * 
     * @author coolearth
     * @since 2015年4月24日
     */
    public void createUser(String username, String password, TAGS tags) throws Exception {
        HttpHost targetHost = new HttpHost(host, port, scheme);
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials(authUsername, authPassword));
        CloseableHttpClient httpClient =
                HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicScheme = new BasicScheme();
        authCache.put(targetHost, basicScheme);
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credentialsProvider);
        context.setAuthCache(authCache);

        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("password", password);
        map.put("tags", tags.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        String postJson = objectMapper.writeValueAsString(map);
        System.out.println(postJson);
        HttpPut request = new HttpPut(USERS_URI + username);
        request.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(postJson);
        request.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(targetHost, request, context);
        System.out.println(response.getStatusLine());
        if (response.getEntity() != null) {
            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);
        }

        response.close();
        httpClient.close();

    }

}
