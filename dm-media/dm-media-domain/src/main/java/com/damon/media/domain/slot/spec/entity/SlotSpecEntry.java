package com.damon.media.domain.slot.spec.entity;

import com.damon.shared.common.WorkerId;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 资源位规格
 * @author Damon S.
 */
@Entity
@Data
@Builder
@WorkerId(4)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "damon_media_slot_spec")
public final class SlotSpecEntry {
    @Id
    @NonNull
    @Column private Long specId;
    @Column private int width;
    @Column private int height;
    @Column private String imageType;
    @Column private int imageSize;
    @Column private String snapshot;
    @Column private String state;
    @Column private String slotType;
    @Column private String layoutIds;
    @Column private String specExt;
    @Column private Long createdBy;
    @Column private Long updatedBy;
    @Column private Timestamp createdAt;
    @Column private Timestamp updatedAt;
}
