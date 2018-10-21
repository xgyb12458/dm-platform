package com.damon.media.domain.slot.spec.aggregate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源位规格扩展
 * @author Damon S.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SpecExtension {
    private Integer frameCount;
    private String lookAndFeel;
}
