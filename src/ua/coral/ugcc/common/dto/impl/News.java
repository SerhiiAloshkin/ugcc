package ua.coral.ugcc.common.dto.impl;

import ua.coral.ugcc.common.annotation.LongString;
import ua.coral.ugcc.common.annotation.ShortString;
import ua.coral.ugcc.common.dto.Dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class News implements Dto, Serializable {

    private Long id = System.currentTimeMillis();
    @ShortString
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
        if (createDate != null) {
            return (Date) createDate.clone();
        }
        return null;
    }

    public void setCreateDate(final Date createDate) {
        if (createDate != null) {
            this.createDate = (Date) createDate.clone();
        }
    }

    public String getShortContent() {
        return abbreviate(content, 500);
    }

    private String abbreviate(String str, int maxWidth) {
        return abbreviate(str, 0, maxWidth);
    }

    private String abbreviate(String str, int offset, int maxWidth) {
        if(str == null) {
            return null;
        } else if(maxWidth < 4) {
            throw new IllegalArgumentException("Minimum abbreviation width is 4");
        } else if(str.length() <= maxWidth) {
            return str;
        } else {
            if(offset > str.length()) {
                offset = str.length();
            }

            if(str.length() - offset < maxWidth - 3) {
                offset = str.length() - (maxWidth - 3);
            }

            String abrevMarker = "...";
            if(offset <= 4) {
                return str.substring(0, maxWidth - 3) + "...";
            } else if(maxWidth < 7) {
                throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
            } else {
                return offset + maxWidth - 3 < str.length()?"..." + abbreviate(str.substring(offset), maxWidth - 3):"..." + str.substring(str.length() - (maxWidth - 3));
            }
        }
    }
}
