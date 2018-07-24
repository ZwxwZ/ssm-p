package cn.mycompany.core.service.impl;

import cn.mycompany.core.mapper.UserMapper;
import cn.mycompany.core.pojo.User;
import cn.mycompany.core.service.UserService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpSolrServer httpSolrServer;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public List<User> search(String search) throws SolrServerException {
        if ("".equals(search)) {
            return userMapper.findAllUser();
        }
        SolrQuery sq = new SolrQuery();
        sq.setQuery("user_name:*" + search+"*");
        sq.setHighlight(true);
        sq.addHighlightField("user_name");
        sq.setHighlightSimplePre("<font color='red'>");
        sq.setHighlightSimplePost("</font>");
        QueryResponse queryResponse = httpSolrServer.query(sq);
        SolrDocumentList results = queryResponse.getResults();
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        ArrayList<User> userList = new ArrayList<>();
        for (SolrDocument doc : results) {
            User user = new User();
            String key = doc.get("id").toString();
            List<String> list = highlighting.get(key).get("user_name");
            String username = "";
            if (list != null && list.size() > 0) {
                username = list.get(0);
            } else {
                username=doc.get("user_name").toString();
            }
            user.setName(username);
            try {
                int id = Integer.parseInt(key.trim()) ;
                user.setId(id);
                user.setBirthday(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(doc.get("user_birthday").toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            userList.add(user);
        }
        return userList;
    }
}
