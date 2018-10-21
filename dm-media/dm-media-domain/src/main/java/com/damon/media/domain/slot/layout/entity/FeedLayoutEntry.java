package com.damon.media.domain.slot.layout.entity;

import com.damon.shared.common.WorkerId;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 信息流资源位样式规格
 * @author Damon S.
 */
@Entity
@Data
@Builder
@WorkerId(5)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "damon_media_slot_layout")
public final class FeedLayoutEntry {
    @Id
    @Column private Long layoutId;
    @Column private int width;
    @Column private int height;
    @Column private String layoutType;
    @Column private String state;
    @Column private String snapshot;
    @Column private Long createdBy;
    @Column private Long updatedBy;
    @Column private Timestamp createdAt;
    @Column private Timestamp updatedAt;
}
