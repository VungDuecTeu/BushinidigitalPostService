package dev.lam.models;


import javax.persistence.*;

import dev.lam.user.UserAccount;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Setter
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int post_id;
	
	@Column
	private String title;
	
	@Column
	private String content_body;
	
	@Column
	private String date_created;
	
	@Column
	private String view_count;
	
	@Column
	private String category;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserAccount user;
}
