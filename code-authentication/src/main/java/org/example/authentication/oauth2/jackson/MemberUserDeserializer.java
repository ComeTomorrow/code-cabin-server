package org.example.authentication.oauth2.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.example.authentication.model.MemberUserDetails;
import org.example.cabin.ums.dto.MemberAuthDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.util.Set;

/**
 * Custom Deserializer for {@link User} class. This is already registered with
 * {@link SysUserMixin}. You can also use it directly with your mixin class.
 *
 * @author Jitendra Singh
 * @see SysUserMixin
 * @since 4.2
 */
public class MemberUserDeserializer extends JsonDeserializer<MemberUserDetails> {

    private static final TypeReference<Set<SimpleGrantedAuthority>> SIMPLE_GRANTED_AUTHORITY_SET = new TypeReference<Set<SimpleGrantedAuthority>>() {
    };

    /**
     * This method will create {@link User} object. It will ensure successful object
     * creation even if password key is null in serialized json, because credentials may
     * be removed from the {@link User} by invoking {@link User#eraseCredentials()}. In
     * that case there won't be any password key in serialized json.
     *
     * @param jp   the JsonParser
     * @param ctxt the DeserializationContext
     * @return the user
     * @throws IOException if a exception during IO occurs
     */
    @Override
    public MemberUserDetails deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        Set<? extends GrantedAuthority> authorities = mapper.convertValue(jsonNode.get("authorities"),
                SIMPLE_GRANTED_AUTHORITY_SET);
        JsonNode passwordNode = readJsonNode(jsonNode, "password");
        Long userId = readJsonNode(jsonNode, "userId").asLong();
        String username = readJsonNode(jsonNode, "username").asText();
        String password = passwordNode.asText("");
        Integer dataScope = readJsonNode(jsonNode, "dataScope").asInt();
        Long deptId = readJsonNode(jsonNode, "deptId").asLong();
        boolean enabled = readJsonNode(jsonNode, "enabled").asBoolean();
        boolean accountNonExpired = readJsonNode(jsonNode, "accountNonExpired").asBoolean();
        boolean credentialsNonExpired = readJsonNode(jsonNode, "credentialsNonExpired").asBoolean();
        boolean accountNonLocked = readJsonNode(jsonNode, "accountNonLocked").asBoolean();
        MemberAuthDTO memberAuthInfo = new MemberAuthDTO();
        memberAuthInfo.setId(userId);
        memberAuthInfo.setUsername(username);
        memberAuthInfo.setPassword(password);
        memberAuthInfo.setEnabled(true);
        MemberUserDetails result = new MemberUserDetails(memberAuthInfo);
//        if (passwordNode.asText(null) == null) {
//            result.eraseCredentials();
//        }
        return result;
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

}
