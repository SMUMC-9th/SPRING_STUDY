package com.example.umc9th.domain.reply.service.query;

import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.domain.reply.exception.ReplyException;
import com.example.umc9th.domain.reply.exception.code.ReplyErrorCode;
import com.example.umc9th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;

    @Override
    public Reply getReply(
            Long id
    ) {

        // 조회
        Reply reply = replyRepository.findById(id).orElse(null);

        // 없는 경우
        if (reply == null){
            throw new ReplyException(ReplyErrorCode.NOT_FOUND);
        }

        return reply;
    }

    @Override
    public List<Reply> getReplies(

    ){

        // 전체 조회
        List<Reply> replyList = replyRepository.findAll();

        // 없는 경우
        if(replyList.isEmpty()){
            throw new ReplyException(ReplyErrorCode.NOT_FOUND);
        }

        return replyList;
    }
}
