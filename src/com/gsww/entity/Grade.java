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
@Table(name = "tb_grade")
@AutoDao(name = "gradeDao")
public class Grade {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column(name = "gradeId")
	private String gradeId;
	private String gradeName;
	private String gradeSum;// 班级人数
	private String brief;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courseTable")
	private Enclosure courseTable;// 课表

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentNames")
	private Enclosure studentNames;// 学生名单

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "data")
	private Enclosure data;// 资料
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="userId")
    public User user;
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeSum() {
		return gradeSum;
	}

	public void setGradeSum(String gradeSum) {
		this.gradeSum = gradeSum;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Enclosure getCourseTable() {
		return courseTable;
	}

	public void setCourseTable(Enclosure courseTable) {
		this.courseTable = courseTable;
	}

	public Enclosure getStudentNames() {
		return studentNames;
	}

	public void setStudentNames(Enclosure studentNames) {
		this.studentNames = studentNames;
	}

	public Enclosure getData() {
		return data;
	}

	public void setData(Enclosure data) {
		this.data = data;
	}

}
