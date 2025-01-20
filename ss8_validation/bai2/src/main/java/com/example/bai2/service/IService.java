package com.example.bai2.service;

import com.example.bai2.model.Song;

import java.util.List;

public interface IService {
    void add (Song song);
    void update (Song song);
    Song getById(int id);
    List<Song> getAll();
}
