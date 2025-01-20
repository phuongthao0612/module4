package com.example.bai2.service.impl;

import com.example.bai2.exception.ResourceNotFoundException;
import com.example.bai2.model.Song;
import com.example.bai2.repository.ISongRepository;
import com.example.bai2.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements IService {
    @Autowired
    private ISongRepository songRepository;

    @Override
    public void add(Song song) {
        songRepository.save(song);
    }

    @Override
    public void update(Song song) {
        songRepository.save(song);
    }

    @Override
    public Song getById(int id) {
        return songRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Song with id" + id + "not found."));
    }

    @Override
    public List<Song> getAll() {
        return songRepository.findAll();
    }
}
