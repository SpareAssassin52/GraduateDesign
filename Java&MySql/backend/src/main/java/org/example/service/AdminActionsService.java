package org.example.service;

import org.example.pojo.AdminActions;
import org.example.pojo.PageBean;

public interface AdminActionsService {
    void add(AdminActions adminActions);

    PageBean<AdminActions> list(Integer pageNum, Integer pageSize);
}
