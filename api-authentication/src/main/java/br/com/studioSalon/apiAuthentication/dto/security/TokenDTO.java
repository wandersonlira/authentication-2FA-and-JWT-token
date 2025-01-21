package br.com.studioSalon.apiAuthentication.dto.security;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String userName;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;


    public TokenDTO() {}

    public TokenDTO(String userName, Boolean authenticated, Date created,Date expiration, String accessToken, String refreshToken ) {
        this.userName = userName;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenDTO tokenDTO = (TokenDTO) o;
        return Objects.equals(userName, tokenDTO.userName) && Objects.equals(authenticated, tokenDTO.authenticated) && Objects.equals(created, tokenDTO.created) && Objects.equals(expiration, tokenDTO.expiration) && Objects.equals(accessToken, tokenDTO.accessToken) && Objects.equals(refreshToken, tokenDTO.refreshToken);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(userName);
        result = 31 * result + Objects.hashCode(authenticated);
        result = 31 * result + Objects.hashCode(created);
        result = 31 * result + Objects.hashCode(expiration);
        result = 31 * result + Objects.hashCode(accessToken);
        result = 31 * result + Objects.hashCode(refreshToken);
        return result;
    }
}
