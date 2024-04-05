package com.example.demo.group;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.student.Student;
import java.time.LocalDateTime;

public class Group {
	private Long id;
	private String topic;
	private String description;
	private Student creator;
	private LocalDateTime  startTime;
	private LocalDateTime  endTime;
	private List<Student> members = new ArrayList<Student>();
	
	public static long index = 0;
	
	public Group(){
		this.id = index++;
		this.creator = new Student("","","","");
		this.members.add(creator);
	}
	
	public Group(Long id,
			String topic,
			String description,
			Student creator,
			LocalDateTime  startTime,
			LocalDateTime  endTime ) {
		super();
		this.id = id;
		this.topic = topic;
		this.description = description;
		this.creator = creator;
		this.startTime = startTime;
		this.endTime = endTime;
		this.members.add(creator);
	}
	
	public Group(String topic,
			String description,
			Student creator,
			LocalDateTime startTime,
			LocalDateTime endTime ) {
		super();
		this.id = index++;
		this.topic = topic;
		this.description = description;
		this.creator = creator;
		this.startTime = startTime;
		this.endTime = endTime;
		this.members.add(creator);
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getCreator() {
        return creator;
    }

    public void setCreator(Student creator) {
        this.creator = creator;
        this.members.add(creator);
    }

    public LocalDateTime  getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime  startTime) {
        this.startTime = startTime;
    }
    
    public LocalDateTime  getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime  endTime) {
        this.endTime = endTime;
    }
    
    public List<Student> getMembers() {
        return members;
    }

    public String getMembersFnames() {
    	String names ="";
    	for(Student member : members) {
    		if(member == null) {
    			continue;
    		}
    		names = names + " " +member.getFname();
		}
        return names;
    }
    
    public void addMember(Student member) {
        this.members.add(member);
    }
    
    public void showMembers() {
    	for(Student member : members) {
    		if(member == null) {
    			continue;
    		}
    		System.out.print(member.getFname() + " ");
		}
    }
    
    // toString method to return a string representation of the object
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", creator='" + creator + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", members'" + members + '\'' +
                '}';
    }
}
