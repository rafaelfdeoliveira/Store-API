package com.rafael.userapi.repository.specification;

import com.rafael.userapi.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class UserSpecification {
    public static Specification<User> filterByUserNameList(List<String> userNameList) {
        return (root, query, builder) -> builder.in(root.get("userName")).value(userNameList);
    }
}
