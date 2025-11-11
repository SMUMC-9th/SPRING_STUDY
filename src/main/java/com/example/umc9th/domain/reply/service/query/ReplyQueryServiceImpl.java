package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.exception.ArticleErrorCode;
import com.example.umc9th.domain.article.exception.ArticleException;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.domain.reply.converter.ReplyConverter;
import com.example.umc9th.domain.reply.dto.response.ReplyResponseDTO;
import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;
    private final ReplyConverter replyConverter;

    @Override
    public Reply getReply(Long replyId) {
        return replyRepository.findById(replyId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
    }
    @Override
    public List<Reply> getRepliesByArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        return replyRepository.findAllByArticle(article);
    }

    //offset 기반페이지네이션, 오버로드
    @Override
    public Page<ReplyResponseDTO.ReplyResDTO> getRepliesByArticle(Long articleId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size); //Pageable 구현체

        return replyRepository.findByArticle_IdOrderByCreatedAtDesc(articleId, pageRequest)
                .map(replyConverter::toDTO); //JPA가 반환한 Page<Reply> 객체를 순회하며 각 Reply 엔티티를 ReplyResDTO로 변환
    }
}
