package ua.coral.ugcc.common.dto.impl;

import ua.coral.ugcc.common.annotation.LongString;
import ua.coral.ugcc.common.dto.Dto;

import java.io.Serializable;
import java.util.Date;

public class News implements Dto, Serializable {

    private Long id;
    private String title;
    @LongString
    private String content;
    private Date createDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return (Date) createDate.clone();
    }

    public void setCreateDate(final Date createDate) {
        if (createDate != null) {
            this.createDate = (Date) createDate.clone();
        }
    }
}
