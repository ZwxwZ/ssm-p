package cn.mycompany.core.controller;

import cn.mycompany.core.pojo.User;
import cn.mycompany.core.service.UserService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 跳转列表页面
     * @param model
     * @return
     */
    @RequestMapping("/list.do")
    public String list(Model model,String search) {
        if (search==null) {
            search="";
        }
        List<User> resultList = null;
        try {
            resultList = userService.search(search);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        model.addAttribute("userList", resultList);
        model.addAttribute("search", search);
        return "list";
    }

    /**
     * 跳转保存页面
     * @return
     */
    @RequestMapping("/toInsert.do")
    public String toInsert() {
        return "insert";
    }

    /**
     * 保存页面
     * @param name
     * @param birthday
     * @return
     */
    @RequestMapping("/insert.action")
    public String insert(String name,String birthday) {
        if (name == null) {
            name = "";
        }
        if(birthday==null){
            birthday = "";
        }
        User user = new User();
        user.setName(name);
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
            user.setBirthday(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userService.insertUser(user);
        return "forward:/user/toInsert.do";
    }
}
