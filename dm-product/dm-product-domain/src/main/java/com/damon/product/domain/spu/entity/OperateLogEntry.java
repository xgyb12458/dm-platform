package com.damon.product.domain.spu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 商品操作日志
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月10日 20:50
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pms_spu_operate_log")
public class OperateLogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long        logId;
    @Column(name = "object_id")
    private Long        objectId;
    @Column private String      target;
    @Column private String      type;
    @Column private String      content;

    @Column(name = "operated_by")
    private Long        operatedBy;
    @Column(name = "operated_at")
    private Timestamp   operatedAt;
}
