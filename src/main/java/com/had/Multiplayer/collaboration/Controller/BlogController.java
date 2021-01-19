package com.had.Multiplayer.collaboration.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.had.Multiplayer.collaboration.Service.BlogService;
import com.had.Multiplayer.collaboration.Service.UserService;
import com.had.Multiplayer.collaboration.bean.Blog;
import com.had.Multiplayer.collaboration.bean.Result;
import com.had.Multiplayer.collaboration.bean.ResultWithPage;
import com.had.Multiplayer.collaboration.bean.User;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName BlogController
 * @Description TODO
 * @Author had
 * @Date 2021/1/14 15:04
 * @Version 1.0
 **/
@RestController
public class BlogController {
    private UserService userService;
    private BlogService blogService;

    @Inject
    public BlogController(UserService userService, BlogService blogService) {
        this.userService = userService;
        this.blogService = blogService;
    }

    @GetMapping("/blog/{blogId}")
    public Result getBlogById(@PathVariable("blogId") int id) {
        try {
            Blog blog = blogService.getBlogWithUserById(id);
            return new Result("ok", "获取成功", blog);
        } catch (RuntimeException e) {
            return new Result("fail", "系统异常");
        }
    }


    @GetMapping("/blog")
    public Object getBlog(@RequestParam(value = "page", required = false) int pageNo, @RequestParam(defaultValue = "10") int pageSize
            , Integer UserId
            , @RequestParam boolean atIndex) {
        try {
            if (pageNo <= 0) {
                pageNo = 1;
            }
            if (pageSize <= 0) {
                pageSize = 10;
            }
            PageHelper.startPage(pageNo, pageSize);
            String threadUserName = AuthController.getThreadUserName();
            if (threadUserName == null || threadUserName.equals("anonymousUser")) {
                UserId = null;
            } else {
                UserId = userService.getUserByUserName(threadUserName).getId();
            }
            List<Blog> blogList = blogService.getBlog(UserId, atIndex);
            PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
            return new ResultWithPage("ok", "获取成功", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new Result("fail", "系统异常");
        }
    }

    @DeleteMapping("/blog/{blogId}")
    public Result deleteBlogById(@PathVariable("blogId") int id) {
        Blog b = blogService.getBlogById(id);
        if (b == null) {
            return Result.failResult("博客不存在");
        }
        String threadUserName = AuthController.getThreadUserName();
        User user = userService.getUserByUserName(threadUserName);
        if (b.getUserId() != user.getId()) {
            return Result.failResult("无法删除别人的博客");
        }
        try {
            blogService.deleteBlogById(id);
            return Result.successResult("ok", "删除成功");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Result.failResult("删除失败");
        }
    }

    @PatchMapping("/blog/{blogId}")
    public Result updateBlogById(@PathVariable("blogId") int blogId, @RequestBody Blog blog) {
        String threadUserName = AuthController.getThreadUserName();
        User user = userService.getUserByUserName(threadUserName);
        blog.setId(blogId);
        blog.setUserId(user.getId());
        try {
            blogService.updateBlogById(blog);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Result.failResult("修改失败");
        }
        return Result.successResult("ok", "修改成功", blog);
    }


    @PostMapping("/blog")
    public Result saveBlog(@RequestBody Blog blog) {
        String threadUserName = AuthController.getThreadUserName();
        if (threadUserName.equals("anonymousUser")) {
            return Result.failResult("登录后才能操作");
        }
        //description: 博客内容简要描述,可为空，如果为空则后台自动从content 中提取
        if (blog.getDescription() == null || blog.getDescription().equals("")) {
            String firstLine = blog.getContent().substring(0, 10);
            blog.setDescription(firstLine);
        }
        User user = userService.getUserByUserName(threadUserName);
        blog.setUserId(user.getId());
        blog.setCreateAt(Instant.now());
        blog.setUpdateAt(Instant.now());
        try {
            blogService.saveBlog(blog);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Result.failResult("创建博客失败");
        }
        blog.setUser(user);
        return Result.successResult("ok", "创建成功", blog);
    }
}
