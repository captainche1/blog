package com.che.blog.iService;

import com.che.blog.entity.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

}
