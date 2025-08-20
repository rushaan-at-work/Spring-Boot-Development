package com.newProject.mvc.Services;

import com.newProject.mvc.Records.UniversalSearch;
import com.newProject.mvc.Records.Video;
import com.newProject.mvc.Records.VideoSearch;
import com.newProject.mvc.Entities.VideoEntity;
import com.newProject.mvc.Repsitories.VideoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class VideoService {
    private final VideoRepository repository;
    public VideoService(VideoRepository repository){
        this.repository = repository;
    }
    //List.of() creates an immutable list.
    //This list is used as test data to simulate real videos we might fetch from a database.
    private List<Video> videos = List.of(
            new Video("Need HELP with your SPRING BOOT 3 App?"),
            new Video("Don't do THIS to your own CODE!"),
            new Video("SECRETS to fix BROKEN CODE!")
    );

    public List<Video> getVideos(){
        return videos;
    }
    //Since List.of() produces an immutable list (can’t be changed), you cannot directly call .add() on it without getting an
    // UnsupportedOperationException.
    //The solution:
    //Make a temporary mutable copy (ArrayList) of the current immutable list.
    //Add the new item to the temporary list.
    //Convert it back to a new immutable list with List.copyOf().

    public Video create(Video newVideo){
        List<Video> extend = new ArrayList<>(videos); //extend already contains vidoes content.
        extend.add(newVideo);
        this.videos = List.copyOf(extend);
        return newVideo;
    }

    public List<VideoEntity> search(VideoSearch videoSearch){
        if(StringUtils.hasText(videoSearch.name()) && StringUtils.hasText(videoSearch.description())){
            return repository.findByNameContainsOrDescriptionContainsAllIgnoreCase(videoSearch.name(), videoSearch.description());
        }
        if(StringUtils.hasText(videoSearch.name())){
            return repository.findByNameContains(videoSearch.name());
        }
        if(StringUtils.hasText(videoSearch.description())){
            return repository.findByDescriptionContains(videoSearch.description());
        }
        return Collections.emptyList();
    }

    public List<VideoEntity> search(UniversalSearch search){
        VideoEntity probe = new VideoEntity();
        if(StringUtils.hasText(search.value())){
            probe.setName(search.value());
            probe.setDescription(search.value());
        }
        Example<VideoEntity> example = Example.of(probe,
                ExampleMatcher.matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example);
    }
}
