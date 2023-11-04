package com.cansu.reportingdemo.service.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
@Getter
@Setter
@Component
public class RestClient {

    // use context headers for every request
    private Boolean useContextHeaders = true;

    // default base url
    private String baseURL;
    // default timeout
    private TimeoutLevel timeout = TimeoutLevel.MEDIUM;
    // common headers
    private HttpHeaders headers;



    @Autowired
    private MultiTimeoutRestTemplateHolder restTemplateHolder;


    @PostConstruct
    public void initialize(){
        // default headers
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }


    private String createURL(String url){
        if(baseURL == null || baseURL.isEmpty()) return url;
        return baseURL + url; // TODO: check multiple slash
    }
    private URI createURL(URI url){

        if(baseURL == null || baseURL.isEmpty()) return url;
        try {
            return new URI(baseURL + url.toString()); // TODO: check multiple slash
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeHeader(String name){
        headers.remove(name);
    }
    public void addHeader(String name, String value){
        headers.add(name, value);
    }
    public void setBasicAuth(String username, String password){
        headers.setBasicAuth(username, password);
    }
    public void setBearerAuth(String token){
        headers.setBearerAuth(token);
    }
    public <T> ResponseEntity<T> get(String url, @Nullable HttpEntity<?> requestEntity, Class<T> responseType){
        return get(url, requestEntity, responseType, TimeoutLevel.MEDIUM);
    }
    public <T> ResponseEntity<T> get(String url, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, TimeoutLevel level){
        return restTemplateHolder.getRestTemplateForLevel(level).exchange(createURL(url), HttpMethod.GET, requestEntity, responseType);
    }



    // RestTemplate operations (org.springframework.web.client.RestOperations)

    // GET

    //
    @Nullable
    public <T> T getForObject(String url, Class<T> responseType,TimeoutLevel level, Object... uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).getForObject(createURL(url),responseType, uriVariables);
    }
    @Nullable
    public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).getForObject(createURL(url),responseType, uriVariables);
    }

    //
    @Nullable
    public <T> T getForObject(String url, Class<T> responseType, TimeoutLevel level, Map<String, ?> uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).getForObject(createURL(url),responseType,uriVariables);
    }
    @Nullable
    public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).getForObject(createURL(url),responseType,uriVariables);
    }
    //
    @Nullable
    public <T> T getForObject(URI url, Class<T> responseType, TimeoutLevel level) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).getForObject(createURL(url),responseType);
    }

    @Nullable
    public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).getForObject(createURL(url),responseType);
    }

    //
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, TimeoutLevel level, Object... uriVariables)
            throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).getForEntity(createURL(url), responseType, uriVariables);
    }
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).getForEntity(createURL(url), responseType, uriVariables);
    }

    //
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, TimeoutLevel level, Map<String, ?> uriVariables)
            throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).getForEntity(createURL(url), responseType, uriVariables);

    }
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).getForEntity(createURL(url), responseType, uriVariables);

    }
    //
    public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType, TimeoutLevel level) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).getForEntity(createURL(url), responseType);
    }

    public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).getForEntity(createURL(url), responseType);
    }


    // HEAD

    //
    public HttpHeaders headForHeaders(String url,TimeoutLevel level, Object... uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).headForHeaders(createURL(url), uriVariables);
    }
    public HttpHeaders headForHeaders(String url, Object... uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).headForHeaders(createURL(url), uriVariables);
    }

    //
    public HttpHeaders headForHeaders(String url, TimeoutLevel level, Map<String, ?> uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).headForHeaders(createURL(url), uriVariables);

    }
    public HttpHeaders headForHeaders(String url,  Map<String, ?> uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).headForHeaders(createURL(url), uriVariables);

    }
    //
    public HttpHeaders headForHeaders(URI url, TimeoutLevel level) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).headForHeaders(url);
    }
    public HttpHeaders headForHeaders(URI url) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).headForHeaders(url);
    }

    // POST
    //
    @Nullable
    public URI postForLocation(String url,TimeoutLevel level, @Nullable Object request, Object... uriVariables)
            throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).postForLocation(createURL(url), request, uriVariables);

    }

    @Nullable
    public URI postForLocation(String url, @Nullable Object request, Object... uriVariables)
            throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).postForLocation(createURL(url), request, uriVariables);

    }

    //
    @Nullable
    public URI postForLocation(String url,TimeoutLevel level, @Nullable Object request, Map<String, ?> uriVariables)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).postForLocation(createURL(url), request, uriVariables);

    }
    @Nullable
    public URI postForLocation(String url, @Nullable Object request, Map<String, ?> uriVariables)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).postForLocation(createURL(url), request, uriVariables);

    }

    //
    @Nullable
    public URI postForLocation(URI url,TimeoutLevel level, @Nullable Object request) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).postForLocation(createURL(url), request);

    }
    @Nullable
    public URI postForLocation(URI url,@Nullable Object request) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).postForLocation(createURL(url), request);

    }

    //
    @Nullable
    public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType,
                               TimeoutLevel level,Object... uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).postForObject(createURL(url), request, responseType,uriVariables);

    }
    @Nullable
    public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType,
                               Object... uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).postForObject(createURL(url), request, responseType,uriVariables);

    }

    //
    @Nullable
    public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, TimeoutLevel level,
                               Map<String, ?> uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).postForObject(createURL(url), request, responseType,uriVariables);

    }
    @Nullable
    public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType,
                               Map<String, ?> uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).postForObject(createURL(url), request, responseType,uriVariables);

    }

    //
    @Nullable
    public <T> T postForObject(URI url, @Nullable Object request, Class<T> responseType, TimeoutLevel level)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).postForObject(createURL(url), request, responseType);

    }
    @Nullable
    public <T> T postForObject(URI url, @Nullable Object request, Class<T> responseType)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).postForObject(createURL(url), request, responseType);

    }
    //
    public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request,
                                               Class<T> responseType, TimeoutLevel level, Object... uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).postForEntity(createURL(url), request, responseType,uriVariables);
    }
    public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request,
                                               Class<T> responseType, Object... uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).postForEntity(createURL(url), request, responseType,uriVariables);
    }
    //
    public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request,
                                               Class<T> responseType, TimeoutLevel level, Map<String, ?> uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).postForEntity(createURL(url), request, responseType,uriVariables);
    }
    public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request,
                                               Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).postForEntity(createURL(url), request, responseType,uriVariables);
    }

    //
    public <T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType, TimeoutLevel level)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).postForEntity(createURL(url), request, responseType);
    }

    public <T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).postForEntity(createURL(url), request, responseType);
    }
    // PUT

    public void put(String url, @Nullable Object request, TimeoutLevel level, Object... uriVariables)
            throws RestClientException {

        restTemplateHolder.getRestTemplateForLevel(level).put(createURL(url), request,uriVariables);
    }
    public void put(String url, @Nullable Object request, Object... uriVariables)
            throws RestClientException {

        restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).put(createURL(url), request,uriVariables);
    }
    //
    public void put(String url, @Nullable Object request, TimeoutLevel level, Map<String, ?> uriVariables)
            throws RestClientException {

        restTemplateHolder.getRestTemplateForLevel(level).put(createURL(url), request,uriVariables);
    }
    public void put(String url, @Nullable Object request, Map<String, ?> uriVariables)
            throws RestClientException {

        restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).put(createURL(url), request,uriVariables);
    }
    //
    public void put(URI url, @Nullable Object request, TimeoutLevel level) throws RestClientException {
        restTemplateHolder.getRestTemplateForLevel(level).put(createURL(url), request);
    }
    public void put(URI url, @Nullable Object request) throws RestClientException {
        restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).put(createURL(url), request);
    }

    // PATCH

    @Nullable
    public <T> T patchForObject(String url, @Nullable Object request, Class<T> responseType,TimeoutLevel level,
                                Object... uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).patchForObject(createURL(url), request,responseType,uriVariables);
    }
    @Nullable
    public <T> T patchForObject(String url, @Nullable Object request, Class<T> responseType,
                                Object... uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).patchForObject(createURL(url), request,responseType,uriVariables);
    }
    //
    @Nullable
    public <T> T patchForObject(String url, @Nullable Object request, Class<T> responseType, TimeoutLevel level,
                                Map<String, ?> uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).patchForObject(createURL(url), request,responseType,uriVariables);
    }
    @Nullable
    public <T> T patchForObject(String url, @Nullable Object request, Class<T> responseType,
                                Map<String, ?> uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).patchForObject(createURL(url), request,responseType,uriVariables);
    }
    //
    @Nullable
    public <T> T patchForObject(URI url, @Nullable Object request, Class<T> responseType, TimeoutLevel level)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).patchForObject(createURL(url), request,responseType);
    }
    @Nullable
    public <T> T patchForObject(URI url, @Nullable Object request, Class<T> responseType)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).patchForObject(createURL(url), request,responseType);
    }


    // DELETE

    public void delete(String url, TimeoutLevel level, Object... uriVariables) throws RestClientException {
        restTemplateHolder.getRestTemplateForLevel(level).delete(createURL(url),uriVariables);
    }
    public void delete(String url, Object... uriVariables) throws RestClientException {
        restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).delete(createURL(url),uriVariables);
    }
    //

    public void delete(String url, TimeoutLevel level, Map<String, ?> uriVariables) throws RestClientException {
        restTemplateHolder.getRestTemplateForLevel(level).delete(createURL(url),uriVariables);
    }
    public void delete(String url, Map<String, ?> uriVariables) throws RestClientException {
        restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).delete(createURL(url),uriVariables);
    }
    //
    public void delete(URI url, TimeoutLevel level) throws RestClientException {
        restTemplateHolder.getRestTemplateForLevel(level).delete(url);
    }
    public void delete(URI url) throws RestClientException {
        restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).delete(url);
    }

    // OPTIONS

    public Set<HttpMethod> optionsForAllow(String url, TimeoutLevel level, Object... uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).optionsForAllow(createURL(url),uriVariables);
    }
    public Set<HttpMethod> optionsForAllow(String url, Object... uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).optionsForAllow(createURL(url),uriVariables);
    }

    //
    public Set<HttpMethod> optionsForAllow(String url, TimeoutLevel level, Map<String, ?> uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).optionsForAllow(createURL(url),uriVariables);
    }
    public Set<HttpMethod> optionsForAllow(String url, Map<String, ?> uriVariables) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).optionsForAllow(createURL(url),uriVariables);
    }
    //
    public Set<HttpMethod> optionsForAllow(URI url, TimeoutLevel level) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).optionsForAllow(url);
    }
    public Set<HttpMethod> optionsForAllow(URI url) throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).optionsForAllow(url);
    }

    // exchange
    //
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                          @Nullable HttpEntity<?> requestEntity, Class<T> responseType, TimeoutLevel level, Object... uriVariables)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).exchange(createURL(url),method, requestEntity,responseType, uriVariables);
    }
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                          @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).exchange(createURL(url),method, requestEntity,responseType, uriVariables);
    }
    //
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                          @Nullable HttpEntity<?> requestEntity, Class<T> responseType, TimeoutLevel level, Map<String, ?> uriVariables)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).exchange(createURL(url),method, requestEntity,responseType, uriVariables);
    }
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                          @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).exchange(createURL(url),method, requestEntity,responseType, uriVariables);
    }
    //
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                          Class<T> responseType, TimeoutLevel level) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).exchange(createURL(url),method, requestEntity,responseType);
    }
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                          Class<T> responseType) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).exchange(createURL(url),method, requestEntity,responseType);
    }
    //
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                          ParameterizedTypeReference<T> responseType, TimeoutLevel level, Object... uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).exchange(createURL(url),method, requestEntity,responseType, uriVariables);
    }
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                          ParameterizedTypeReference<T> responseType, Object... uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).exchange(createURL(url),method, requestEntity,responseType, uriVariables);
    }
    //
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                          ParameterizedTypeReference<T> responseType, TimeoutLevel level, Map<String, ?> uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).exchange(createURL(url),method, requestEntity,responseType, uriVariables);
    }
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                          ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).exchange(createURL(url),method, requestEntity,responseType, uriVariables);
    }

    //
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                          ParameterizedTypeReference<T> responseType, TimeoutLevel level) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).exchange(createURL(url),method,requestEntity,responseType);
    }
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                          ParameterizedTypeReference<T> responseType) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).exchange(createURL(url),method,requestEntity,responseType);
    }
    //
    public <T> ResponseEntity<T> exchange(RequestEntity<?> entity, Class<T> responseType, TimeoutLevel level)
            throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(level).exchange(entity,responseType);
    }

    public <T> ResponseEntity<T> exchange(RequestEntity<?> entity, Class<T> responseType)
            throws RestClientException {
        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).exchange(entity,responseType);
    }

    //
    public <T> ResponseEntity<T> exchange(RequestEntity<?> entity, ParameterizedTypeReference<T> responseType, TimeoutLevel level)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).exchange(entity,responseType);
    }
    public <T> ResponseEntity<T> exchange(RequestEntity<?> entity, ParameterizedTypeReference<T> responseType)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).exchange(entity,responseType);
    }

    // General execution

    @Nullable
    public <T> T execute(String url, HttpMethod method,TimeoutLevel level, @Nullable RequestCallback requestCallback,
                         @Nullable ResponseExtractor<T> responseExtractor, Object... uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).execute(createURL(url),method, requestCallback, responseExtractor, uriVariables);

    }
    @Nullable
    public <T> T execute(String url, HttpMethod method, @Nullable RequestCallback requestCallback,
                         @Nullable ResponseExtractor<T> responseExtractor, Object... uriVariables) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).execute(createURL(url),method, requestCallback, responseExtractor, uriVariables);

    }

    @Nullable
    public <T> T execute(String url, HttpMethod method, TimeoutLevel level, @Nullable RequestCallback requestCallback,
                         @Nullable ResponseExtractor<T> responseExtractor, Map<String, ?> uriVariables)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).execute(createURL(url),method, requestCallback, responseExtractor, uriVariables);
    }
    @Nullable
    public <T> T execute(String url, HttpMethod method, @Nullable RequestCallback requestCallback,
                         @Nullable ResponseExtractor<T> responseExtractor, Map<String, ?> uriVariables)
            throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).execute(createURL(url),method, requestCallback, responseExtractor, uriVariables);
    }
    @Nullable
    public <T> T execute(URI url, HttpMethod method, TimeoutLevel level, @Nullable RequestCallback requestCallback,
                         @Nullable ResponseExtractor<T> responseExtractor) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(level).execute(createURL(url),method, requestCallback, responseExtractor);
    }
    @Nullable
    public <T> T execute(URI url, HttpMethod method, @Nullable RequestCallback requestCallback,
                         @Nullable ResponseExtractor<T> responseExtractor) throws RestClientException {

        return restTemplateHolder.getRestTemplateForLevel(TimeoutLevel.MEDIUM).execute(createURL(url),method, requestCallback, responseExtractor);
    }
}
