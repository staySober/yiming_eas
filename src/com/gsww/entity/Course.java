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
@Table(name = "tb_course")
@AutoDao(name = "courseDao")
public class Course {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column(name = "courseId")
	private String courseId;
	private String name;
	private String courseNo;
	private String brief;// 简介
	private String courseType;
	private String grades;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courseTable")
	private Enclosure courseTable;// 课表

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "data")
	private Enclosure data;// 资料

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public Enclosure getCourseTable() {
		return courseTable;
	}

	public void setCourseTable(Enclosure courseTable) {
		this.courseTable = courseTable;
	}

	public Enclosure getData() {
		return data;
	}

	public void setData(Enclosure data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
