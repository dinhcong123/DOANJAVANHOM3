package com.example.doanjava.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Quốc Gia")
public class Quocgia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quocgiaId;

    @NotBlank(message = "Tên Quốc Gia không được để trống")
    @Size(max = 255, message = "Tên Quốc gia không được vượt quá 255 ký tự")
    private String tenquocgia;

    private String description;
}
