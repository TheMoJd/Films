package com.services.impl;

import com.dtos.CommentDto;
import com.entities.Comment;
import com.repositories.CommentRepository;
import com.services.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;

    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto saveComment(CommentDto commentDto) {
        Comment comment = convertToEntity(commentDto);
        comment = commentRepository.save(comment);
        return convertToDto(comment);
    }

    @Override
    public List<CommentDto> findCommentsByMovieId(Long movie_id) {
        System.out.println("On est la ");
        //System.out.println(commentRepository.findByMovieId(movie_id).toString());
        //[comments.forEach(comment -> log.info(comment.getComment_id().toString()));

        return commentRepository.findByMovieId(movie_id).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteComment(Long comment_id) {
        if (commentRepository.existsById(comment_id)) {
            commentRepository.deleteById(comment_id);
            return true;
        }
        return false;
    }

    private CommentDto convertToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        if (comment.getId() != null) {
            commentDto.setComment_id(comment.getId());
        }
        BeanUtils.copyProperties(comment, commentDto);
        return commentDto;
    }

    private Comment convertToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        return comment;
    }



}
