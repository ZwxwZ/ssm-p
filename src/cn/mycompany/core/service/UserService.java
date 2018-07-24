package cn.mycompany.core.service;

import cn.mycompany.core.pojo.User;
import org.apache.solr.client.solrj.SolrServerException;

import java.util.List;

public interface UserService {
    List<User> findAllUser();

    void insertUser(User user);

    List<User> search(String search) throws SolrServerException;
}
