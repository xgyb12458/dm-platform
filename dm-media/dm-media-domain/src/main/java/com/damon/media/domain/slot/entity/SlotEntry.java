package com.damon.media.domain.slot.entity;

import com.damon.shared.common.WorkerId;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 资源位数据持久化类
 * @author Damon S.
 */
@Entity
@Builder
@Data
@WorkerId(11)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dm_media_slot")
public final class SlotEntry {
    @Id
    @NotNull
    @Column private Long slotId;
    @Column private String name;
    @Column private String alias;
    @Column private String type;
    @Column private String state;
    @Column private String status;
    @Column private String os;
    @Column(name = "blocked")
    private String blockIndustry;
    @Column private String channel;
    @Column private String snapshot;
    @Column private String description;
    @Column private String appIds;
    @Column private Long specId;
    @Column private Long userId;
    @Column private Long createdBy;
    @Column private Long updatedBy;
    @Column private Timestamp createdAt;
    @Column private Timestamp updatedAt;
}
