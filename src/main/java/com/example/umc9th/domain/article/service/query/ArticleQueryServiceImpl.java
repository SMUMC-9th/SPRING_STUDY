package com.example.umc9th.domain.article.service.query;

import com.example.umc9th.domain.article.dto.response.ArticleListResponseDTO;
import com.example.umc9th.domain.article.dto.response.ArticleResponseDTO;
import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.article.repository.ArticleRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService{

    private final ArticleRepository articleRepository;

    // cursor 기반 페이지네이션
    @Override
    public ArticleListResponseDTO getArticles(Long cursorId, int size) {
        Pageable pageable = PageRequest.of(0, size);
        Slice<Article> articleSlice;

        // 첫 페이지 조회
        if(cursorId == null){
            articleSlice = articleRepository.findByOrderByIdDesc(pageable);
        }
        else{
            articleSlice = articleRepository.findAllByIdLessThanOrderByIdDesc(cursorId, pageable);
        }
        List<ArticleResponseDTO> articleList = articleSlice.stream().map(ArticleResponseDTO::from).toList();

        //다음 커서 id 계산
        Long nextCursorId = null;
        if(!articleList.isEmpty()){
            //목록의 마지막 게시글 DTO에서 id를 가져오기
            nextCursorId = articleList.get(articleList.size() - 1).id();
        }
        return new ArticleListResponseDTO(
                articleList,
                nextCursorId,
                articleSlice.hasNext()
        );
    }

    @Override
    public Article getArticle(Long id) {
        // 구현, 힌트: findById(Long id)
        // findById의 결과로 Optional 형태가 나올 예정인데 1주차 워크북의 구현된 Error code를 참고하여 ArticleErrorCode를 작성해보시고 직접 에러를 발생시키셔도 좋고 아니면 일단 .get()을 사용하시고 제가 세미나에서 알려드릴게요
        return articleRepository.findById(id).orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
    }

}
