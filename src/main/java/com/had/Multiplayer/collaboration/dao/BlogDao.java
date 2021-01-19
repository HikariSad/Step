package com.had.Multiplayer.collaboration.dao;

import com.had.Multiplayer.collaboration.Mapper.BlogMapper;
import com.had.Multiplayer.collaboration.bean.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BlogDao
 * @Description TODO
 * @Author had
 * @Date 2021/1/14 19:44
 * @Version 1.0
 **/
@Service
public class BlogDao {
    @Autowired
    private BlogMapper blogMapper;


    public void saveBlog(Blog blog) {
        blogMapper.saveBlog(blog);
    }

    public List<Blog> getBlog(Blog blog) {
        return blogMapper.getBlog(blog);
    }

    public Blog getBlogById(int id) {
        return blogMapper.getBlogById(id);
    }

    public void updateBlogById(Blog blog) {
        blogMapper.updateBlogById(blog);
    }

    public void deleteBlogById(int id) {
        blogMapper.deleteBlogById(id);
    }
}
