package com.example.doanjava.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Phim")
public class Phim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phimId;

    @NotBlank(message = "tên phim phải có.")
    @Size(max = 255, message = "Tên phim không được vượt quá 255 ký tự")
    private String tenphim;

    @NotBlank(message = "Movie description is required.")
    private String description;

    private String imageurl;

    private LocalDate year;

    private Integer sotapphimhientai;

    private int sotapphim;

    @ManyToOne
    @JoinColumn(name = "theloaiId")
    private Theloai theloai;

    @ManyToOne
    @JoinColumn(name = "quocgiaId")
    private Quocgia quocgia;
}
