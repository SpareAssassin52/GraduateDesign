package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.security.RolesAllowed;
import org.apache.ibatis.annotations.Mapper;
import org.example.mapper.RoleChangeMapper;
import org.example.pojo.PageBean;
import org.example.pojo.RoleChangeRequest;
import org.example.service.RoleChangeService;
import org.example.utls.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleChangeServiceImpl implements RoleChangeService {
    @Autowired
    private RoleChangeMapper roleChangeMapper;
    @Override
    public void add(RoleChangeRequest roleChangeRequest) {
        roleChangeMapper.add(roleChangeRequest);
    }

    @Override
    public PageBean<RoleChangeRequest> list(Integer pageNum, Integer pageSize, Integer userId, String status) {
        PageBean<RoleChangeRequest> rc=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<RoleChangeRequest> lrc=roleChangeMapper.list(userId,status);
        Page<RoleChangeRequest> p=(Page<RoleChangeRequest>) lrc;
        rc.setTotal(p.getTotal());
        rc.setItems(p.getResult());
        return rc;
    }

    @Override
    public void update(RoleChangeRequest roleChangeRequest) {
        //no need to change updated_at because sql column's attribute is set to update when updated.
        roleChangeMapper.update(roleChangeRequest);
    }
}
