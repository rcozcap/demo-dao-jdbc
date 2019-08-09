package model.entities;

public class Student {
	
	private int ra;
	private String name;
	
	public Student() {
		super();
	}

	public Student(int ra, String name) {
		super();
		this.ra = ra;
		this.name = name;
	}

	public int getRa() {
		return ra;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ra;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (ra != other.ra)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [ra=" + ra + ", name=" + name + "]";
	}
	
}
