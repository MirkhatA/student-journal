package com.jdbc.starter.services;

import com.jdbc.starter.database.dao.GroupDao;
import com.jdbc.starter.database.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupDao groupDao;

    @Autowired
    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<Group> getAllGroups() {
        return groupDao.findAll();
    }

    public Optional<Group> getGroupById(Long id) {
        return groupDao.findById(id);
    }

    public Group saveGroup(Group group) {
        return groupDao.save(group);
    }

    public void updateGroup(Group group) {
        groupDao.update(group);
    }

    public boolean deleteGroup(Long id) {
        return groupDao.delete(id);
    }
}
