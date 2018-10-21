package com.damon.media.domain.app.entity;

import com.damon.shared.common.WorkerId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 媒体数据持久化类
 * @author Damon S.
 */
@Entity
@Data
@Builder
@WorkerId(2)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "damon_media_app")
public final class AppEntry {
    @Id
    @NotNull
    @Column private Long appId;
    @Column private String name;
    @Column private String os;
    @Column private String appKey;
    @Column private String secret;
    @Column private String downloadUrl;
    @Column private String packageName;
    @Column private String type;
    @Column private String status;
    @Column private String state;
    @Column private String source;
    @Column private String industry;
    @Column private String category;
    @Column private String keywords;
    @Column private String snapshot;
    @Column private String description;
    @Column private Long userId;
    @Column private Long createdBy;
    @Column private Long updatedBy;
    @Column private Timestamp createdAt;
    @Column private Timestamp updatedAt;
}
