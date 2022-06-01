package com.rafael.userapi.dto;

import com.rafael.userapi.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDTO {
    @NotNull
    private String userName;
    private String password;
    private Boolean enabled;
    private List<String> roles;

    public static UserDTO convert(User user) {
        UserDTO dto = new UserDTO();
        dto.setEnabled(user.getEnabled());
        dto.setUserName(user.getUserName());
        dto.setRoles(user.getAuthorities()
                .stream()
                .map((authority -> authority.getAuthorityKey().getAuthority()))
                .collect(Collectors.toList()));
        return dto;
    }
}
