package com.example.doanjava.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Thể Loại")
public class Theloai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theloaiId;
    @NotBlank(message = "Tên thể loại không được để trống")
    @Size(max = 255, message = "Tên thể loại không được vượt quá 255 ký tự")
    private String tentheloai;

    private String description;

}
