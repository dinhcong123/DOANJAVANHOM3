package com.example.doanjava.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Lưu Phim")
public class Luuphim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long luuphimId;

    @ManyToOne
    @JoinColumn(name = "phimId")
    private Phim phim;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
