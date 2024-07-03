package com.example.doanjava.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tập Phim")
public class Tapphim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tapphimId;

    @NotNull(message = "Link file is required.")
    private String tapphimupdate;

    private int tapphimthu;

    @ManyToOne
    @JoinColumn(name = "phimId")
    private Phim phim;
}
