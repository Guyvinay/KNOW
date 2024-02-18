package com.know.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.know.exception.CoachingNotFoundException;
import com.know.exception.StudentNotFoundException;
import com.know.modals.Coaching;
import com.know.modals.Faculty;
import com.know.repository.CoachingRepository;
import com.know.service.CoachingService;

@Service
public class CoachingServiceImpl implements CoachingService{

	@Autowired
	private CoachingRepository coachingRepository;
	
	@Override
	public Coaching saveCoaching(Coaching coaching) {
		
		return coachingRepository.save(coaching);
	}
	
	@Override
	public List<Coaching> saveAllCoachings(List<Coaching> coachings) {
		
		return coachingRepository.saveAll(coachings);
	}
	@Override
	public List<Coaching> getAllCoaching() {
		List<Coaching> allFaculties = coachingRepository.findAll();
		if(allFaculties.isEmpty())
			throw new CoachingNotFoundException("No Coaching Found!!!");
		return allFaculties;
	}

	@Override
	public List<Coaching> getAllCoachingByPagination(int page, int limit,String direction, String field) {
		Pageable pageRequest = PageRequest.of(
				page, limit, 
				Sort.by(direction.equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, 
						field)
				);
	
	Page<Coaching> pages = coachingRepository.findAll(pageRequest);
	List<Coaching> coachings = pages.getContent();
	
	if(coachings.isEmpty())
		throw new StudentNotFoundException("No Coaching Found!!!");
	
	return coachings;
	}

	@Override
	public Coaching getCoachingById(String coachingId) {
		Coaching coaching = coachingRepository.findById(coachingId).orElseThrow(()-> new CoachingNotFoundException("Coaching with id: "+coachingId+", not found!!!"));
		return coaching;
	}

	@Override
	public Coaching getCoachingByName(String coachingname) {
		
		return null;
	}

	@Override
	public Coaching updateCoachingBy(String coachingId, Coaching coaching) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCoachingBy(String coachingId) {
		Coaching coaching = coachingRepository.findById(coachingId).orElseThrow(
				()-> new CoachingNotFoundException("Coaching with id: "+coachingId+", not found!!!"));
		coachingRepository.delete(coaching);
		return "Coaching with id: "+coachingId+", deleted";
	}

}
