package com.xyyh.edc.meta.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.springframework.data.domain.Persistable;

import com.xyyh.edc.meta.api.InputControls;

import lombok.Getter;
import lombok.Setter;

/**
 * 列表单属性，定义一个列的表单展现形式
 * 
 * @author LiDong
 *
 */
@Entity
@Getter
@Setter
public class ColumnFormAttributes implements Persistable<Long> {

	@Id
	private Long id;

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	@OneToOne
	@MapsId
	private com.xyyh.edc.meta.entity.Column column;

	/**
	 * 列字段名称
	 */
	@Column(name = "title_", length = 200)
	private String title;

	/**
	 * 指定字段的取值范围， 对于数值型，进行值校验，对于字符串，进行长度校验
	 */
	@Column(name = "min_")
	private BigDecimal min;

	/**
	 * 指定字段的取值范围
	 */
	@Column(name = "max_")
	private BigDecimal max;

	@Column(name = "input_control_")
	@Enumerated(EnumType.STRING)
	private InputControls inputControl;

	@Column(name = "true_label_")
	private String trueLabel;

	@Column(name = "false_label_")
	private String falseLabel;

	@Override
	public boolean isNew() {
		return Objects.isNull(this.column) && Objects.isNull(this.column.getId());
	}
}
