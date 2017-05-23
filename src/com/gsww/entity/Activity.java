package com.gsww.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gsww.dao.base.impl.AutoDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_activity")
@AutoDao(name = "activityDao")
public class Activity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column(name = "activityId")
	private String activityId;
	private String type;
	private String name;
	private String brief;// 简介
	private String result;
	private String address;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "data")
	private Enclosure data;// 相关资料
	private String mark;
	private String createTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Enclosure getData() {
		return data;
	}

	public void setData(Enclosure data) {
		this.data = data;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
