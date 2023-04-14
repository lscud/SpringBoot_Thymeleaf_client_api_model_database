package br.com.mobguide.service.impl;

import br.com.mobguide.service.RestService;
import com.google.gson.Gson;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestServiceImpl<T> implements RestService<T> {

    @Override
    public List<T> get(final String resource) {

        final RestTemplate restTemplate = new RestTemplate();
        List<T> response = null;

        try {
            final HttpEntity<String> httpEntity = new HttpEntity<>("");

            ResponseEntity<List<T>> responseEntity = restTemplate.exchange(resource, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<T>>() {});
            response = responseEntity.getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public int post(String resource, T entity) {

        final RestTemplate restTemplate = new RestTemplate();
        int response = -1;
        try{
            final HttpEntity<T> httpEntity = new HttpEntity<>(entity);

            final ResponseEntity<String> responseEntity = restTemplate.exchange(resource, HttpMethod.POST, httpEntity, String.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                String bodyResponse = responseEntity.getBody();
                return Integer.parseInt(bodyResponse);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public boolean put(String resource, T entity) {

        boolean response = false;

        final RestTemplate restTemplate = new RestTemplate();

        try{

            final HttpEntity<T> httpEntity = new HttpEntity<>(entity);

            ResponseEntity<Boolean> responseEntity = restTemplate.exchange(resource, HttpMethod.PUT, httpEntity, Boolean.class);

            return responseEntity.getStatusCode() == HttpStatus.OK;
            //return responseEntity.getStatusCode() == HttpStatus.OK ? true : false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public boolean delete(String resource) {

        boolean response = false;

        final RestTemplate restTemplate = new RestTemplate();


        try{
            final HttpEntity<String> httpEntity = new HttpEntity<>("");

            ResponseEntity<String> responseEntity = restTemplate.exchange(resource, HttpMethod.DELETE, httpEntity, String.class);

            return responseEntity.getStatusCode() == HttpStatus.OK;

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public T getById(String resource, Class<T> clazz) {

        T response = null;

        final RestTemplate restTemplate = new RestTemplate();

        try{
            final HttpEntity<String>  httpEntity = new HttpEntity<>("");

            final ResponseEntity<String> responseEntity = restTemplate.exchange(resource, HttpMethod.GET, httpEntity, String.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                Gson gson = new Gson();
                T responseObject = gson.fromJson(responseEntity.getBody(), clazz);
                response = responseObject;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return response;
    }
}
