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
@Table(name = "tb_student")
@AutoDao(name = "studentDao")
public class Student {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column(name = "studentId")
	private String studentId;
	private String type;// 2推免生、3奖学金、4转专业、5留学生、6本科生、7研究生
	private String studentNo;
	private String name;
	private String sex;
	private String score;
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="gradeId")
	private Grade grade;
	private String createTime;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "data")
	private Enclosure data;// 相关资料

	private String mark;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getType() {
		return type;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Enclosure getData() {
		return data;
	}

	public void setData(Enclosure data) {
		this.data = data;
	}

}
