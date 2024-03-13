package com.services;

import com.dtos.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto saveComment(CommentDto commentDto);
    List<CommentDto> findCommentsByMovieId(Long movieId);
    boolean deleteComment(Long commentId);
    // Autres méthodes selon les besoins, comme la mise à jour d'un commentaire
}
