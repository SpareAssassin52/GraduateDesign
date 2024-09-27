package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.AdminActionsMapper;
import org.example.pojo.AdminActions;
import org.example.pojo.PageBean;
import org.example.pojo.RoleChangeRequest;
import org.example.service.AdminActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AdminActionsServiceImpl implements AdminActionsService {

    @Autowired
    private AdminActionsMapper adminActionsMapper;

    @Override
    public void add(AdminActions adminActions) {
        adminActionsMapper.add(adminActions);
    }

    @Override
    public PageBean<AdminActions> list(Integer pageNum, Integer pageSize) {
        PageBean<AdminActions> aa=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<AdminActions> laa=adminActionsMapper.list();
        Page<AdminActions> p=(Page<AdminActions>) laa;
        aa.setTotal(p.getTotal());
        aa.setItems(p.getResult());
        return aa;
    }
}
