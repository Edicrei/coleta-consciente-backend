package br.com.impacta.coleta.consciente.user.repository;

import br.com.impacta.coleta.consciente.user.dto.TokenDto;
import br.com.impacta.coleta.consciente.user.dto.UserDto;
import br.com.impacta.coleta.consciente.user.dto.UserDtoRequest;
import br.com.impacta.coleta.consciente.user.dto.UserDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(url="${authserver.hostname}", name = "authorization-server")
public interface AuthorizationServerClient {

    @PostMapping("/oauth/token?grant_type=password&username={username}&password={password}")
    TokenDto getToken(@RequestHeader Map<String, String> headerMap, @PathVariable String username, @PathVariable String password);


    @PostMapping("/user/save")
    UserDtoResponse create(@RequestHeader Map<String, String> headerMap, @RequestBody UserDto userDto);
}
