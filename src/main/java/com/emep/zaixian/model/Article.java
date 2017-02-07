package com.emep.zaixian.model;

import java.io.Serializable;

public class Article  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2637091151932813505L;

	private Long id;

	private String title;

	private String url;

	private String content;

	private String date;

	public Article() {
		super();
	}

	public Article(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
