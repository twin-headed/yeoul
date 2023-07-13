package com.myapp.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentVO {
    private int seq;
    private String content;
    private int boardSeq;
    private String id;
    private String registDate;
    private int followSeq;

    List<CommentVO> subComment = new ArrayList<CommentVO>();
}
