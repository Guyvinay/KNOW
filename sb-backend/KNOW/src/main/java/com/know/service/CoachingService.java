package com.know.service;

import java.util.List;

import com.know.modals.Coaching;

public interface CoachingService {

	public Coaching saveCoaching(Coaching coaching);
	public List<Coaching> saveAllCoachings(List<Coaching> coaching);
	public List<Coaching> getAllCoaching();
	public List<Coaching> getAllCoachingByPagination(int page, int limit,String direction, String field);
	public Coaching getCoachingById(String coachingId);
	public Coaching getCoachingByName(String coachingname);
	public Coaching updateCoachingBy(String coachingId, Coaching coaching);
	public String deleteCoachingBy(String coachingId);
	
}
