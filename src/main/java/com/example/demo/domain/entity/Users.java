package com.example.demo.domain.entity;

import com.example.demo.domain.dto.request.UpdateUserRequest;
import com.example.demo.domain.dto.request.UserSignupRequest;
import com.example.demo.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Users extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;
    private String password;
    private Integer age;
    private Role role;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    public Users(UserSignupRequest userSignupRequest) {
        this.nickName = userSignupRequest.getNickName();
        this.password = userSignupRequest.getPassword();
        this.age = calculateAge(userSignupRequest.getBirthDate());
        this.role = Role.USER;
    }

    private Integer calculateAge(LocalDate birthDate) {

        if (birthDate == null) {
            return null;
        }

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }

    public void encodingPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public Users updateUser(UpdateUserRequest updateUserRequest,PasswordEncoder passwordEncoder) {
        this.nickName = updateUserRequest.getNickName();
        this.password = updateUserRequest.getPassword();
        this.age = updateUserRequest.getAge();
        encodingPassword(passwordEncoder);

        return this;
    }

    public void addReview(Review review) {
        review.setUsers(this);
        this.reviews.add(review);
    }
}
