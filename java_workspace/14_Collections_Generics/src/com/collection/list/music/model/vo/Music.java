package com.collection.list.music.model.vo;

import java.util.Objects;

public class Music implements Comparable<Music>{
	private String title;
	private String singer;
	
	public Music() {}

	public Music(String title, String singer) {
		this.title = title;
		this.singer = singer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		return title + "\t\t" + singer;
	}

	@Override
	public int compareTo(Music o) {
		return this.singer.compareTo(o.singer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(singer, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		// 같은 타입여부 검사
		if (obj instanceof Music)
//		if (this.getClass() != obj.getClass())
			return false;
		
		// this와 obj는 메모리상 다른 객체이다.
		// obj는 null이 아니고, Music타입이다. 고로 Music으로 형변환 할 수 있다.
		Music other = (Music) obj;
		if (singer == null) {
			if (other.singer != null)
				return false;
		} else if (!singer.equals(other.singer))
			return false;
		
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		
		return true;
	}
	
	
	
}
