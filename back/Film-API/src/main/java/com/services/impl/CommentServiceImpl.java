package com.services.impl;

import com.dtos.CommentDto;
import com.entities.Comment;
import com.repositories.CommentRepository;
import com.services.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;

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
    public List<CommentDto> findCommentsByMovieId(Long movieId) {
        return commentRepository.findByMovieId(movieId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteComment(Long commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }

    private CommentDto convertToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(comment, commentDto);
        return commentDto;
    }

    private Comment convertToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        return comment;
    }

    public List<CommentDto> findByMovieId(Long movie_id) {
        return commentRepository.findByMovieId(movie_id).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CommentDto save(CommentDto commentDto) {
        Comment comment = convertToEntity(commentDto);
        comment = commentRepository.save(comment);
        return convertToDto(comment);
    }



}
