package org.example.service;

import org.example.pojo.PageBean;
import org.example.pojo.RoleChangeRequest;

public interface RoleChangeService {
    void add(RoleChangeRequest roleChangeRequest);

    PageBean<RoleChangeRequest> list(Integer pageNum, Integer pageSize, Integer userId, String status);

    void update(RoleChangeRequest roleChangeRequest);
}
