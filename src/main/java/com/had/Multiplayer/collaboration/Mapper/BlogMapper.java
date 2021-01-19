package com.had.Multiplayer.collaboration.Mapper;

import com.had.Multiplayer.collaboration.bean.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    void saveBlog(Blog blog);

    List<Blog> getBlog(Blog blog);

    Blog getBlogById(int id);

    void updateBlogById(Blog blog);

    void deleteBlogById(int id);
}
