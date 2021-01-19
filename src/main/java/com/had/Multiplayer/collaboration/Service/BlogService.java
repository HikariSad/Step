package com.had.Multiplayer.collaboration.Service;

import com.had.Multiplayer.collaboration.Mapper.BlogMapper;
import com.had.Multiplayer.collaboration.bean.Blog;
import com.had.Multiplayer.collaboration.bean.Result;
import com.had.Multiplayer.collaboration.bean.User;
import com.had.Multiplayer.collaboration.dao.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName BlogService
 * @Description TODO
 * @Author had
 * @Date 2021/1/14 15:27
 * @Version 1.0
 **/
@Service
public class BlogService {
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private UserService userService;

    public void saveBlog(Blog blog) {
        blogDao.saveBlog(blog);
    }


    public List<Blog> getBlog(Integer userId, boolean atIndex) {
        Blog blog = new Blog();
        blog.setUserId(userId);
        if (atIndex == true) {
            blog.setInIndex(0);
        } else {
            blog.setInIndex(1);
        }
        List<Blog> blogList = blogDao.getBlog(blog);
        if (userId == null) {
            List<User> userList = userService.getUserList();
            return blogList.stream().map(blog1 -> {
                blog1.setUser(userList.stream().filter(user -> user.getId() == blog1.getUserId()).findFirst().get());
                return blog1;
            }).collect(Collectors.toList());
        } else {
            User user = userService.getUserById(userId);
            return blogList.stream().
                    map(blog1 -> {
                        blog1.setUser(user);
                        return blog1;
                    }).collect(Collectors.toList());
        }
    }

    public Blog getBlogsWithUser(Blog blog, List<User> list) {
        for (int i = 0; i < list.size(); i++) {
            if (blog.getUserId() == list.get(i).getId()) {
                blog.setUser(list.get(i));
            }
        }
        return blog;
    }

    public Blog getBlogWithUserById(int id) {
        Blog blog = blogDao.getBlogById(id);
        User user = userService.getUserById(blog.getUserId());
        blog.setUser(user);
        return blog;
    }

    public void updateBlogById(Blog blog) {
        if (true == blog.getAtIndex()) {
            blog.setInIndex(0);
        } else if (false == blog.getAtIndex()) {
            blog.setInIndex(1);
        }
        blogDao.updateBlogById(blog);
    }

    public void deleteBlogById(int id) {
        blogDao.deleteBlogById(id);
    }

    public Blog getBlogById(int id) {
        return blogDao.getBlogById(id);
    }
}
